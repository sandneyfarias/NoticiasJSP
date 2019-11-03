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

@WebServlet(urlPatterns = {"/editoria", "/editoria/", "/editoria/nova", "/editoria/adiciona", "/editoria/remove", "/editoria/altera", "/editoria/edita"})
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
			if (acao.equals("/editoria/adiciona")) {
				adiciona(request, response);
			} else if (acao.equals("/editoria/altera")) {
				altera(request, response);				
			} else if (acao.equals("/editoria/nova")) {
				nova(request, response);
			} else if (acao.equals("/editoria/edita")) {
				edita(request, response);				
			} else if (acao.equals("/editoria/remove")) {
				remove(request, response);
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
			e.printStackTrace();
		}
		response.sendRedirect("/editoria");
	}

	private void altera(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Editoria editoria = new Editoria();
		editoria.setIdEditoria(Integer.parseInt(request.getParameter("id")));
		editoria.setNome(request.getParameter("nome"));

		editoriaDao.altera(editoria);
		response.sendRedirect("/editoria");
	}

	private void nova(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Editoria editoria = new Editoria();
		request.setAttribute("editoria", editoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editoria/manter.jsp");	
		dispatcher.forward(request, response);	
	}
	
	private void edita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Editoria editoria = new Editoria();
		int id = Integer.parseInt(request.getParameter("id"));

		editoria = editoriaDao.getById(id);
        request.setAttribute("editoria", editoria);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/editoria/manter.jsp");	
		dispatcher.forward(request, response);	
	}	

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Editoria editoria = new Editoria();
		editoria.setIdEditoria(Integer.parseInt(request.getParameter("id")));

		editoriaDao.remove(editoria);
		response.sendRedirect("/editoria");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <Editoria> editorias = editoriaDao.getLista();
        request.setAttribute("editorias", editorias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editoria/consulta.jsp");
        dispatcher.forward(request, response);		

	}

}
