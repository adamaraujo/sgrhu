package com.backend.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.repository.DAOUsuario;
import com.backend.backend.models.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private DAOUsuario repository;
	
	UsuarioController (DAOUsuario usuarioRepository) {
		this.repository = usuarioRepository;
	}
	
	// Criando novo usuário
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Usuario criar(@RequestBody Usuario usuario) {
		Usuario usuarioExiste = repository.findBycpfUsuario(usuario.getCpfUsuario());
		if (usuarioExiste != null) {
			return usuarioExiste; // Retorna o usuário já existente
		}
		else {
			return repository.save(usuario); // Salva o novo usuário
		}
	}
	
	// Busca por usuário específico usando seu id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity excluir(@PathVariable Long id){
	   return repository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	// Retorna todos os usuários
	@GetMapping // Não precisa indicar o caminho, pois para este caso, será sempre /usuarios
	public List listar(){
	   return repository.findAll();
	}
	
	// Deletando um usuário específico usando seu id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable Long id) {
	   return repository.findById(id)
	           .map(record -> {
	               repository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	// Alterando dados do usuário
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable("id") Long id,
	                                      @RequestBody Usuario usuario) {
	   return repository.findById(id)
	           .map(record -> {
	               record.setCpfUsuario(usuario.getCpfUsuario());
	               record.setPrimeiro_nome(usuario.getPrimeiro_nome());
	               record.setSobrenome(usuario.getSobrenome());
	               record.setNivelAcesso(usuario.getNivelAcesso());
	               record.setLogin(usuario.getLogin());
	               record.setSenha(usuario.getSenha());
	               Usuario atualizado = repository.save(record);
	               return ResponseEntity.ok().body(atualizado);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
}
