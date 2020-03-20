package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Almoco;

@Repository
public interface DAOAlmoco extends JpaRepository<Almoco, Long> {
	
}