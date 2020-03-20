package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Autorizacao;
import com.backend.backend.models.Usuario;

@Repository
public interface DAOAutorizacao extends JpaRepository<Autorizacao, Long> {
	Autorizacao findBycpfAutorizado(String cpfAutorizado); // Personalizando uma query usando o nome do atributo desejado
}