package lade.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lade.beans.BN_Secteur;
import lade.beans.BN_Utilisateur;
import lade.beans.BN_Voie;
import lade.dao.DAO_Exception;
import lade.dao.DAO_Secteur;
import lade.dao.DAO_Voie;

public class FORM_TraitementFormulaireAjoutVoie {
	
	public static final String ATT_SESSION_UTILISATEUR 	= "sessionUtilisateur";
	
	public static final String CHAMP_CREATEUR_VOIE 		= "idCreateurVoie";
	public static final String CHAMP_NOM_SECTEUR		= "nomSecteur";
	public static final String CHAMP_ID_SECTEUR			= "idSecteur";
	public static final String CHAMP_NOM_VOIE			= "nomVoie";
	public static final String CHAMP_EQUIPE_VOIE		= "equipeVoie";
	public static final String CHAMP_COTATION_VOIE		= "cotationVoie";
	
	String resultatAjoutVoie;
	
	Map<String, String> erreursAjoutVoie = new HashMap<String, String>();
	
	private DAO_Secteur daoSecteur;
	private DAO_Voie daoVoie;
	
	public FORM_TraitementFormulaireAjoutVoie(DAO_Secteur daoSecteur, DAO_Voie daoVoie) {
		
		this.daoSecteur = daoSecteur;
		this.daoVoie = daoVoie;
	}
	
	public String getResultatAjoutVoie() {
		return resultatAjoutVoie;
	}

	public void setResultatAjoutVoie(String resultatAjoutVoie) {
		this.resultatAjoutVoie = resultatAjoutVoie;
	}

	public Map<String, String> getErreursAjoutVoie() {
		return erreursAjoutVoie;
	}

	public BN_Voie traitementFormulaireAjoutVoie(HttpServletRequest request) {

		BN_Utilisateur createurVoie = (BN_Utilisateur) request.getAttribute(ATT_SESSION_UTILISATEUR);
		Long idCreateurVoie 		= createurVoie.getIdUtilisateur();
		
		String nomSecteur			= getValeurChampTexteFormulaire(request, CHAMP_NOM_SECTEUR);
		String nomVoie 				= getValeurChampTexteFormulaire(request, CHAMP_NOM_VOIE);
		Boolean equipeVoie 			= getValeurChampBooleenFormulaire(request, CHAMP_EQUIPE_VOIE);
		String cotationVoie			= getValeurChampTexteFormulaire(request, CHAMP_COTATION_VOIE);
	
		BN_Voie nouvelleVoie = new BN_Voie();
			
		try {
			
			traitementIdCreateurVoie(idCreateurVoie, nouvelleVoie);
			traitementIdSecteur(nomSecteur, nouvelleVoie);
			traitementNom(nomVoie, nouvelleVoie);
			traitementEquipe(equipeVoie, nouvelleVoie);
			traitementCotation(cotationVoie, nouvelleVoie);
		
			if(erreursAjoutVoie.isEmpty()) {
				
				daoVoie.insertionVoie(nouvelleVoie);
				
				resultatAjoutVoie = "Succés de l'ajout de la voie.";
			
			} else {
				
				resultatAjoutVoie = "Échec de l'ajout de la voie.";
			}
			
		} catch (DAO_Exception e) {
			
			resultatAjoutVoie = "Échec de l'ajout de la voie : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return nouvelleVoie;
	}
	
	private void traitementIdCreateurVoie(Long idCreateurVoie, BN_Voie nouvelleVoie) {

		nouvelleVoie.setIdCreateurVoie(idCreateurVoie);
	}
	
	private void traitementIdSecteur(String nomSecteur, BN_Voie nouvelleVoie) {
		
		BN_Secteur secteur = daoSecteur.selectionSecteurParNom(nomSecteur);
		
		Long idSecteur = secteur.getIdSecteur();
		
		nouvelleVoie.setIdSecteur(idSecteur);
	}

	private void traitementNom(String nomVoie, BN_Voie nouvelleVoie) {
		
		try {
			
			validationNomVoie(nomVoie);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutVoie(CHAMP_NOM_VOIE, e.getMessage());
		}
		nouvelleVoie.setNomVoie(nomVoie);
	}

	private void validationNomVoie(String nomVoie) throws FORM_Exception {

		if(nomVoie != null) {

			if(nomVoie.length() > 3 && nomVoie.length() < 30) {

				if(nomVoie.matches("^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ '-]+$")) {
					
					if(daoVoie.selectionVoieParNom(nomVoie) != null ) {
	                	
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
	
	private void traitementEquipe(Boolean equipeVoie, BN_Voie nouvelleVoie) {

		nouvelleVoie.setEquipeVoie(equipeVoie);
	}
	
	private void traitementCotation(String cotationVoie, BN_Voie nouvelleVoie) {
		
		try {
			
			validationCotation(cotationVoie);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutVoie(CHAMP_COTATION_VOIE, e.getMessage());
		}
		nouvelleVoie.setCotationVoie(cotationVoie);
	}
	
	private void validationCotation(String cotationVoie) throws FORM_Exception {
		
		if(cotationVoie == null) {
			
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
		
	private static Boolean getValeurChampBooleenFormulaire(HttpServletRequest request, String nomChampBooleenFormulaire) {
		
		String valeurChampTexteFormulaire = request.getParameter(nomChampBooleenFormulaire);
		
		Boolean valeurChampBooleenFormulaire;
		
		if(valeurChampTexteFormulaire == null) {
			
			valeurChampBooleenFormulaire = false;
		
		} else {
			
			valeurChampBooleenFormulaire = true;
		}
			
		return valeurChampBooleenFormulaire;
	}

	public void setErreursAjoutVoie(String nomChampFormulaire, String message) {

		erreursAjoutVoie.put(nomChampFormulaire, message);
	}
}
