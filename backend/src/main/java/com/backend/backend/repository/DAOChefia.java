package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Chefia;
import com.backend.backend.models.Usuario;

@Repository
public interface DAOChefia extends JpaRepository<Chefia, Long> {
	Chefia findByidUsuario(Long idUsuario);
}