package com.backend.backend.models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Cria um construtor com todos os atributos da classe
@NoArgsConstructor // Cria um construtor vazio
@Data // Cria metodos como toString, equals, hashCode, getters e setters.
@Entity // Classe é automaticamente mapeada para a tabela de mesmo nome
@Table(name = "ticket", schema="sgrhu") // Mapeia a tabela
public class Ticket {
	@Id // Especifica qual o PK da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o id automaticamente
	@Column(name = "idticket", nullable = false) 
	protected Long idTicket;
	
	@Column(name = "cpfcliente", nullable = false) 
	protected String cpfCliente;
	
	@Column(name = "dataconsumo", nullable = false) 
	protected String dataConsumo; // Tenho que verificar se esse é realmente o tipo adequado, uma vez que não é o usuário que entra com esse parâmetro de requisição
	
	@Column(name = "horaconsumo", nullable = false) 
	protected String horaConsumo; // Tenho que verificar se esse é realmente o tipo adequado, uma vez que não é o usuário que entra com esse parâmetro de requisição
	
	@Column(name = "idrefeicao", nullable = false) 
	protected Long idRefeicao;
	
	@Column(name = "idatendente", nullable = false) 
	protected Long idAtendente;
	
	@Column(name = "idchefia") 
	protected Long idChefia;
	
	@Column(name = "nomerefeicao") 
	protected String nomeRefeicao;
}