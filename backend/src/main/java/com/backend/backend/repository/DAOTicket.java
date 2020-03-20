package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Ticket;

@Repository
public interface DAOTicket extends JpaRepository<Ticket, Long> {
	
}