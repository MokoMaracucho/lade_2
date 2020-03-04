package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Site;
import lade.dao.DAO_Commentaire;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Site;
import lade.formulaires.FORM_TraitementFormulaireAjoutSite;

@WebServlet("/AjoutSite")
public class SRV_AjoutSite extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_AJOUT_SITE 							= "/restreint/jsp_ajout_site.jsp";
	public static final String URL_ACCUEIL 								= "/lade/Accueil";

	public static final String ATT_TRAITEMENT_FORMULAIRE_AJOUT_SITE 	= "traitementFormulaireAjoutSite";
	public static final String ATT_NOUVEAU_SITE 						= "nouveauSite";
	public static final String ATT_SESSION_UTILISATEUR					= "sessionUtilisateur";

	public static final String CONFIGURATION_DAO_FACTORY 				= "daoFactory";
	
	private DAO_Site daoSite;
	private DAO_Commentaire daoCommentaire;
    
    public void init() throws ServletException {

        this.daoSite = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSite();
        this.daoCommentaire = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoCommentaire();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_SITE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FORM_TraitementFormulaireAjoutSite traitementFormulaireAjoutSite = new FORM_TraitementFormulaireAjoutSite(daoSite, daoCommentaire);
		
		HttpSession session = request.getSession();
		
		BN_Site nouveauSite = traitementFormulaireAjoutSite.traitementFormulaireAjoutSite(request, session);
		
		if(traitementFormulaireAjoutSite.getErreursAjoutSite().isEmpty()) {

	        response.sendRedirect(URL_ACCUEIL);
		
		} else {
			
			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_AJOUT_SITE, 	traitementFormulaireAjoutSite);
			request.setAttribute(ATT_NOUVEAU_SITE,		 				nouveauSite);
			
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_SITE).forward(request, response);
		}
	}
}
