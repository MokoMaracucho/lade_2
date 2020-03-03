package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Deconnection")
public class SRV_Deconnection extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String URL_CONNECTION = "/lade/Connection";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    HttpSession session = request.getSession();
	    
	    session.invalidate();
	
	    response.sendRedirect(URL_CONNECTION);
    }
}
