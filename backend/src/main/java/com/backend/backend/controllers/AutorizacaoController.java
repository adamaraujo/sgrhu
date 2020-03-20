package com.backend.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.backend.backend.repository.DAOAutorizacao;
import com.backend.backend.models.Autorizacao;
import com.backend.backend.models.Usuario;

@RestController // Indica que a classe é do tipo Controller
@RequestMapping("/autorizacoes") // Indica a rota de acesso
public class AutorizacaoController {
	private DAOAutorizacao daoAutorizacao;
	
	@Autowired
	AutorizacaoController (DAOAutorizacao autorizacaoRepository) {
		this.daoAutorizacao = autorizacaoRepository;
	}
	
	// Criando nova autorizacao
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Autorizacao criar(@RequestBody Autorizacao autorizacao) {
		Autorizacao autorizacaoExiste = daoAutorizacao.findBycpfAutorizado(autorizacao.getCpfAutorizado());
		if (autorizacaoExiste != null) {
			return autorizacaoExiste; // Retorna a autorizacao já existente
		}
		else {
			return daoAutorizacao.save(autorizacao); // Salva a nova autorizacao
		}
	}
	
	// Retorna todas as autorizações
	@GetMapping // Não precisa indicar o caminho, pois para este caso, será sempre /autorizacoes
	public List<Autorizacao> listar(){
	   return daoAutorizacao.findAll();
	}
	
	// Busca por autorizacao específica usando seu id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity excluir(@PathVariable Long id){
	   return daoAutorizacao.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	// Deletando uma autorização específica usando seu id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return daoAutorizacao.findById(id)
				.map(record -> {
		            daoAutorizacao.deleteById(id);
		            return ResponseEntity.ok().build();
		         }).orElse(ResponseEntity.notFound().build());
	}
	
	// Alterando uma autorizacao
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable("id") Long id,
	                                      @RequestBody Autorizacao autorizacao) {
	   
	   return daoAutorizacao.findById(id)
	           .map(record -> {        	   
	               record.setCpfAutorizado(autorizacao.getCpfAutorizado());
	               record.setPrimeiro_nome(autorizacao.getPrimeiro_nome());
	               record.setSobrenome(autorizacao.getSobrenome());
	               record.setDataFim(autorizacao.getDataFim());
	               record.setDataInicio(autorizacao.getDataInicio());
	               record.setHoraInicio(autorizacao.getHoraInicio());
	               record.setHoraFim(autorizacao.getHoraFim());
	               record.setIdChefia(autorizacao.getIdChefia());
	               Autorizacao atualizada = daoAutorizacao.save(record);
	               return ResponseEntity.ok().body(atualizada);
	           }).orElse(ResponseEntity.notFound().build());
	}
}