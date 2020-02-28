package lade.beans;

import java.security.Timestamp;

public class BN_Utilisateur {

	private Long		idUtilisateur;
	private String 		prenomUtilisateur;
	private String 		nomUtilisateur;
	private String 		emailUtilisateur;
	private String 		motDePasseUtilisateur;
	private String 		numeroMembreUtilisateur;
	private Timestamp 	dateInscriptionUtilisateur;
	
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}
	
	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}
	
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	
	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}
	
	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}
	
	public String getMotDePasseUtilisateur() {
		return motDePasseUtilisateur;
	}
	
	public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
		this.motDePasseUtilisateur = motDePasseUtilisateur;
	}
	
	public String getNumeroMembreUtilisateur() {
		return numeroMembreUtilisateur;
	}
	
	public void setNumeroMembreUtilisateur(String numeroMembreUtilisateur) {
		this.numeroMembreUtilisateur = numeroMembreUtilisateur;
	}
	
	public Timestamp getDateInscriptionUtilisateur() {
		return dateInscriptionUtilisateur;
	}
	
	public void setDateInscriptionUtilisateur(Timestamp dateInscriptionUtilisateur) {
		this.dateInscriptionUtilisateur = dateInscriptionUtilisateur;
	}
}
