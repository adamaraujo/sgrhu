package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.TicketAvulso;

@Repository
public interface DAOTicketAvulso extends JpaRepository<TicketAvulso, Long> {
	
}