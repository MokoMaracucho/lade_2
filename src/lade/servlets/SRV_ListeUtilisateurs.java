package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_Utilisateur;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Utilisateur;

@WebServlet("/ListeUtilisateurs")
public class SRV_ListeUtilisateurs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String VUE_LISTE_UTILISATEUR		= "/jsp_liste_utilisateurs.jsp";
	
	public static final String ATT_LISTE_UTILISATEURS 		= "listeUtilisateurs";

	public static final String CONFIGURATION_DAO_FACTORY 	= "daoFactory";
	
	private DAO_Utilisateur daoUtilisateur;
    
    public void init() throws ServletException {

        this.daoUtilisateur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoUtilisateur();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BN_Utilisateur> listeUtilisateurs = daoUtilisateur.recuperationListeUtilisateurs();
		
		request.setAttribute(ATT_LISTE_UTILISATEURS, listeUtilisateurs);
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_UTILISATEUR).forward(request, response);
	}
}
