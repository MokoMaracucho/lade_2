package lade.formulaires;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import lade.beans.BN_Utilisateur;

public class FORM_TraitementFormulaireInscription {
	
	public static final String CHAMP_PRENOM_UTILISATEUR 						= "prenomUtilisateur";
	public static final String CHAMP_NOM_UTILISATEUR 							= "nomUtilisateur";
	public static final String CHAMP_EMAIL_UTILISATEUR 							= "emailUtilisateur";
	public static final String CHAMP_MOT_DE_PASSE_UTILISATEUR 					= "motDePasseUtilisateur";
	public static final String CHAMP_CONFIRMATION_MOT_DE_PASSE_UTILISATEUR 		= "confirmationMotDePasseUtilisateur";
	public static final String CHAMP_NUMERO_MEMBRE_UTILISATEUR 					= "numeroMembreUtilisateur";
	
	private static final String ALGORYTHME_CHIFFREMENT 							= "SHA-256";
	
	String resultatInscription;
	
	Map<String, String> erreursInscription = new HashMap<String, String>();

	public String getResultatInscription() {
		return resultatInscription;
	}

	public void setResultatInscription(String resultatInscription) {
		this.resultatInscription = resultatInscription;
	}

	public Map<String, String> getErreursInscription() {
		return erreursInscription;
	}

	public void setErreursInscription(Map<String, String> erreursInscription) {
		this.erreursInscription = erreursInscription;
	}
	
	public BN_Utilisateur traitementFormulaireInscription(HttpServletRequest request) {
		
		String prenomUtilisateur 						= getValeurChampFormulaire(request, CHAMP_PRENOM_UTILISATEUR);
		String nomUtilisateur 							= getValeurChampFormulaire(request, CHAMP_NOM_UTILISATEUR);
		String emailUtilisateur 						= getValeurChampFormulaire(request, CHAMP_EMAIL_UTILISATEUR);
		String motDePasseUtilisateur 					= getValeurChampFormulaire(request, CHAMP_MOT_DE_PASSE_UTILISATEUR);
		String confirmationMotDePasseUtilisateur 		= getValeurChampFormulaire(request, CHAMP_CONFIRMATION_MOT_DE_PASSE_UTILISATEUR);
		String numeroMembreUtilisateur 					= getValeurChampFormulaire(request, CHAMP_NUMERO_MEMBRE_UTILISATEUR);
	
		BN_Utilisateur nouvelUtilisateur = new BN_Utilisateur();
			
		traitementPrenom(prenomUtilisateur, nouvelUtilisateur);
		traitementNom(nomUtilisateur, nouvelUtilisateur);
		traitementEmail(emailUtilisateur, nouvelUtilisateur);
		traitementMotDePasse(motDePasseUtilisateur, confirmationMotDePasseUtilisateur, nouvelUtilisateur);
		traitementNumeroMembre(numeroMembreUtilisateur, nouvelUtilisateur);
	
		if(erreursInscription.isEmpty()) {
			
			resultatInscription = "Succés de l'inscription.";
		
		} else {
			
			resultatInscription = "Échec de l'inscription.";
		}
		
		return nouvelUtilisateur;
	}
	
	private void traitementPrenom(String prenomUtilisateur, BN_Utilisateur nouvelUtilisateur) {
		
		try {
			
			validationPrenom(prenomUtilisateur);
		
		} catch(FORM_Exception e) {
			
			setErreursInscription(CHAMP_PRENOM_UTILISATEUR, e.getMessage());
		}
		nouvelUtilisateur.setPrenomUtilisateur(prenomUtilisateur);
	}

