package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Utilisateur;
import lade.formulaires.FORM_TraitementFormulaireInscription;

@WebServlet("/Inscription")
public class SRV_Inscription extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String VUE_INSCRIPTION 							= "/jsp_inscription.jsp";
	public static final String URL_ACCUEIL								= "/lade/Accueil";

	public static final String ATT_TRAITEMENT_FORMULAIRE_INSCRIPTION 	= "traitementFormulaireInscription";
	public static final String ATT_NOUVEL_UTILISATEUR 					= "nouvelUtilisateur";
 	public static final String ATT_SESSION_UTILISATEUR					= "sessionUtilisateur";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		FORM_TraitementFormulaireInscription traitementFormulaireInscription = new FORM_TraitementFormulaireInscription();
		
		BN_Utilisateur nouvelUtilisateur = traitementFormulaireInscription.traitementFormulaireInscription(request);
		
		HttpSession session = request.getSession();
		
		if(traitementFormulaireInscription.getErreursInscription().isEmpty()) {
			
			session.setAttribute(ATT_SESSION_UTILISATEUR, nouvelUtilisateur);

	        response.sendRedirect(URL_ACCUEIL);
		
		} else {
			
			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_INSCRIPTION, traitementFormulaireInscription);
			request.setAttribute(ATT_NOUVEL_UTILISATEUR, 				nouvelUtilisateur);
			
			session.setAttribute(ATT_SESSION_UTILISATEUR, null);
			
			this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);
		}
    }
}
