package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RechercheAvancee")
public class SRV_RechercheAvancee extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String VUE_RECHERCHE_AVANCEE 					= "/jsp_recherche_avancee.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_RECHERCHE_AVANCEE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		this.getServletContext().getRequestDispatcher(VUE_RECHERCHE_AVANCEE).forward(request, response);
	}
}
