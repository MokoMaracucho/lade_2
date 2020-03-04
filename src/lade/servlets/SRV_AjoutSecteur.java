package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Secteur;
import lade.beans.BN_SiteInnerJoin;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Secteur;
import lade.dao.DAO_Site;
import lade.formulaires.FORM_TraitementFormulaireAjoutSecteur;

@WebServlet("/AjoutSecteur")
public class SRV_AjoutSecteur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_AJOUT_SITE 							= "/restreint/jsp_ajout_secteur.jsp";
	public static final String URL_ACCUEIL 								= "/lade/Accueil";

	public static final String ATT_TRAITEMENT_FORMULAIRE_AJOUT_SECTEUR 	= "traitementFormulaireAjoutSecteur";
	public static final String ATT_SESSION_UTILISATEUR					= "sessionUtilisateur";
	public static final String ATT_NOUVEAU_SECTEUR 						= "nouveauSecteur";
	public static final String ATT_LISTE_SITES							= "listeSites";

	public static final String CONFIGURATION_DAO_FACTORY 				= "daoFactory";
	
	private DAO_Site daoSite;
	private DAO_Secteur daoSecteur;
    
    public void init() throws ServletException {

    	this.daoSite = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSite();
        this.daoSecteur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSecteur();
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BN_SiteInnerJoin> listeSites = daoSite.selectionSites();
		
		request.setAttribute(ATT_LISTE_SITES, listeSites);
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_SITE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		FORM_TraitementFormulaireAjoutSecteur traitementFormulaireAjoutSecteur = new FORM_TraitementFormulaireAjoutSecteur(daoSite, daoSecteur);
		
		BN_Secteur nouveauSecteur = traitementFormulaireAjoutSecteur.traitementFormulaireAjoutSecteur(request, session);
		
		if(traitementFormulaireAjoutSecteur.getErreursAjoutSecteur().isEmpty()) {

	        response.sendRedirect(URL_ACCUEIL);
		
		} else {
			
			List<BN_SiteInnerJoin> listeSites = daoSite.selectionSites();
			
			request.setAttribute(ATT_LISTE_SITES, listeSites);
			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_AJOUT_SECTEUR, 	traitementFormulaireAjoutSecteur);
			request.setAttribute(ATT_NOUVEAU_SECTEUR,		 				nouveauSecteur);
			
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_SITE).forward(request, response);
		}
	}
}
