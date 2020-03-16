package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="tb_longueur")
public class BN_Longueur {

	@Column(name="id_longueur")
	private Long idLongueur;
	@Column(name="id_createur_longueur")
	private Long idCreateurLongueur;
	@Column(name="id_voie_longueur")
	private Long idVoieLongeur;
	@Column(name="nom_longueur")
	private String nomLongueur;
	@Column(name="cotation_longueur")
	private String cotationLongueur;
	
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
		return idVoieLongeur;
	}
	
	public void setIdVoie(Long idVoieLongeur) {
		this.idVoieLongeur = idVoieLongeur;
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
}