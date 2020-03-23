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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSetMetaData;

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
	//private consultaHospital consultaHU;
	
	@Autowired
	TicketController (DAOChefia chefiaRepository, DAOAtendente atendenteRepository, DAOTicket ticketRepository, DAOTicketAvulso avulsoRepository, DAOTicketNormal normalRepository, DAORefeicao refeicaoRepository) {
		this.daoTicket = ticketRepository;
		this.daoTicketAvulso = avulsoRepository;
		this.daoTicketNormal = normalRepository;
		this.daoChefia = chefiaRepository;
		this.daoAtendente = atendenteRepository;
		//this.consultaHU = huRepository;
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
	
	@RequestMapping(value = "/consulta/paciente/{cpf}", method = RequestMethod.GET, produces="application/json") 
	public ResponseEntity<String> consultaPaciente(@PathVariable String cpf) {
		JSONArray entities = new JSONArray();
		String buscaPaciente = "SELECT * FROM essgrhu.paciente AS paciente "
        		+ "NATURAL JOIN essgrhu.internacao AS internacao "
        		+ "NATURAL JOIN essgrhu.leito  AS leito "
        		+ "NATURAL JOIN essgrhu.unidadeFuncional AS unidadeF "
        		+ "WHERE paciente.cpfpaciente = '" + cpf + "';";
		
		try {
			ResultSet ps = retrieveData(buscaPaciente);
				while(ps.next()) {
					JSONObject record = new JSONObject();
				    record.put("cpfpaciente", ps.getString("cpfpaciente"));
				    record.put("primeiro_nome", ps.getString("primeiro_nome"));
				    record.put("sobrenome", ps.getString("sobrenome"));
				    record.put("datainternacao", ps.getDate("datainternacao"));
				    record.put("dataprevisao", ps.getDate("dataprevisao"));
				    record.put("horainternacao", ps.getTime("horainternacao"));
				    record.put("horaprevisao", ps.getTime("horaprevisao"));
				    record.put("tipo", ps.getString("tipo"));
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
	
	@GetMapping // Não precisa indicar o caminho, pois para este caso, será sempre /tickets
	public List listar(){
	   return daoTicket.findAll();
	}
	
	// && consumidoHOJE.getNomeRefeicao() == ticket.getNomeRefeicao()
	
	// Criando novo ticket
	@RequestMapping(value = "/normal/new", method = RequestMethod.POST)
	public void criar(@RequestBody Ticket ticket) {
		List<Ticket> consumidoHOJE = daoTicket.findBycpfCliente(ticket.getCpfCliente());
		
		int i = 0;
		
		String dataTicket = ticket.getDataConsumo();
		String nomeTicket = ticket.getNomeRefeicao();
		
		while (i < consumidoHOJE.size()) {
			String dataConsumido = consumidoHOJE.get(i).getDataConsumo();
			String nomeConsumido = consumidoHOJE.get(i).getNomeRefeicao();
			if (dataTicket.equals(dataConsumido) && nomeTicket.equals(nomeConsumido)) {
				//return ResponseEntity.notFound().build();
			}
			i = i + 1;
		}
		Refeicao ref = new Refeicao();
		ref.setNomeRefeicao(ticket.getNomeRefeicao());
		ref.setValorParcial(0);
		ref.setValorTotal(0);
		Refeicao registrada = daoRefeicao.save(ref);
		System.out.print(registrada);
		
		//Long idRef = registrada.getIdRefeicao();
		//ticket.setIdRefeicao(idRef);
		
		//Ticket resultado = daoTicket.save(ticket);
		
		//TicketNormal normal = new TicketNormal();
		//normal.setIdTicket(resultado.getIdTicket());
		
		
		//return ResponseEntity.ok().body(registrada);
	}
}