package com.backend.backend.models;

import java.sql.Time;
import java.util.Date;

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
@Table(name = "autorizacao", schema="sgrhu") // Mapeia a tabela
public class Autorizacao {
	@Id // Especifica qual o PK da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o id automaticamente
    @Column(name = "idautorizacao", nullable = false) // @Column é usado quando o nome da variável é diferente do nome do atributo no BD
	protected Long idAutorizacao;
	
	@Column(name = "cpfautorizado", nullable = false)
	protected String cpfAutorizado;
	
	protected String primeiro_nome;
    
	protected String sobrenome;
    
	@Column(name = "datainicio", nullable = false)
	protected Date dataInicio;
    
	@Column(name = "horainicio", nullable = false)
	protected Time horaInicio;
    
	@Column(name = "datafim", nullable = false)
	protected Date dataFim;
    
	@Column(name = "horafim", nullable = false)
	protected Time horaFim;
	
	@Column(name = "idchefia", nullable = false)
	protected int idChefia;
}