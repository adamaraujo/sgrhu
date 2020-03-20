package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Chefia;
import com.backend.backend.models.Gestor;

@Repository
public interface DAOGestor extends JpaRepository<Gestor, Long> {
	Gestor findByidUsuario(Long idUsuario);
}