package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_Voie;
import lade.beans.BN_LongueurInnerJoin;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Voie;
import lade.dao.DAO_Longueur;

@WebServlet("/ListeLongueurs")
public class SRV_ListeLongueurs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String VUE_LISTE_LONGUEURS				= "/jsp_liste_longueurs.jsp";
	
	public static final String ATT_LISTE_LONGUEURS 				= "listeLongueurs";

	public static final String CONFIGURATION_DAO_FACTORY 		= "daoFactory";
	
	private DAO_Voie daoVoie;
	private DAO_Longueur daoLongueur;
    
    public void init() throws ServletException {

        this.daoVoie = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoVoie();
        this.daoLongueur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoLongueur();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomVoie = request.getParameter("nom");
		
		if(nomVoie == null) {
		
			List<BN_LongueurInnerJoin> listeLongueurs = daoLongueur.selectionLongueurs();
			
			request.setAttribute(ATT_LISTE_LONGUEURS, listeLongueurs);
			
		} else {
			
			BN_Voie secteur = daoVoie.selectionVoieParNom(nomVoie);
			
			Long idVoie = secteur.getIdVoie();
			System.out.println(idVoie);
			
			List<BN_LongueurInnerJoin> listeLongueurs = daoLongueur.selectionLongueursParIdVoie(idVoie);
			
			request.setAttribute(ATT_LISTE_LONGUEURS, listeLongueurs);
		}
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_LONGUEURS).forward(request, response);
	}
}
