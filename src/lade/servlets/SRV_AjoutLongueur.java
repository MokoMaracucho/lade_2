package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_VoieInnerJoin;
import lade.beans.BN_Utilisateur;
import lade.beans.BN_Longueur;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Voie;
import lade.dao.DAO_Longueur;
import lade.formulaires.FORM_TraitementFormulaireAjoutLongueur;

@WebServlet("/AjoutLongueur")
public class SRV_AjoutLongueur extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String VUE_AJOUT_LONGUEUR 							= "/restreint/jsp_ajout_longueur.jsp";
	public static final String URL_ACCUEIL 									= "/lade/Accueil";

	public static final String ATT_TRAITEMENT_FORMULAIRE_AJOUT_LONGUEUR 	= "traitementFormulaireAjoutLongueur";
	public static final String ATT_NOUVELLE_LONGUEUR						= "nouvelleLongueur";
	public static final String ATT_LISTE_VOIES 								= "listeVoies";
	public static final String ATT_SESSION_UTILISATEUR				 		= "sessionUtilisateur";

	public static final String CONFIGURATION_DAO_FACTORY 					= "daoFactory";
	
	private DAO_Voie daoVoie;
	private DAO_Longueur daoLongueur;
    
    public void init() throws ServletException {

        this.daoVoie = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoVoie();
        this.daoLongueur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoLongueur();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BN_VoieInnerJoin> listeVoies = daoVoie.selectionVoies();
		
		request.setAttribute(ATT_LISTE_VOIES, listeVoies);
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_LONGUEUR).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FORM_TraitementFormulaireAjoutLongueur traitementFormulaireAjoutLongueur = new FORM_TraitementFormulaireAjoutLongueur(daoVoie, daoLongueur);
		
		HttpSession session = request.getSession();
		
		BN_Utilisateur utilisateur = (BN_Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR);
		
		request.setAttribute(ATT_SESSION_UTILISATEUR, utilisateur);
		
		BN_Longueur nouvelleLongueur = traitementFormulaireAjoutLongueur.traitementFormulaireAjoutLongueur(request);
		
		if(traitementFormulaireAjoutLongueur.getErreursAjoutLongueur().isEmpty()) {

	        response.sendRedirect(URL_ACCUEIL);
		
		} else {
			
			List<BN_VoieInnerJoin> listeVoies = daoVoie.selectionVoies();
			
			request.setAttribute(ATT_LISTE_VOIES, 							listeVoies);
			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_AJOUT_LONGUEUR, 	traitementFormulaireAjoutLongueur);
			request.setAttribute(ATT_NOUVELLE_LONGUEUR,		 				nouvelleLongueur);
			
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_LONGUEUR).forward(request, response);
		}
	}
}
