package lade.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lade.beans.BN_Voie;
import lade.beans.BN_Utilisateur;
import lade.beans.BN_Longueur;
import lade.dao.DAO_Exception;
import lade.dao.DAO_Voie;
import lade.dao.DAO_Longueur;

public class FORM_TraitementFormulaireAjoutLongueur {
	
	public static final String ATT_SESSION_UTILISATEUR 		= "sessionUtilisateur";
	
	public static final String CHAMP_CREATEUR_LONGUEUR 		= "idCreateurLongueur";
	public static final String CHAMP_NOM_VOIE				= "nomVoie";
	public static final String CHAMP_ID_VOIE				= "idVoie";
	public static final String CHAMP_NOM_LONGUEUR			= "nomLongueur";
	public static final String CHAMP_COTATION_LONGUEUR		= "cotationLongueur";
	
	String resultatAjoutLongueur;
	
	Map<String, String> erreursAjoutLongueur = new HashMap<String, String>();
	
	private DAO_Voie daoVoie;
	private DAO_Longueur daoLongueur;
	
	public FORM_TraitementFormulaireAjoutLongueur(DAO_Voie daoVoie, DAO_Longueur daoLongueur) {
		
		this.daoVoie = daoVoie;
		this.daoLongueur = daoLongueur;
	}
	
	public String getResultatAjoutLongueur() {
		return resultatAjoutLongueur;
	}

	public void setResultatAjoutLongueur(String resultatAjoutLongueur) {
		this.resultatAjoutLongueur = resultatAjoutLongueur;
	}

	public Map<String, String> getErreursAjoutLongueur() {
		return erreursAjoutLongueur;
	}

	public BN_Longueur traitementFormulaireAjoutLongueur(HttpServletRequest request) {

		BN_Utilisateur createurLongueur = (BN_Utilisateur) request.getAttribute(ATT_SESSION_UTILISATEUR);
		Long idCreateurLongueur 		= createurLongueur.getIdUtilisateur();
		
		String nomVoie					= getValeurChampTexteFormulaire(request, CHAMP_NOM_VOIE);
		String nomLongueur 				= getValeurChampTexteFormulaire(request, CHAMP_NOM_LONGUEUR);
		String cotationLongueur			= getValeurChampTexteFormulaire(request, CHAMP_COTATION_LONGUEUR);
	
		BN_Longueur nouvelleLongueur = new BN_Longueur();
			
		try {
			
			traitementIdCreateurLongueur(idCreateurLongueur, nouvelleLongueur);
			traitementIdVoie(nomVoie, nouvelleLongueur);
			traitementNom(nomLongueur, nouvelleLongueur);
			traitementCotation(cotationLongueur, nouvelleLongueur);
		
			if(erreursAjoutLongueur.isEmpty()) {
				
				daoLongueur.insertionLongueur(nouvelleLongueur);
				
				resultatAjoutLongueur = "Succés de l'ajout de la voie.";
			
			} else {
				
				resultatAjoutLongueur = "Échec de l'ajout de la voie.";
			}
			
		} catch (DAO_Exception e) {
			
			resultatAjoutLongueur = "Échec de l'ajout de la voie : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return nouvelleLongueur;
	}
	
	private void traitementIdCreateurLongueur(Long idCreateurLongueur, BN_Longueur nouvelleLongueur) {

		nouvelleLongueur.setIdCreateurLongueur(idCreateurLongueur);
	}
	
	private void traitementIdVoie(String nomVoie, BN_Longueur nouvelleLongueur) {
		
		BN_Voie secteur = daoVoie.selectionVoieParNom(nomVoie);
		
		Long idVoie = secteur.getIdVoie();
		
		nouvelleLongueur.setIdVoie(idVoie);
	}

	private void traitementNom(String nomLongueur, BN_Longueur nouvelleLongueur) {
		
		try {
			
			validationNomLongueur(nomLongueur);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutLongueur(CHAMP_NOM_LONGUEUR, e.getMessage());
		}
		nouvelleLongueur.setNomLongueur(nomLongueur);
	}

	private void validationNomLongueur(String nomLongueur) throws FORM_Exception {

		if(nomLongueur != null) {

			if(nomLongueur.length() > 3 && nomLongueur.length() < 30) {

				if(nomLongueur.matches("^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ '-]+$")) {
					
					if(daoLongueur.selectionLongueurParNom(nomLongueur) != null ) {
	                	
	                    throw new FORM_Exception("Ce site est déjà existant.");
	                }
				} else {

					throw new FORM_Exception("Le nom de la voie ne doit pas comporter que des lettres.");
				}
			} else {

				throw new FORM_Exception("Le nom de la voie n'a pas une longueur appropiée.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un nom de voie.");
		}
	}
	
	private void traitementCotation(String cotationLongueur, BN_Longueur nouvelleLongueur) {
		
		try {
			
			validationCotation(cotationLongueur);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutLongueur(CHAMP_COTATION_LONGUEUR, e.getMessage());
		}
		nouvelleLongueur.setCotationLongueur(cotationLongueur);
	}
	
	private void validationCotation(String cotationLongueur) throws FORM_Exception {
		
		if(cotationLongueur == null) {
			
			throw new FORM_Exception("Veuillez renseigner une cotation.");
		}
	}
	
	private static String getValeurChampTexteFormulaire(HttpServletRequest request, String nomChampTexteFormulaire) {
		
		String valeurChampTexteFormulaire = request.getParameter(nomChampTexteFormulaire);
		
		if(valeurChampTexteFormulaire == null || valeurChampTexteFormulaire.trim().length() == 0) {
			
			return null;
		
		} else {
			
			return valeurChampTexteFormulaire;
		}
	}

	public void setErreursAjoutLongueur(String nomChampFormulaire, String message) {

		erreursAjoutLongueur.put(nomChampFormulaire, message);
	}
}
