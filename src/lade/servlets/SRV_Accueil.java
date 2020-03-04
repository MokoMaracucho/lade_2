package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Accueil")
public class SRV_Accueil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String VUE_ACCUEIL = "/jsp_accueil.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(request, response);
	}
}
