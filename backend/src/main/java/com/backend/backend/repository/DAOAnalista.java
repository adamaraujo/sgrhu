package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Analista;
import com.backend.backend.models.Chefia;

@Repository
public interface DAOAnalista extends JpaRepository<Analista, Long> {
	Analista findByidUsuario(Long idUsuario);
}