package br.edu.cesmac.noticia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:15432/noticiasSI", "postgres", "Postgres2019!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
