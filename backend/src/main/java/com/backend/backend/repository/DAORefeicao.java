package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Refeicao;

@Repository
public interface DAORefeicao extends JpaRepository<Refeicao, Long> {
	
}