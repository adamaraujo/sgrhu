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

import com.backend.backend.repository.DAOUsuario;
import com.backend.backend.repository.DAOGestor;
import com.backend.backend.repository.DAOAnalista;
import com.backend.backend.repository.DAOChefia;
import com.backend.backend.repository.DAOAtendente;
import com.backend.backend.models.Usuario;
import com.backend.backend.models.Atendente;
import com.backend.backend.models.Analista;
import com.backend.backend.models.Chefia;
import com.backend.backend.models.Gestor;

@RestController // Indica que a classe é do tipo Controller
@RequestMapping("/usuarios") // Indica a rota de acesso
public class UsuarioController {
	private DAOUsuario daoUser;
	private DAOAtendente daoAtendente;
	private DAOGestor daoGestor;
	private DAOChefia daoChefia;
	private DAOAnalista daoAnalista;
	
	@Autowired
	UsuarioController (DAOUsuario usuarioRepository, DAOAtendente atendenteRepository, DAOChefia chefiaRepository, DAOAnalista analistaRepository, DAOGestor gestorRepository) {
		this.daoUser = usuarioRepository;
		this.daoAnalista = analistaRepository;
		this.daoGestor = gestorRepository;
		this.daoChefia = chefiaRepository;
		this.daoAtendente = atendenteRepository;
	}
	
	// Criando novo usuário
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Usuario criar(@RequestBody Usuario usuario) {
		Usuario usuarioExiste = daoUser.findBycpfUsuario(usuario.getCpfUsuario());
		if (usuarioExiste != null) {
			return usuarioExiste; // Retorna o usuário já existente
		}
		else {
			Usuario gerado = daoUser.save(usuario); // Salva o novo usuário
			
			int nivelAcesso = usuario.getNivelAcesso();
			Long id = gerado.getIdUsuario();
			adicionaFuncao(nivelAcesso, id);
			return gerado; 
		}
	}
	
	// Função para adicionar as entidades de funções no banco de dados, usada em POST e PUT
	public void adicionaFuncao (int nivelAcesso, Long id) {
		switch (nivelAcesso) {
			case 1:
				Gestor gestor = new Gestor();
				gestor.setIdUsuario(id);
				daoGestor.save(gestor);
				break;
			case 2: 
				Chefia chefia = new Chefia();
				chefia.setIdUsuario(id);
				daoChefia.save(chefia);
				break;
			case 3: 
				Analista analista = new Analista();
				analista.setIdUsuario(id);
				daoAnalista.save(analista);
				break;
			case 4: 
				Atendente atendente = new Atendente();
				atendente.setIdUsuario(id);
				daoAtendente.save(atendente);
				break;
			}
	}
	
	public void removeFuncao (int nivelAcesso, Long id) {
		switch (nivelAcesso) {
	   		case 1:
	   			Gestor gestorKey = daoGestor.findByidUsuario(id);
	   			daoGestor.deleteById(gestorKey.getIdGestor());
	   			break;
	   		case 2:
	   			Chefia chefiaKey = daoChefia.findByidUsuario(id);
	   			daoChefia.deleteById(chefiaKey.getIdChefia());
	   			break;
	   		case 3:
	   			Analista analistaKey = daoAnalista.findByidUsuario(id);
	   			daoAnalista.deleteById(analistaKey.getIdAnalista());
	   			break;
	   		case 4:
	   			Atendente atendenteKey = daoAtendente.findByidUsuario(id);
	   			daoAtendente.deleteById(atendenteKey.getIdAtendente());
	   			break;
		}
	}
	
	// Busca por usuário específico usando seu id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity excluir(@PathVariable Long id){
	   return daoUser.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	// Retorna todos os usuários
	@GetMapping // Não precisa indicar o caminho, pois para este caso, será sempre /usuarios
	public List listar(){
	   return daoUser.findAll();
	}
	
	// Deletando um usuário específico usando seu id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable Long id) {
	   return daoUser.findById(id)
	           .map(record -> {
	        	   int nivel = record.getNivelAcesso();
	        	   removeFuncao(nivel, id);
	               daoUser.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	// Alterando dados do usuário
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable("id") Long id,
	                                      @RequestBody Usuario usuario) {
	   
	   return daoUser.findById(id)
	           .map(record -> {
	        	   int nivelAntigo = record.getNivelAcesso();
	        	   
	               record.setCpfUsuario(usuario.getCpfUsuario());
	               record.setPrimeiro_nome(usuario.getPrimeiro_nome());
	               record.setSobrenome(usuario.getSobrenome());
	               record.setNivelAcesso(usuario.getNivelAcesso());
	               record.setLogin(usuario.getLogin());
	               record.setSenha(usuario.getSenha());
	               Usuario atualizado = daoUser.save(record);
	               	               	               
	               int nivelNovo = record.getNivelAcesso();
	               
	               // Responsável por alterar as tabelas de nível de acesso
	               if (nivelNovo != nivelAntigo) {
	            	   switch (nivelAntigo) {
	            	   		case 1:
	            	   			removeFuncao(nivelAntigo, id);
	            	   			adicionaFuncao(nivelNovo, id);
	            	   			break;
	            	   		case 2:
	            	   			removeFuncao(nivelAntigo, id);
	            	   			adicionaFuncao(nivelNovo, id);
	            	   			break;
	            	   		case 3:
	            	   			removeFuncao(nivelAntigo, id);
	            	   			adicionaFuncao(nivelNovo, id);
	            	   			break;
	            	   		case 4:
	            	   			removeFuncao(nivelAntigo, id);
	            	   			adicionaFuncao(nivelNovo, id);
	            	   			break;
	            	   }
	               }
	               return ResponseEntity.ok().body(atualizado);
	           }).orElse(ResponseEntity.notFound().build());
	}
}