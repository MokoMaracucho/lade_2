package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjoutSite")
public class SRV_AjoutSite extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String VUE_AJOUT_SITE 							= "/restreint/jsp_ajout_site.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE_AJOUT_SITE).forward(request, response);
	}
}
