package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_Utilisateur;
import lade.formulaires.FORM_TraitementFormulaireInscription;

@WebServlet("/Inscription")
public class SRV_Inscription extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String VUE_INSCRIPTION 							= "/WEB-INF/jsp_inscription.jsp";

	public static final String ATT_TRAITEMENT_FORMULAIRE_INSCRIPTION 	= "traitementFormulaireInscription";
	public static final String ATT_NOUVEL_UTILISATEUR 					= "nouvelUtilisateur";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		FORM_TraitementFormulaireInscription traitementFormulaireInscription = new FORM_TraitementFormulaireInscription();
		
		BN_Utilisateur nouvelUtilisateur = traitementFormulaireInscription.traitementFormulaireInscription(request);
		
		request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_INSCRIPTION, traitementFormulaireInscription);
		request.setAttribute(ATT_NOUVEL_UTILISATEUR, 				nouvelUtilisateur);
		
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);
    }
}
