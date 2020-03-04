package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_TopoInnerJoin;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Topo;

@WebServlet("/ListeTopos")
public class SRV_ListeTopos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String VUE_LISTE_TOPOS				= "/jsp_liste_topos.jsp";
	
	public static final String ATT_LISTE_TOPOS 				= "listeTopos";

	public static final String CONFIGURATION_DAO_FACTORY 	= "daoFactory";
	
	private DAO_Topo daoTopo;
    
    public void init() throws ServletException {

        this.daoTopo = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoTopo();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BN_TopoInnerJoin> listeTopos = daoTopo.selectionToposInnerJoin();
		
		request.setAttribute(ATT_LISTE_TOPOS, listeTopos);
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_TOPOS).forward(request, response);
	}
}
