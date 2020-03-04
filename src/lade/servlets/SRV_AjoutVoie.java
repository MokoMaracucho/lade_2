package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_SecteurInnerJoin;
import lade.beans.BN_Utilisateur;
import lade.beans.BN_Voie;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Secteur;
import lade.dao.DAO_Voie;
import lade.formulaires.FORM_TraitementFormulaireAjoutVoie;

@WebServlet("/AjoutVoie")
public class SRV_AjoutVoie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String VUE_AJOUT_VOIE 							= "/restreint/jsp_ajout_voie.jsp";
	public static final String URL_ACCUEIL 								= "/lade/Accueil";

	public static final String ATT_TRAITEMENT_FORMULAIRE_AJOUT_VOIE 	= "traitementFormulaireAjoutVoie";
	public static final String ATT_NOUVELLE_VOIE						= "nouvelleVoie";
	public static final String ATT_LISTE_SECTEURS 						= "listeSecteurs";
	public static final String ATT_SESSION_UTILISATEUR				 	= "sessionUtilisateur";
	
	public static final String CONFIGURATION_DAO_FACTORY 				= "daoFactory";
	
	private DAO_Secteur daoSecteur;
	private DAO_Voie daoVoie;
    
    public void init() throws ServletException {

        this.daoSecteur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSecteur();
        this.daoVoie = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoVoie();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BN_SecteurInnerJoin> listeSecteurs = daoSecteur.selectionSecteurs();
		
		request.setAttribute(ATT_LISTE_SECTEURS, listeSecteurs);
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_VOIE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FORM_TraitementFormulaireAjoutVoie traitementFormulaireAjoutVoie = new FORM_TraitementFormulaireAjoutVoie(daoSecteur, daoVoie);
		
		HttpSession session = request.getSession();
		
		BN_Utilisateur utilisateur = (BN_Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR);
		
		request.setAttribute(ATT_SESSION_UTILISATEUR, utilisateur);
		
		BN_Voie nouvelleVoie = traitementFormulaireAjoutVoie.traitementFormulaireAjoutVoie(request);
		
		if(traitementFormulaireAjoutVoie.getErreursAjoutVoie().isEmpty()) {

	        response.sendRedirect(URL_ACCUEIL);
		
		} else {
			
			List<BN_SecteurInnerJoin> listeSecteurs = daoSecteur.selectionSecteurs();
			
			request.setAttribute(ATT_LISTE_SECTEURS, 					listeSecteurs);
			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_AJOUT_VOIE, 	traitementFormulaireAjoutVoie);
			request.setAttribute(ATT_NOUVELLE_VOIE,		 				nouvelleVoie);
			
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_VOIE).forward(request, response);
		}
	}
}
