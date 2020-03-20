package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Lanche;

@Repository
public interface DAOLanche extends JpaRepository<Lanche, Long> {
	
}