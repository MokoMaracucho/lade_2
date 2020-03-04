package lade.formulaires;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import lade.beans.BN_Utilisateur;
import lade.dao.DAO_Exception;
import lade.dao.DAO_Utilisateur;

public class FORM_TraitementFormulaireConnection {

	private static final String CHAMP_EMAIL_UTILISATEUR 				= "emailUtilisateur";
	private static final String CHAMP_MOT_DE_PASSE_UTILISATEUR 			= "motDePasseUtilisateur";
	
	private static final String ALGORYTHME_CHIFFREMENT 					= "SHA-256";
	
	private String resultatConnection;
	
	private Map<String, String> erreursConnection = new HashMap<String, String>();
	
	private DAO_Utilisateur daoUtilisateur;

	public String getResultatConnection() {
		return resultatConnection;
	}
	
	public Map<String, String> getErreursConnection() {
		return erreursConnection;
	}
	
	public FORM_TraitementFormulaireConnection(DAO_Utilisateur daoUtilisateur) {
		
		this.daoUtilisateur = daoUtilisateur;
	}
	
	public BN_Utilisateur traitementFormulaireConnection(HttpServletRequest request) {
		
		String emailUtilisateur 			= getValeurChampFormulaire(request, CHAMP_EMAIL_UTILISATEUR);
		String motDePasseUtilisateur 		= getValeurChampFormulaire(request, CHAMP_MOT_DE_PASSE_UTILISATEUR);
		
		BN_Utilisateur utilisateur = new BN_Utilisateur();
		
		try {
			
			traitementEmail(emailUtilisateur, utilisateur);
			traitementMotDePasse(motDePasseUtilisateur, utilisateur);
			
			if(erreursConnection.isEmpty()) {
				
				utilisateur = daoUtilisateur.selectionUtilisateurParEmail(emailUtilisateur);
				
				String emailUtilisateurBDD = utilisateur.getEmailUtilisateur();
				
				if(emailUtilisateurBDD == null) {
					
					setErreursConnection(CHAMP_EMAIL_UTILISATEUR, "Cette adresse email ne correspond à aucun utilisateur. Veuillez en utiliser une autre.");
					
				} else {
					
					ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
			        passwordEncryptor.setAlgorithm(ALGORYTHME_CHIFFREMENT);
			        passwordEncryptor.setPlainDigest(false);

					String motDePasseChiffreUtilisateur = utilisateur.getMotDePasseUtilisateur();
					
					if (passwordEncryptor.checkPassword(motDePasseUtilisateur, motDePasseChiffreUtilisateur)) {

						resultatConnection = "Succés de la connection.";
						
					} else {

						setErreursConnection(CHAMP_MOT_DE_PASSE_UTILISATEUR, "Le mot-de-passe n'est pas valide.");
						
						resultatConnection = "Échec de la connection.";
					}
				}
			} else {
				
				resultatConnection = "Échec de la connection.";
			}
			
		} catch(DAO_Exception e) {
			
			resultatConnection = "Échec de l'inscription : une erreur imprévue est survenue. Merci de réessayer dans quelques instants.";
			
			e.printStackTrace();
		}
		
		return utilisateur;
	}
	
	private void traitementEmail(String email, BN_Utilisateur nouvelUtilisateur){
		
		try {
			
			validationEmail(email);
		
		} catch(FORM_Exception e) {
			
			setErreursConnection(CHAMP_EMAIL_UTILISATEUR, e.getMessage());
		}
	}
	
	private void validationEmail(String email) throws FORM_Exception {
		
        if (email != null && email.trim().length() != 0) {
        	
            if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            	
                throw new FORM_Exception("L'adresse email n'est pas valide.");
            }
        } else {
        	
            throw new FORM_Exception("Veuillez renseigner un adresse email.");
        }
    }
	
	private void traitementMotDePasse(String motDePasse, BN_Utilisateur nouvelUtilisateur){
		
		try {
			
			validationMotDePasse(motDePasse);
		
		} catch(FORM_Exception e) {
			
			setErreursConnection(CHAMP_MOT_DE_PASSE_UTILISATEUR, e.getMessage());
		}
	}

	private void validationMotDePasse(String motDePasseUtilisateur) throws FORM_Exception {
		
		if (motDePasseUtilisateur != null && motDePasseUtilisateur.trim().length() != 0) {
		
			if (motDePasseUtilisateur.equals(motDePasseUtilisateur)) {
	
				if (motDePasseUtilisateur.length() >= 8 && motDePasseUtilisateur.length() <= 50) {
	
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

			throw new FORM_Exception("Veuillez renseigner un mot-de-passe.");
		}
	}
	
	private static String getValeurChampFormulaire(HttpServletRequest request, String nomChampFormulaire) {
		
		String valeurChampFormulaire = request.getParameter(nomChampFormulaire);
		
		if(valeurChampFormulaire == null || valeurChampFormulaire.trim().length() == 0) {
			
			return null;
		
		} else {
			
			return valeurChampFormulaire;
		}
	}

	public void setErreursConnection(String nomChampFormulaire, String message) {

		erreursConnection.put(nomChampFormulaire, message);
	}
}
