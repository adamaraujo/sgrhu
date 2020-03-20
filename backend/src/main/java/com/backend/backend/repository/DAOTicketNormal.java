package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.TicketNormal;

@Repository
public interface DAOTicketNormal extends JpaRepository<TicketNormal, Long> {
	
}