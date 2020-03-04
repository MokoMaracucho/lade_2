package lade.beans;

public class BN_LongueurInnerJoin {

	private Long				idLongueur;
	private Long				IdCreateurLongueur;
	private Long				IdVoie;
	private String 				nomLongueur;
	private String 				cotationLongueur;
	private BN_Utilisateur		createurLongueur;
	private BN_Voie 			VoieLongueur;
	
	public Long getIdLongueur() {
		return idLongueur;
	}
	
	public void setIdLongueur(Long idLongueur) {
		this.idLongueur = idLongueur;
	}
	
	public Long getIdCreateurLongueur() {
		return IdCreateurLongueur;
	}
	
	public void setIdCreateurLongueur(Long IdCreateurLongueur) {
		this.IdCreateurLongueur = IdCreateurLongueur;
	}
	
	public Long getIdVoie() {
		return IdVoie;
	}
	
	public void setIdVoie(Long IdVoie) {
		this.IdVoie = IdVoie;
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
