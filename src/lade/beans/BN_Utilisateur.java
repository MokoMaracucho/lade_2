package lade.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="tb_utilisateur")
public class BN_Utilisateur {

	@Column(name="id_utilisateur")
	private Long idUtilisateur;
	@Column(name="prenom_utilisateur")
	private String prenomUtilisateur;
	@Column(name="nom_utilisateur")
	private String nomUtilisateur;
	@Column(name="email_utilisateur")
	private String emailUtilisateur;
	@Column(name="mot_de_passe_utilisateur")
	private String motDePasseUtilisateur;
	@Column(name="numero_membre_utilisateur")
	private String numeroMembreUtilisateur;
	@Column(name="date_inscription_utilisateur")
	private Timestamp dateInscriptionUtilisateur;
	
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
