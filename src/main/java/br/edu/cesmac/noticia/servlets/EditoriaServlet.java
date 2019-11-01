package br.edu.cesmac.noticia.servlets;
// https://www.javaguides.net/2019/03/jsp-servlet-jdbc-mysql-crud-example-tutorial.html

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.cesmac.noticia.dao.EditoriaDao;
import br.edu.cesmac.noticia.model.Editoria;

@WebServlet("/editoria")
public class EditoriaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EditoriaDao editoriaDao;

	public void init() {
		editoriaDao = new EditoriaDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();

		try {
			if (acao.equals("adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("altera")) {
				// busca a lista no DAO
				// despacha para um jsp
			} else if (acao.equals("remove")) {
				// busca a lista no DAOhttps://www.javaguides.net/2019/03/jsp-servlet-jdbc-mysql-crud-example-tutorial.html
				// despacha para um jsp				
			} else {
				lista(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void adiciona(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Editoria editoria = new Editoria();
		editoria.setNome(request.getParameter("nome"));
		try {
			editoriaDao.adiciona(editoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/editoria/mostra.jsp");

		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

	}

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Editoria> editorias = editoriaDao.getLista();
        request.setAttribute("editorias", editorias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editoria/consulta.jsp");
        dispatcher.forward(request, response);		

	}

	private void showAltera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

	}

	private void showEdita(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

	}

}
