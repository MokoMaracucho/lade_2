package lade.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Secteur;
import lade.beans.BN_Site;
import lade.beans.BN_Utilisateur;
import lade.dao.DAO_Exception;
import lade.dao.DAO_Secteur;
import lade.dao.DAO_Site;

public class FORM_TraitementFormulaireAjoutSecteur {
	
	public static final String ATT_SESSION_UTILISATEUR 	= "sessionUtilisateur";
	
	public static final String CHAMP_CREATEUR_SECTEUR 	= "idCreateurSecteur";
	public static final String CHAMP_NOM_SITE			= "nomSite";
	public static final String CHAMP_ID_SITE			= "idSite";
	public static final String CHAMP_NOM_SECTEUR		= "nomSecteur";
	
	String resultatAjoutSecteur;
	
	Map<String, String> erreursAjoutSecteur = new HashMap<String, String>();
	
	private DAO_Site daoSite;
	private DAO_Secteur daoSecteur;
	
	public FORM_TraitementFormulaireAjoutSecteur(DAO_Site daoSite, DAO_Secteur daoSecteur) {
		
		this.daoSite = daoSite;
		this.daoSecteur = daoSecteur;
	}
	
	public String getResultatAjoutSecteur() {
		return resultatAjoutSecteur;
	}

	public void setResultatAjoutSecteur(String resultatAjoutSecteur) {
		this.resultatAjoutSecteur = resultatAjoutSecteur;
	}

	public Map<String, String> getErreursAjoutSecteur() {
		return erreursAjoutSecteur;
	}

	public BN_Secteur traitementFormulaireAjoutSecteur(HttpServletRequest request, HttpSession session) {

		BN_Utilisateur createurSecteur = (BN_Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR);
		Long idCreateurSecteur 		= createurSecteur.getIdUtilisateur();
		
		String nomSite 				= getValeurChampTexteFormulaire(request, CHAMP_NOM_SITE);
		String nomSecteur			= getValeurChampTexteFormulaire(request, CHAMP_NOM_SECTEUR);
	
		BN_Secteur nouveauSecteur = new BN_Secteur();
			
		try {
			
			traitementIdCreateurSecteur(idCreateurSecteur, nouveauSecteur);
			traitementIdSite(nomSite, nouveauSecteur);
			traitementNom(nomSecteur, nouveauSecteur);
		
			if(erreursAjoutSecteur.isEmpty()) {
				
				daoSecteur.insertionSecteur(nouveauSecteur);
				
				resultatAjoutSecteur = "Succés de l'ajout de la voie.";
			
			} else {
				
				resultatAjoutSecteur = "Échec de l'ajout de la voie.";
			}
			
		} catch (DAO_Exception e) {
			
			resultatAjoutSecteur = "Échec de l'ajout de la voie : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return nouveauSecteur;
	}
	
	private void traitementIdCreateurSecteur(Long idCreateurSecteur, BN_Secteur nouveauSecteur) {

		nouveauSecteur.setIdCreateurSecteur(idCreateurSecteur);
	}
	
	private void traitementIdSite(String nomSite, BN_Secteur nouveauSecteur) {
		
		BN_Site site = daoSite.selectionSiteParNom(nomSite);
		
		Long idSite = site.getIdSite();
		
		nouveauSecteur.setIdSite(idSite);
	}
	
	private void traitementNom(String nomSecteur, BN_Secteur nouveauSecteur) {
		
		try {
			
			validationNom(nomSecteur);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutSecteur(CHAMP_NOM_SECTEUR, e.getMessage());
		}
		nouveauSecteur.setNomSecteur(nomSecteur);
	}

	private void validationNom(String nomSecteur) throws FORM_Exception {

		if (nomSecteur != null) {

			if (nomSecteur.length() > 3 && nomSecteur.length() < 30) {

				if (nomSecteur.matches("^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ '-]+$")) {
					
					if (daoSecteur.selectionSecteurParNom(nomSecteur) != null ) {
	                	
	                    throw new FORM_Exception("Cette voie est déjà existante.");
	                }
				} else {

					throw new FORM_Exception("Le nom de la voie ne doit comporter de caractères spéciaux.");
				}
			} else {

				throw new FORM_Exception("Le nom de la voie n'a pas une longueur appropiée.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un nom de voie.");
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
	public void setErreursAjoutSecteur(String nomChampFormulaire, String message) {

		erreursAjoutSecteur.put(nomChampFormulaire, message);
	}
}
