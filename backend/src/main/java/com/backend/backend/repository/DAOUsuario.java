package com.backend.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.Usuario;

@Repository
public interface DAOUsuario extends JpaRepository<Usuario, Long> {
	Usuario findBycpfUsuario(String cpfUsuario); // Personalizando uma query usando o nome do atributo desejado
}
