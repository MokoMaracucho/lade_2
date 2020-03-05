package lade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lade.beans.BN_ReservationTopo;
import lade.dao.DAO_ReservationTopo;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Topo;
import lade.formulaires.FORM_TraitementDemandeReservationTopo;

@WebServlet("/DemandeReservationTopo")
public class SRV_DemandeReservationTopo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String VUE_RESULTAT_DEMANDE_RESERVATION_TOPO			= "/restreint/jsp_resultat_demande_reservation.jsp";
	
	public static final String ATT_NOM_TOPO 									= "nomTopo";
	public static final String ATT_NOUVELLE_RESERVATION_TOPO					= "nouvelleReservationTopo";
	public static final String ATT_UTILISATEUR									= "utilisateur";
	public static final String ATT_SESSION_UTILISATEUR							= "sessionUtilisateur";

	public static final String CONFIGURATION_DAO_FACTORY 						= "daoFactory";
	
	private DAO_Topo daoTopo;
	private DAO_ReservationTopo daoReservationTopo;
    
    public void init() throws ServletException {

        this.daoTopo = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoTopo();
        this.daoReservationTopo = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoReservationTopo();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FORM_TraitementDemandeReservationTopo traitementDemandeReservationTopo = new FORM_TraitementDemandeReservationTopo(daoTopo, daoReservationTopo); 
		
		HttpSession session = request.getSession();
		
		String nomTopo = request.getParameter("nomTopo");
		
		request.setAttribute(ATT_NOM_TOPO, nomTopo);
		
		BN_ReservationTopo nouvelleReservationTopo = traitementDemandeReservationTopo.traitementDemandeReservationTopo(request, session);
		
		request.setAttribute(ATT_NOUVELLE_RESERVATION_TOPO, nouvelleReservationTopo);
		
		this.getServletContext().getRequestDispatcher(VUE_RESULTAT_DEMANDE_RESERVATION_TOPO).forward(request, response);
	}
}
