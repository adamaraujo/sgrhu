package com.backend.backend.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSetMetaData;
import java.util.*;

import org.hibernate.boot.model.source.internal.hbm.ResultSetMappingBinder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.repository.DAOAtendente;
import com.backend.backend.repository.DAOAutorizacao;
import com.backend.backend.repository.DAOChefia;
import com.backend.backend.repository.DAORefeicao;
import com.backend.backend.repository.DAOTicket;
import com.backend.backend.repository.DAOTicketAvulso;
import com.backend.backend.repository.DAOTicketNormal;
import com.fasterxml.jackson.core.JsonEncoding;
import com.backend.backend.consulta.consultaHospital;
import com.backend.backend.models.Refeicao;
import com.backend.backend.models.Ticket;
import com.backend.backend.models.TicketNormal;
import com.backend.backend.models.Usuario;

@RestController // Indica que a classe é do tipo Controller
@RequestMapping("/tickets") // Indica a rota de acesso
public class TicketController {
	private DAOChefia daoChefia;
	private DAOAtendente daoAtendente;
	private DAOTicket daoTicket;
	private DAOTicketAvulso daoTicketAvulso;
	private DAOTicketNormal daoTicketNormal;
	private DAORefeicao daoRefeicao;
	private DAOAutorizacao daoAutorizacao;
	
