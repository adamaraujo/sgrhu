package com.backend.backend.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Ticket;

@Repository
public interface DAOTicket extends JpaRepository<Ticket, Long> {
	List<Ticket> findByCpfClienteAndNomeRefeicaoAndDataConsumo(String cpfCliente, String nomeRefeicao, Date dataConsumo);
	List<Ticket> findBycpfCliente(String cpfCliente);
}