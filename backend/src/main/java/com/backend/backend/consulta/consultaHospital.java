package com.backend.backend.consulta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultaHospital {

	 private final String url = "jdbc:postgresql://localhost:5432/hospital";
	 private final String user = "sgrhu";
	 private final String password = "sgrhu";
	
	 public Connection connect() throws SQLException {
		 return DriverManager.getConnection(url, user, password);
	 }

}