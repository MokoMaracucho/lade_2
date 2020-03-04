package lade.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lade.beans.BN_ReservationTopo;
import lade.beans.BN_Topo;
import lade.beans.BN_Utilisateur;
import lade.dao.DAO_ReservationTopo;
import lade.dao.DAO_Topo;
import lade.enums.ENUM_StatutReservationTopo;
import lade.dao.DAO_Exception;

public class FORM_TraitementDemandeReservationTopo {
	
	public static final String ATT_SESSION_UTILISATEUR 					= "sessionUtilisateur";
	
	public static final String CHAMP_NOM_TOPO	 						= "nomTopo";
	public static final String CHAMP_ID_PROPRIETAIRE_TOPO				= "idProprietaireTopo";
	public static final String CHAMP_ID_DEMANDEUR_RESERVATION_TOPO		= "idDemandeurReservationTopo";
	public static final String CHAMP_STATUT_DEMANDE_RESERVATION_TOPO	= "statutDemandeReservationTopo";
	
	String resultatDemandeReservationTopo;
	
	Map<String, String> erreursDemandeReservationTopo = new HashMap<String, String>();
	
	private DAO_Topo daoTopo;
	private DAO_ReservationTopo daoReservationTopo;
	
	public FORM_TraitementDemandeReservationTopo(DAO_Topo daoTopo, DAO_ReservationTopo daoReservationTopo) {
		
		this.daoTopo = daoTopo;
		this.daoReservationTopo = daoReservationTopo;
	}
	
	public String getResultatDemandeReservationTopo() {
		return resultatDemandeReservationTopo;
	}

	public void setResultatDemandeReservationTopo(String resultatDemandeReservationTopo) {
		this.resultatDemandeReservationTopo = resultatDemandeReservationTopo;
	}

	public Map<String, String> getErreursDemandeReservationTopo() {
		return erreursDemandeReservationTopo;
	}

	public BN_ReservationTopo traitementDemandeReservationTopo(HttpServletRequest request, HttpSession session) {

		String nomTopo 					= getValeurChampTexteFormulaire(request, CHAMP_NOM_TOPO);
		
		BN_Utilisateur utilisateur  	= (BN_Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR);
		
		Long idDemandeurReservationTopo = utilisateur.getIdUtilisateur();
		
		BN_ReservationTopo nouvelleReservationTopo = new BN_ReservationTopo();
			
		try {
			
			traitementNomTopo(nomTopo, nouvelleReservationTopo);
			traitementIdDemandeurReservationTopo(idDemandeurReservationTopo, nouvelleReservationTopo);
			traitementStatutDemandeReservationTopo(nouvelleReservationTopo);
			
			if(erreursDemandeReservationTopo.isEmpty()) {
				
				daoReservationTopo.insertionReservationTopo(nouvelleReservationTopo);

				resultatDemandeReservationTopo = "Succés de l'ajout du site.";
			
			} else {
				
				resultatDemandeReservationTopo = "Échec de l'ajout du site.";
			}
			
		} catch (DAO_Exception e) {
			
			resultatDemandeReservationTopo = "Échec de l'ajout du site : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return nouvelleReservationTopo;
	}
	
	private void traitementNomTopo(String nomTopo, BN_ReservationTopo nouvelleReservationTopo) {
			
		BN_Topo topo = daoTopo.selectionTopoParNom(nomTopo);
		
		Long idTopo = topo.getIdTopo();
		
		nouvelleReservationTopo.setIdTopo(idTopo);
		
		Long idProprietaireTopo = topo.getIdProprietaireTopo();
		
		nouvelleReservationTopo.setIdProprietaireTopo(idProprietaireTopo);
	}
	
	private void traitementIdDemandeurReservationTopo(Long idDemandeurReservationTopo, BN_ReservationTopo nouvelleReservationTopo) {
		
		nouvelleReservationTopo.setIdDemandeurReservationTopo(idDemandeurReservationTopo);
	}
	
	private void traitementStatutDemandeReservationTopo(BN_ReservationTopo nouvelleReservationTopo) {
		
		String statutReservationTopo = ENUM_StatutReservationTopo.ATTENTE.toString();
		
		nouvelleReservationTopo.setStatutReservationTopo(statutReservationTopo);
	}
	
	private static String getValeurChampTexteFormulaire(HttpServletRequest request, String nomChampTexteFormulaire) {
		
		String valeurChampTexteFormulaire = request.getParameter(nomChampTexteFormulaire);
		
		if(valeurChampTexteFormulaire == null || valeurChampTexteFormulaire.trim().length() == 0) {
			
			return null;
		
		} else {
			
			return valeurChampTexteFormulaire;
		}
	}

	public void setErreursAjoutSite(String nomChampFormulaire, String message) {

		erreursDemandeReservationTopo.put(nomChampFormulaire, message);
	}
}
