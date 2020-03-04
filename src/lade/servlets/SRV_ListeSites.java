package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_SiteInnerJoin;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Site;

@WebServlet("/ListeSites")
public class SRV_ListeSites extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String VUE_LISTE_SITES				= "/jsp_liste_sites.jsp";
	
	public static final String ATT_LISTE_SITES 				= "listeSites";

	public static final String CONFIGURATION_DAO_FACTORY 	= "daoFactory";
	
	private DAO_Site daoSite;
    
    public void init() throws ServletException {

        this.daoSite = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSite();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BN_SiteInnerJoin> listeSites = daoSite.selectionSites();
		
		request.setAttribute(ATT_LISTE_SITES, listeSites);
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_SITES).forward(request, response);
	}
}
