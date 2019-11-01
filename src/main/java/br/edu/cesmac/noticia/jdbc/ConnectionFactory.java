package br.edu.cesmac.noticia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/noticiasSI", "postgres", "postgres");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
