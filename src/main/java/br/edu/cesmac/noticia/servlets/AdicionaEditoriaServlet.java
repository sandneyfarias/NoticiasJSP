package br.edu.cesmac.noticia.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.cesmac.noticia.dao.EditoriaDao;
import br.edu.cesmac.noticia.model.Editoria;

@WebServlet("/editoria/adiciona")
public class AdicionaEditoriaServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set the default response content type and encoding
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		Editoria editoria = new Editoria();

		try {
			editoria.setNome(nome);

			EditoriaDao dao = new EditoriaDao();
			dao.adiciona(editoria);
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		out.println("<html>");
		out.println("<body>");
		out.println("Editoria " + editoria.getNome() + " adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
	}

}
