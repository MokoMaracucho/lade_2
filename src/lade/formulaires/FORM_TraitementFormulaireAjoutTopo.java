package lade.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Topo;
import lade.beans.BN_Utilisateur;
import lade.dao.DAO_Exception;
import lade.dao.DAO_Topo;

public class FORM_TraitementFormulaireAjoutTopo {
	
	public static final String ATT_SESSION_UTILISATEUR 		= "sessionUtilisateur";
	
	public static final String CHAMP_ID_PROPRIETAIRE_TOPO 	= "idPropietaireTopo";
	public static final String CHAMP_NOM_TOPO 				= "nomTopo";
	public static final String CHAMP_REGION_TOPO			= "regionTopo";
	public static final String CHAMP_DESCRIPTION_TOPO		= "descriptionTopo";
	
	String resultatAjoutTopo;
	
	Map<String, String> erreursAjoutTopo = new HashMap<String, String>();
	
	private DAO_Topo daoTopo;
	
	public FORM_TraitementFormulaireAjoutTopo(DAO_Topo daoTopo) {
		
		this.daoTopo = daoTopo;
	}
	
	public String getResultatAjoutTopo() {
		return resultatAjoutTopo;
	}

	public void setResultatAjoutTopo(String resultatAjoutTopo) {
		this.resultatAjoutTopo = resultatAjoutTopo;
	}

	public Map<String, String> getErreursAjoutTopo() {
		return erreursAjoutTopo;
	}

	public BN_Topo traitementFormulaireAjoutTopo(HttpServletRequest request, HttpSession session) {
		
		BN_Utilisateur proprietaireTopo	 	= (BN_Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR);
		Long idProprietaireTopo 			= proprietaireTopo.getIdUtilisateur();

		String nomTopo 				= getValeurChampTexteFormulaire(request, CHAMP_NOM_TOPO);
		
		String regionTopo 			= getValeurChampTexteFormulaire(request, CHAMP_REGION_TOPO);
		
		String descriptionTopo		= getValeurChampTexteFormulaire(request, CHAMP_DESCRIPTION_TOPO);
		
		BN_Topo nouveauTopo = new BN_Topo();
			
		try {
			
			traitementIdProprietaireTopo(idProprietaireTopo, nouveauTopo);
			traitementNomTopo(nomTopo, nouveauTopo);
			traitementRegionTopo(regionTopo, nouveauTopo);
			traitementDescriptionTopo(descriptionTopo, nouveauTopo);
			traitementDisponibiliteTopo(true, nouveauTopo);
		
			if(erreursAjoutTopo.isEmpty()) {
				
				daoTopo.insertionTopo(nouveauTopo);
				
				resultatAjoutTopo = "Succés de l'ajout du site.";
			
			} else {
				
				resultatAjoutTopo = "Échec de l'ajout du site.";
			}
			
		} catch (DAO_Exception e) {
			
			resultatAjoutTopo = "Échec de l'ajout du site : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return nouveauTopo;
	}
	
	private void traitementIdProprietaireTopo(Long idProprietaireTopo, BN_Topo nouveauTopo) {

		nouveauTopo.setIdProprietaireTopo(idProprietaireTopo);
	}
	
	private void traitementNomTopo(String nomTopo, BN_Topo nouveauTopo) {
		
		try {
			
			validationNomTopo(nomTopo);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutTopo(CHAMP_NOM_TOPO, e.getMessage());
		}
		nouveauTopo.setNomTopo(nomTopo);
	}

	private void validationNomTopo(String nomTopo) throws FORM_Exception {

		if(nomTopo != null) {

			if(nomTopo.length() > 3 && nomTopo.length() < 30) {

				if(nomTopo.matches("^[a-zA-Z0-9 ']+$")) {
					
					if(daoTopo.selectionTopoParNom(nomTopo) != null ) {
	                	
	                    throw new FORM_Exception("Ce topo est déjà existant.");
	                }
				} else {

					throw new FORM_Exception("Le nom du topo ne doit comporter que des lettres.");
				}
			} else {

				throw new FORM_Exception("Le nom du topo n'a pas une longueur appropiée.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un nom de topo.");
		}
	}
	
	private void traitementRegionTopo(String regionTopo, BN_Topo nouveauTopo) {
		
		try {
			
			validationRegionTopo(regionTopo);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutTopo(CHAMP_REGION_TOPO, e.getMessage());
		}
		nouveauTopo.setRegionTopo(regionTopo);
	}

	private void validationRegionTopo(String regionSite) throws FORM_Exception {

		if(regionSite == null) {

			throw new FORM_Exception("Veuillez renseigner une region.");
		}
	}
	
	private void traitementDescriptionTopo(String descriptionTopo, BN_Topo nouveauTopo) {
		
		nouveauTopo.setDescriptionTopo(descriptionTopo);
	}
	
	private void traitementDisponibiliteTopo(Boolean disponibiliteTopo, BN_Topo nouveauTopo) {
		
		nouveauTopo.setDisponibiliteTopo(disponibiliteTopo);
	}
	
	private static String getValeurChampTexteFormulaire(HttpServletRequest request, String nomChampTexteFormulaire) {
		
		String valeurChampTexteFormulaire = request.getParameter(nomChampTexteFormulaire);
		
		if(valeurChampTexteFormulaire == null || valeurChampTexteFormulaire.trim().length() == 0) {
			
			return null;
		
		} else {
			
			return valeurChampTexteFormulaire;
		}
	}

	public void setErreursAjoutTopo(String nomChampFormulaire, String message) {

		erreursAjoutTopo.put(nomChampFormulaire, message);
	}

}
