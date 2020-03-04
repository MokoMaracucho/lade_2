package lade.formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lade.beans.BN_Commentaire;
import lade.beans.BN_Site;
import lade.beans.BN_Utilisateur;
import lade.dao.DAO_Commentaire;
import lade.dao.DAO_Exception;
import lade.dao.DAO_Site;

public class FORM_TraitementFormulaireAjoutSite {
	
	public static final String ATT_SESSION_UTILISATEUR 	= "sessionUtilisateur";
	
	public static final String CHAMP_ID_CREATEUR_SITE 	= "idCreateurSite";
	public static final String CHAMP_NOM 				= "nomSite";
	public static final String CHAMP_REGION				= "regionSite";
	public static final String CHAMP_COMMENTAIRE		= "commentaireSite";
	
	String resultatAjoutSite;
	
	Map<String, String> erreursAjoutSite = new HashMap<String, String>();
	
	private DAO_Site daoSite;
	private DAO_Commentaire daoCommentaire;
	
	public FORM_TraitementFormulaireAjoutSite(DAO_Site daoSite, DAO_Commentaire daoCommentaire) {
		
		this.daoSite = daoSite;
		this.daoCommentaire = daoCommentaire;
	}
	
	public String getResultatAjoutSite() {
		return resultatAjoutSite;
	}

	public void setResultatAjoutSite(String resultatAjoutSite) {
		this.resultatAjoutSite = resultatAjoutSite;
	}

	public Map<String, String> getErreursAjoutSite() {
		return erreursAjoutSite;
	}

	public BN_Site traitementFormulaireAjoutSite(HttpServletRequest request, HttpSession session) {
		
		BN_Utilisateur createurSite = (BN_Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR);
		Long idCreateurSite 		= createurSite.getIdUtilisateur();

		String nomSite 				= getValeurChampTexteFormulaire(request, CHAMP_NOM);
		
		String regionSite 			= getValeurChampTexteFormulaire(request, CHAMP_REGION);
		
		String commentaireSite		= getValeurChampTexteFormulaire(request, CHAMP_COMMENTAIRE);
		
		BN_Site nouveauSite = new BN_Site();
		BN_Commentaire nouveauCommentaireSite = new BN_Commentaire();
			
		try {
			
			traitementIdCreateurSite(idCreateurSite, nouveauSite);
			traitementNom(nomSite, nouveauSite);
			traitementRegion(regionSite, nouveauSite);
		
			if(erreursAjoutSite.isEmpty()) {
				
				daoSite.insertionSite(nouveauSite);
				
				if(commentaireSite != null) {
				
					BN_Site site = daoSite.selectionSiteParNom(nomSite);
					
					Long idSite = site.getIdSite();
					
					traitementCommentaireSite(idCreateurSite, idSite, commentaireSite, nouveauCommentaireSite);
					
					daoCommentaire.insertionCommentaireSite(nouveauCommentaireSite);
					
				}
				
				resultatAjoutSite = "Succés de l'ajout du site.";
			
			} else {
				
				resultatAjoutSite = "Échec de l'ajout du site.";
			}
			
		} catch (DAO_Exception e) {
			
			resultatAjoutSite = "Échec de l'ajout du site : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return nouveauSite;
	}
	
	private void traitementIdCreateurSite(Long idCreateurSite, BN_Site nouveauSite) {

		nouveauSite.setIdCreateurSite(idCreateurSite);
	}
	
	private void traitementNom(String nomSite, BN_Site nouveauSite) {
		
		try {
			
			validationNom(nomSite);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutSite(CHAMP_NOM, e.getMessage());
		}
		nouveauSite.setNomSite(nomSite);
	}

	private void validationNom(String nomSite) throws FORM_Exception {

		if(nomSite != null) {

			if(nomSite.length() > 3 && nomSite.length() < 30) {

				if(nomSite.matches("^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ '-]+$")) {
					
					if(daoSite.selectionSiteParNom(nomSite) != null ) {
	                	
	                    throw new FORM_Exception("Ce site est déjà existant.");
	                }
				} else {

					throw new FORM_Exception("Le nom du site ne doit comporter que des lettres.");
				}
			} else {

				throw new FORM_Exception("Le nom du site n'a pas une longueur appropiée.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un nom de site.");
		}
	}
	
	private void traitementRegion(String regionSite, BN_Site nouveauSite) {
		
		try {
			
			validationRegion(regionSite);
		
		} catch(FORM_Exception e) {
			
			setErreursAjoutSite(CHAMP_REGION, e.getMessage());
		}
		nouveauSite.setRegionSite(regionSite);
	}

	private void validationRegion(String regionSite) throws FORM_Exception {

		if(regionSite == null) {

			throw new FORM_Exception("Veuillez renseigner une region.");
		}
	}
	
	private void traitementCommentaireSite(Long idAuteurCommentaire, Long idSite, String commentaireSite, BN_Commentaire nouveauCommentaireSite) {
		
		nouveauCommentaireSite.setIdAuteurCommentaire(idAuteurCommentaire);
		nouveauCommentaireSite.setIdSiteCommentaire(idSite);
		nouveauCommentaireSite.setCommentaire(commentaireSite);
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

		erreursAjoutSite.put(nomChampFormulaire, message);
	}
}
