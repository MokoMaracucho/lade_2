package lade.beans;

import javax.persistence.Column;

public class BN_LongueurInnerJoin {

	@Column(name="id_longueur")
	private Long idLongueur;
	@Column(name="id_createur_longueur")
	private Long idCreateurLongueur;
	@Column(name="id_voie_longueur")
	private Long idVoieLongueur;
	@Column(name="nom_longueur")
	private String nomLongueur;
	@Column(name="cotation_longueur")
	private String cotationLongueur;
	private BN_Utilisateur		createurLongueur;
	private BN_Voie 			VoieLongueur;
	
	public Long getIdLongueur() {
		return idLongueur;
	}
	
	public void setIdLongueur(Long idLongueur) {
		this.idLongueur = idLongueur;
	}
	
	public Long getIdCreateurLongueur() {
		return idCreateurLongueur;
	}
	
	public void setIdCreateurLongueur(Long idCreateurLongueur) {
		this.idCreateurLongueur = idCreateurLongueur;
	}
	
	public Long getIdVoie() {
		return idVoieLongueur;
	}
	
	public void setIdVoie(Long idVoieLongueur) {
		this.idVoieLongueur = idVoieLongueur;
	}
	
	public String getNomLongueur() {
		return nomLongueur;
	}
	
	public void setNomLongueur(String nomLongueur) {
		this.nomLongueur = nomLongueur;
	}
	
	public String getCotationLongueur() {
		return cotationLongueur;
	}
	
	public void setCotationLongueur(String cotationLongueur) {
		this.cotationLongueur = cotationLongueur;
	}

	public BN_Utilisateur getCreateurLongueur() {
		return createurLongueur;
	}

	public void setCreateurLongueur(BN_Utilisateur createurLongueur) {
		this.createurLongueur = createurLongueur;
	}
	
	public BN_Voie getVoieLongueur() {
		return VoieLongueur;
	}

	public void setVoieLongueur(BN_Voie VoieLongueur) {
		this.VoieLongueur = VoieLongueur;
	}
}