	@Autowired
	TicketController (DAOChefia chefiaRepository, DAOAtendente atendenteRepository, DAOTicket ticketRepository, DAOTicketAvulso avulsoRepository, DAOTicketNormal normalRepository, DAORefeicao refeicaoRepository, DAOAutorizacao autorizacaoRepository) {
		this.daoTicket = ticketRepository;
		this.daoTicketAvulso = avulsoRepository;
		this.daoTicketNormal = normalRepository;
		this.daoChefia = chefiaRepository;
		this.daoAtendente = atendenteRepository;
		this.daoAutorizacao = autorizacaoRepository;
	}
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital", "sgrhu", "sgrhu");
    }
	
	public static ResultSet retrieveData(String buscaDado) throws Exception {
	      Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital", "sgrhu", "sgrhu");
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(buscaDado);
	      return rs;
	}
	
	/*
	
	@RequestMapping(value = "/consulta/colaborador/{cpf}", method = RequestMethod.GET, produces="application/json") 
	public ResponseEntity<String> consultaColaborador(@PathVariable String cpf) {
		JSONArray entities = new JSONArray();	
		String buscaColaborador = "SELECT * FROM essgrhu.servidor as y\r\n" + 
				"inner join essgrhu.plantao as r \r\n" + 
				"on y.idplantao = r.idplantao\r\n" + 
				"inner join essgrhu.turno as b\r\n" + 
				"on y.idturno = b.idturno WHERE y.cpfservidor = '"+ cpf + "';";
		
		try {
			ResultSet ps = retrieveData(buscaColaborador);
				while(ps.next()) {
					JSONObject record = new JSONObject();
				    record.put("matriculasiape", ps.getString("matriculasiape"));
				    record.put("primeiro_nome", ps.getString("primeiro_nome"));
				    record.put("sobrenome", ps.getString("sobrenome"));
				    record.put("cargo", ps.getString("cargo"));
				    record.put("diaPlantao", ps.getString("dia"));
				    record.put("horaInicioPlantao:", ps.getTime("horainiciop"));
				    record.put("horaFinalPlantao:", ps.getTime("horafinalp"));
				    record.put("dataPlantao", ps.getDate("dataplantao"));				    
				    record.put("horaInicioTurno", ps.getTime("horainicio"));
				    record.put("horaFinalTurno", ps.getTime("horafinal"));
				    entities.put(record);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
			String result = entities.toString();
			if (result.length() != 2) {
				return new ResponseEntity<String>(result, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
	}
	
	*/
	
	@GetMapping // Não precisa indicar o caminho, pois para este caso, será sempre /tickets
	public List listar(){
	   return daoTicket.findAll();
	}
	
	// Verifica se um cliente já consumiu uma refeição
	public Ticket ticketConsumido (String cpf, String refeicao) {	
		LocalDate localDate = LocalDate.now();
        java.sql.Date dataHoje = java.sql.Date.valueOf( localDate );
		Ticket consumidoHoje = daoTicket.findByCpfClienteAndNomeRefeicaoAndDataConsumo(cpf, refeicao, dataHoje);
		return consumidoHoje;
	}
	
	public String verificaRefeicao (Date data, Time hora) {
	
		LocalDate dataPrevista = LocalDate.parse(data.toString());
		LocalTime horaPrevista = LocalTime.parse(hora.toString());
		
		LocalDate dataAgora = LocalDate.now();
		LocalTime horaAgora = LocalTime.now();
		
		// Horários da refeições conforme Regra de Negócio
		LocalTime inicioCafe = LocalTime.parse("06:50:00");
		LocalTime fimCafe = LocalTime.parse("10:00:00");
		LocalTime inicioAlmoco = LocalTime.parse("11:30:00");
		LocalTime fimAlmoco = LocalTime.parse("13:00:00");
		LocalTime inicioJantar = LocalTime.parse("17:40:00");
		LocalTime fimJantar = LocalTime.parse("19:00:00");
		
		String refeicaoAgora = "";
		
		if (dataAgora.isBefore(dataPrevista) || dataAgora.isEqual(dataPrevista)) {
			if (horaAgora.isAfter(inicioCafe) && horaAgora.isBefore(fimCafe)) {
				refeicaoAgora = "Café da Manhã";
			}
			else if (horaAgora.isAfter(inicioAlmoco) && horaAgora.isBefore(fimAlmoco)) {
				refeicaoAgora = "Almoço";
			}
			else if (horaAgora.isAfter(inicioJantar) && horaAgora.isBefore(fimJantar)) {
				refeicaoAgora = "Jantar";
			}
			else {
				refeicaoAgora = "Nenhuma";
			}	
		}
		else {
			refeicaoAgora = "Nenhuma";
		}
		return refeicaoAgora;
		
	}
	
	@RequestMapping(value = "/consulta/colaborador/{cpf}", method = RequestMethod.GET, produces="application/json") 
	public void consultaColaborador (@PathVariable String cpf) {	
		String buscaColaborador = "SELECT * FROM essgrhu.servidor servidor "
				+ "JOIN essgrhu.turno turno USING (idturno) "
				+ "JOIN essgrhu.plantao plantao USING (idplantao) "
				+ "WHERE servidor.cpfservidor = '" + cpf + "';";
		
		boolean existeColaborador = true;
		LocalDate dataAgora = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf( dataAgora );
		String diaSemana = dataAgora.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt"));
		LocalTime horaAgora = LocalTime.now();
		String refeicao = "";
		boolean ticketConsumido = false;
		
		try {
			
			ResultSet ps = retrieveData(buscaColaborador);
			
			if (ps.next() == false) {
				existeColaborador = false;
		      } else {

		        do {
		        	
		    		LocalTime inicioTurno = LocalTime.parse(ps.getTime("inicioturno").toString());
		    		LocalTime fimTurno = LocalTime.parse(ps.getTime("fimturno").toString());
		    		LocalDate dataPlantao = LocalDate.parse(ps.getDate("dataplantao").toString());
		    		LocalTime inicioPlantao = LocalTime.parse(ps.getTime("horainicio").toString());
		    		LocalTime fimPlantao = LocalTime.parse(ps.getTime("horafinal").toString());
		    		String semana = ps.getString("dia");
		        	
		        	if (horaAgora.isAfter(inicioTurno) && horaAgora.isBefore(fimTurno)) {
		        		refeicao = verificaRefeicao (sqlDate, ps.getTime("fimturno"));
		        	}
		        	else if ( ( dataAgora.isAfter(dataPlantao) || dataAgora.isEqual(dataPlantao) ) && diaSemana.matches(semana) ) {
		        		if (horaAgora.isAfter(inicioPlantao) && horaAgora.isBefore(fimPlantao)) {
		        			refeicao = verificaRefeicao (sqlDate, ps.getTime("horafinal"));
		        		}
		        		else {
		        			refeicao = "Nenhuma";
		        		}
		        	}
		        	else {
		        		refeicao = "Nenhuma";
		        	}
		        	
		        	String cpfServidor = ps.getString("cpfservidor");
	        		Ticket consumido = ticketConsumido(cpfServidor, refeicao);
	        		if (consumido != null) {
	        			ticketConsumido = true;
	        		}
	        		
		        } while (ps.next());
		      }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/consulta/acompanhante/{cpf}", method = RequestMethod.GET, produces="application/json") 
	public ResponseEntity<String> consultaAcompanhante (@PathVariable String cpf) {	
		String buscaAcompanhante = "SELECT * FROM essgrhu.acompanhante acompanhante "
				+ "JOIN essgrhu.paciente paciente USING (idpaciente) "
				+ "NATURAL JOIN essgrhu.internacao AS internacao "
				+ "NATURAL JOIN essgrhu.leito  AS leito "
				+ "NATURAL JOIN essgrhu.unidadeFuncional AS unidadeF "
				+ "WHERE acompanhante.cpfacompanhante = '" + cpf + "';";
		
		String cpfPaciente = "";
		String nome = "";
		String sobrenome = "";
		String refeicao = "";
		boolean existeAcompanhante = true;
		boolean ticketConsumido = false;
		JSONObject record = new JSONObject();
		
		try {
			
			ResultSet ps = retrieveData(buscaAcompanhante);
			
			if (ps.next() == false) {
				existeAcompanhante = false;
		      } else {

		        do {
		        	
		        	cpfPaciente = ps.getString("cpfpaciente");
		        	String buscaTodos = "SELECT * FROM essgrhu.acompanhante acompanhante "
							+ "JOIN essgrhu.paciente paciente USING (idpaciente) "
							+ "WHERE paciente.cpfpaciente = '" + cpfPaciente + "';";
		        	Date data = ps.getDate("dataprevisao");
		        	Time hora = ps.getTime("horaprevisao");
		        	nome = ps.getString("primeiro_nome");
		        	sobrenome = ps.getString("sobrenome");
		        	refeicao = verificaRefeicao(data, hora);
		        	ResultSet rs = retrieveData(buscaTodos);
		        	
		        	while (rs.next()) {
		        		String acompanhantes = rs.getString("cpfacompanhante");
		        		Ticket consumido = ticketConsumido(acompanhantes, refeicao);
		        		if (consumido != null) {
		        			ticketConsumido = true;
		        		}
		        	}       	
		        } while (ps.next());
		      }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!existeAcompanhante) {
			//System.out.println("Não existe");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			//System.out.println(nome);
			//System.out.println(sobrenome);
			//System.out.println(refeicao);
			//System.out.println(ticketConsumido);
			
			record.put("Nome", nome);
			record.put("Sobrenome", sobrenome);
			record.put("Refeição", refeicao);
			record.put("Refeição Consumida", ticketConsumido);
			String result = record.toString();
		    return new ResponseEntity<String>(result, HttpStatus.OK);
			
		}
		
	}	
	
	@RequestMapping(value = "/consulta/paciente/{cpf}", method = RequestMethod.GET, produces="application/json") 
	public ResponseEntity<String> consultaPaciente (@PathVariable String cpf) {
		String buscaInternado = "SELECT * FROM essgrhu.paciente AS paciente "
        		+ "NATURAL JOIN essgrhu.internacao AS internacao "
        		+ "NATURAL JOIN essgrhu.leito  AS leito "
        		+ "NATURAL JOIN essgrhu.unidadeFuncional AS unidadeF "
        		+ "WHERE paciente.cpfpaciente = '" + cpf + "';";
		
		String buscaExame = "SELECT * FROM essgrhu.paciente AS paciente "
				+ "NATURAL JOIN essgrhu.exame AS internacao "
        		+ "WHERE paciente.cpfpaciente = '" + cpf + "';";
		
		String nome = "";
		String sobrenome = "";
		String refeicao = ""; // Qual a refeição que o paciente tem direito
		String localInternacao = ""; // Saber se o paciente está ou não na UTI
		boolean temInternacao = true; // Saber se há internação
		boolean temExame = true; // Saber se há exame
		boolean direitoLanche = false; // Saber se o paciente com exame tem direito ao Lanche
		boolean refeicaoConsumida = false;
		
		JSONObject record = new JSONObject();
		
		try {
					
			ResultSet ps = retrieveData(buscaInternado); // Busca internação
			ResultSet rs = retrieveData(buscaExame); // Busca se há exame
			
			// Faz varredura no resultado da busca por exames
			if (rs.next() == false) {
		        temExame = false;
		      } else {

		        do {
		        	nome = rs.getString("primeiro_nome");
		        	sobrenome = rs.getString("sobrenome");
		        	direitoLanche = rs.getBoolean("jejum");
		        } while (rs.next());
		      }
			
			// Faz varredura no resultado da busca por exames
			if (ps.next() == false) {
		        temInternacao = false;
		        if (temExame == true && direitoLanche == true) {
		        	refeicao = "Lanche";
		        }
		        else {
		        	refeicao = "Nenhuma";
		        }
		      } else {

		        do {
		        	nome = ps.getString("primeiro_nome");
		        	sobrenome = ps.getString("sobrenome");
		        	Date data = ps.getDate("dataprevisao");
		        	Time hora = ps.getTime("horaprevisao");
		        	localInternacao = ps.getString("tipo");
		        	
		        	if (localInternacao.matches("UTI")) {
		        		refeicao = "Nenhuma";
		        	}
		        	else {
		        		refeicao = verificaRefeicao(data, hora);
		        	}
		        } while (rs.next());
		      }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//LocalDate datinha = LocalDate.of(2020,3,20);
		//java.sql.Date sqlDate = java.sql.Date.valueOf( datinha );
		
		//Ticket consumido = ticketConsumido("09834523476", "Almoço", sqlDate);
		Ticket consumido = ticketConsumido(cpf, refeicao);
		
		if (consumido != null) {
			refeicaoConsumida = true;
		}
		
		// Não encontrou o paciente com o cpf fornecido
		if (temExame == false && temInternacao == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		else {
			// Cria o objeto JSON 
			record.put("Nome", nome);
			record.put("Sobrenome", sobrenome);
			record.put("Refeição", refeicao);
			record.put("Lanche", direitoLanche);
			record.put("Refeição Consumida", refeicaoConsumida);
			String result = record.toString();
		    return new ResponseEntity<String>(result, HttpStatus.OK);
		}
	}
	
	
	
	
	
}