	private void validationPrenom(String prenomUtilisateur) throws FORM_Exception {

		if (prenomUtilisateur != null && prenomUtilisateur.trim().length() != 0) {

			if (prenomUtilisateur.length() > 2 && prenomUtilisateur.length() < 30) {

				if (!prenomUtilisateur.matches("^[a-zA-Z]+$")) {

					throw new FORM_Exception("Le prénom ne doit comporter que des lettres.");
				}
			} else {

				throw new FORM_Exception("Le prénom n'a pas une longueur appropiée.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un prénom.");
		}
	}
	
	private void traitementNom(String nomUtilisateur, BN_Utilisateur nouvelUtilisateur) {
		
		try {
			
			validationNom(nomUtilisateur);
		
		} catch(FORM_Exception e) {
			
			setErreursInscription(CHAMP_NOM_UTILISATEUR, e.getMessage());
		}
		nouvelUtilisateur.setNomUtilisateur(nomUtilisateur);
	}

	private void validationNom(String nomUtilisateur) throws FORM_Exception {

		if (nomUtilisateur != null && nomUtilisateur.trim().length() != 0) {

			if (nomUtilisateur.length() > 3 && nomUtilisateur.length() < 30) {

				if (!nomUtilisateur.matches("^[a-zA-Z]+$")) {

					throw new FORM_Exception("Le nom ne doit comporter que des lettres.");
				}
			} else {

				throw new FORM_Exception("Le nom n'a pas une longueur appropiée.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un nom.");
		}
	}
	
	private void traitementEmail(String emailUtilisateur, BN_Utilisateur nouvelUtilisateur){
		
		try {
			
			validationEmail(emailUtilisateur);
		
		} catch(FORM_Exception e) {
			
			setErreursInscription(CHAMP_EMAIL_UTILISATEUR, e.getMessage());
		}
		nouvelUtilisateur.setEmailUtilisateur(emailUtilisateur);
	}
	
	private void validationEmail(String emailUtilisateur) throws FORM_Exception {
		
        if (emailUtilisateur != null && emailUtilisateur.trim().length() != 0) {
        	
            if (!emailUtilisateur.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            	
                throw new FORM_Exception("L'adresse email n'est pas valide.");
            }
        } else {
        	
            throw new FORM_Exception("Veuillez renseigner un adresse email.");
        }
    }
	
	private void traitementMotDePasse(String motDePasseUtilisateur, String confirmationMotDePasseUtilisateur, BN_Utilisateur nouvelUtilisateur){
		
		try {
			
			validationMotDePasse(motDePasseUtilisateur, confirmationMotDePasseUtilisateur);
		
		} catch(FORM_Exception e) {
			
			setErreursInscription(CHAMP_MOT_DE_PASSE_UTILISATEUR, e.getMessage());
		}
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm(ALGORYTHME_CHIFFREMENT);
        passwordEncryptor.setPlainDigest(false);
        String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasseUtilisateur);
		nouvelUtilisateur.setMotDePasseUtilisateur(motDePasseChiffre);
	}

	private void validationMotDePasse(String motDePasseUtilisateur, String confirmationMotDePasseUtilisateur) throws FORM_Exception {

		String regex_Minuscule = "[a-z]";

		String regex_Majuscule = "[A-Z]";

		String regex_Chiffre = "[0-9]";

		String regex_Caractere_special = "[- @^_!\"#$%&'()*+,./:;{}<>=|~?]";

		Pattern pattern_Minuscule = Pattern.compile(regex_Minuscule);

		Pattern pattern_Majuscule = Pattern.compile(regex_Majuscule);

		Pattern pattern_Chiffre = Pattern.compile(regex_Chiffre);

		Pattern pattern_Caractere_special = Pattern.compile(regex_Caractere_special);

		Matcher matcher_Minuscule = pattern_Minuscule.matcher(motDePasseUtilisateur);

		Matcher matcher_Majuscule = pattern_Majuscule.matcher(motDePasseUtilisateur);

		Matcher matcher_Chiffre = pattern_Chiffre.matcher(motDePasseUtilisateur);

		Matcher matcher_Caractere_special = pattern_Caractere_special.matcher(motDePasseUtilisateur);

		if (motDePasseUtilisateur != null && motDePasseUtilisateur.trim().length() != 0 && confirmationMotDePasseUtilisateur != null && confirmationMotDePasseUtilisateur.trim().length() != 0 ) {

			if (motDePasseUtilisateur.equals(confirmationMotDePasseUtilisateur)) {

				if (motDePasseUtilisateur.length() >= 8 && motDePasseUtilisateur.length() <= 50) {

					if (matcher_Minuscule.find()) {

						if (matcher_Majuscule.find()) {

							if (matcher_Chiffre.find()) {

								if (matcher_Caractere_special.find()) {

									if (motDePasseUtilisateur.matches("[^a-zA-Z0-9 @^_!\\\"#$%&'()*+,./:;{}<>=|~?-]")) {

										throw new FORM_Exception("Le mot de passe comporte un caractère non-autorisé.");
									}
								} else {

									throw new FORM_Exception("Le mot-de-passe doit comporter au moins un caractère spécial.");
								}
							} else {

								throw new FORM_Exception("Le mot-de-passe doit comporter au moins un chiffre.");
							}
						} else {

							throw new FORM_Exception("Le mot-de-passe doit comporter au moins une lettre majuscule.");
						}
					} else {

						throw new FORM_Exception("Le mot-de-passe doit comporter au moins une lettre minuscule.");
					}
				} else {

					throw new FORM_Exception("Le mot-de-passe n'a pas une longueur appropriée.");
				}
			} else {

				throw new FORM_Exception("Il faut que les mots-de-passes renseignés soient identiques.");
			}
		} else {

			throw new FORM_Exception("Veuillez renseigner un mot-de-passe et le confirmer.");
		}
	}
	
	private void traitementNumeroMembre(String numeroMembreUtilisateur, BN_Utilisateur nouvelUtilisateur){
		
		try {
			
			validationNumeroMembre(numeroMembreUtilisateur);
		
		} catch(FORM_Exception e) {
			
			setErreursInscription(CHAMP_NUMERO_MEMBRE_UTILISATEUR, e.getMessage());
		}
		nouvelUtilisateur.setNumeroMembreUtilisateur(numeroMembreUtilisateur);
	}

	private void validationNumeroMembre(String numeroMembreUtilisateur) throws FORM_Exception {

		if (numeroMembreUtilisateur != null && numeroMembreUtilisateur.trim().length() != 0) {

			if (numeroMembreUtilisateur.length() == 6) {

				if (!numeroMembreUtilisateur.matches("[A-Z0-9]{6}$")) {

					throw new FORM_Exception("Le numéro de membre ne doit comporter que des lettres majuscules ou des chiffres.");
				}
			} else {

				throw new FORM_Exception("Le numéro de membre n'a pas une longueur appropiée.");
			}
		}
	}
	
	private static String getValeurChampFormulaire(HttpServletRequest request, String nomChampTexteFormulaire) {
		
		String valeurChampTexteFormulaire = request.getParameter(nomChampTexteFormulaire);
		
		if(valeurChampTexteFormulaire == null || valeurChampTexteFormulaire.trim().length() == 0) {
			
			return null;
		
		} else {
			
			return valeurChampTexteFormulaire;
		}
	}

	public void setErreursInscription(String nomChampFormulaire, String message) {

		erreursInscription.put(nomChampFormulaire, message);
	}
}
