package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_ReservationTopoInnerJoin;
import lade.beans.BN_Utilisateur;
import lade.dao.DAO_Factory;
import lade.dao.DAO_ReservationTopo;
import lade.dao.DAO_Utilisateur;
import lade.formulaires.FORM_TraitementFormulaireConnection;

@WebServlet("/Connection")
public class SRV_Connection extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String VUE_CONNECTION							= "/jsp_connection.jsp";
	public static final String URL_ACCUEIL								= "/lade/Accueil";
	
	public static final String ATT_TRAITEMENT_FORMULAIRE_CONNECTION 	= "traitementFormulaireConnection";
	public static final String ATT_UTILISATEUR							= "utilisateur";
	public static final String ATT_SESSION_UTILISATEUR					= "sessionUtilisateur";
	public static final String ATT_LISTE_DEMANDES_RESERVATION_TOPO		= "listeDemandesReservationTopo";
 	
 	public static final String CONFIGURATION_DAO_FACTORY 				= "daoFactory";
	
	private DAO_Utilisateur daoUtilisateur;
	private DAO_ReservationTopo daoReservationTopo;
    
    public void init() throws ServletException {

        this.daoUtilisateur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoUtilisateur();
        this.daoReservationTopo = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoReservationTopo();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE_CONNECTION).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FORM_TraitementFormulaireConnection traitementFormulaireConnection = new FORM_TraitementFormulaireConnection(daoUtilisateur); 

		BN_Utilisateur utilisateur = traitementFormulaireConnection.traitementFormulaireConnection(request);
		
		HttpSession session = request.getSession();
		
		if(traitementFormulaireConnection.getErreursConnection().isEmpty()) {
			
			session.setAttribute(ATT_SESSION_UTILISATEUR, utilisateur);
			
			Long idProprietaireTopo = utilisateur.getIdUtilisateur();
			
			List<BN_ReservationTopoInnerJoin> listeDemandesReservationTopo = daoReservationTopo.selectionDemandesReservationTopoInnerJoinParIdProprietaire(idProprietaireTopo);
			
			request.setAttribute(ATT_LISTE_DEMANDES_RESERVATION_TOPO, listeDemandesReservationTopo);
			session.setAttribute(ATT_SESSION_UTILISATEUR, utilisateur);
		    
			response.sendRedirect(URL_ACCUEIL);
		
		} else {
			
			request.setAttribute(ATT_TRAITEMENT_FORMULAIRE_CONNECTION, 	traitementFormulaireConnection);
			request.setAttribute(ATT_UTILISATEUR, 						utilisateur);
			
			session.setAttribute(ATT_SESSION_UTILISATEUR, 				null);
			
			this.getServletContext().getRequestDispatcher(VUE_CONNECTION).forward(request, response);
		}
	}
}
