package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Atendente;
import com.backend.backend.models.Chefia;

@Repository
public interface DAOAtendente extends JpaRepository<Atendente, Long> {
	Atendente findByidUsuario(Long idUsuario);
}