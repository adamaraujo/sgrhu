package com.backend.backend.models;

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
@Table(name = "jantar", schema="sgrhu") // Mapeia a tabela
public class Jantar {
	@Id // Especifica qual o PK da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o id automaticamente
    @Column(name = "idjantar", nullable = false) // @Column é usado quando o nome da variável é diferente do nome do atributo no BD
	protected Long idJantar;
	protected double valorTotal;
	protected double valorParcial;
	protected Long idRefeicao;
}