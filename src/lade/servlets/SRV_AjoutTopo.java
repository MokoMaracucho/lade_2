package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Topo;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Topo;
import lade.formulaires.FORM_TraitementFormulaireAjoutTopo;

@WebServlet("/AjoutTopo")
public class SRV_AjoutTopo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String VUE_AJOUT_TOPO 							= "/restreint/jsp_ajout_topo.jsp";
	public static final String URL_ACCUEIL 								= "/lade/Accueil";

	public static final String ATT_TRAITEMENT_FORMULAIRE_AJOUT_TOPO 	= "traitementFormulaireAjoutTopo";
	public static final String ATT_NOUVEAU_TOPO 						= "nouveauTopo";
	public static final String ATT_SESSION_UTILISATEUR					= "sessionUtilisateur";
	public static final String ATT_LISTE_SITES							= "listeSites";
	
	public static final String CONFIGURATION_DAO_FACTORY 				= "daoFactory";

	private DAO_Topo daoTopo;
    
    public void init() throws ServletException {

        this.daoTopo = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoTopo();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_TOPO).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FORM_TraitementFormulaireAjoutTopo traitementFormulaireAjoutTopo = new FORM_TraitementFormulaireAjoutTopo(daoTopo);
		
		HttpSession session = request.getSession();
		
		BN_Topo nouveauTopo = traitementFormulaireAjoutTopo.traitementFormulaireAjoutTopo(request, session);
		
		if(traitementFormulaireAjoutTopo.getErreursAjoutTopo().isEmpty()) {

	        response.sendRedirect(URL_ACCUEIL);
		
		} else {

			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_AJOUT_TOPO, 	traitementFormulaireAjoutTopo);
			request.setAttribute(ATT_NOUVEAU_TOPO,		 				nouveauTopo);
			
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_TOPO).forward(request, response);
		}
	}
}
