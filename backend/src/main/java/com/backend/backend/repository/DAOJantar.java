package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Jantar;

@Repository
public interface DAOJantar extends JpaRepository<Jantar, Long> {
	
}