package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.CafeDaManha;

@Repository
public interface DAOCafeDaManha extends JpaRepository<CafeDaManha, Long> {
	
}