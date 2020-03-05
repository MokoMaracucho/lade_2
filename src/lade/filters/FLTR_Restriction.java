package lade.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FLTR_Restriction implements Filter  {
	
	public static final String ACCES_CONNECTION 						= "/jsp_connection.jsp";

 	public static final String ATT_SESSION_UTILISATEUR					= "sessionUtilisateur";
	
    public void init(FilterConfig config) throws ServletException {
    	
    }

    public void doFilter(ServletRequest att_request, ServletResponse att_response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request 		= (HttpServletRequest) att_request;
        HttpServletResponse response	= (HttpServletResponse) att_response;

        HttpSession session = request.getSession();

        if (session.getAttribute(ATT_SESSION_UTILISATEUR) == null) {
        	
    	    response.sendRedirect(request.getContextPath() + ACCES_CONNECTION);
    	    
        } else {

            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    	
    }
}
