package lade.beans;

public class BN_Longueur {

	private Long			idLongueur;
	private Long			idCreateurLongueur;
	private Long			idVoie;
	private String 			nomLongueur;
	private String 			cotationLongueur;
	
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
		return idVoie;
	}
	
	public void setIdVoie(Long idVoie) {
		this.idVoie = idVoie;
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