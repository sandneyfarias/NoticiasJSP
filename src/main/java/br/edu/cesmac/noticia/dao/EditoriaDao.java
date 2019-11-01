package br.edu.cesmac.noticia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.cesmac.noticia.jdbc.ConnectionFactory;
import br.edu.cesmac.noticia.model.Editoria;

public class EditoriaDao {
	private Connection connection;

	public EditoriaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Editoria editoria) throws SQLException {
		String sql = "INSERT INTO editoria" + " (nome)" + " values (?)";

			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, editoria.getNome());
			stmt.execute();
			stmt.close();
	}

	public void altera(Editoria editoria) {
		String sql = "UPDATE editoria SET " + 
					" nome = ? "
					+ " WHERE idEditoria = ? ";

		try {
			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, editoria.getIdEditoria());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void remove(Editoria editoria) {
		String sql = 	"DELETE FROM editoria " + 
						" WHERE idEditoria = ? ";

		try {
			PreparedStatement stmt;

			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, editoria.getIdEditoria());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	

	public List<Editoria> getLista() {
		List<Editoria> editorias = new ArrayList<Editoria>();

		try {
			String sql = "SELECT idEditoria, nome " 
						+ " FROM editoria " 
						+ " ORDER BY nome";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				int id = resultado.getInt("idEditoria");
				String nome = resultado.getString("nome");

				Editoria editoria = new Editoria();
				editoria.setIdEditoria(id);
				editoria.setNome(nome);

				editorias.add(editoria);
			}

			resultado.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return editorias;
	}

}
