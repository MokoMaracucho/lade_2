package lade.beans;

public class BN_VoieInnerJoin {

	private Long				idVoie;
	private Long				IdCreateurVoie;
	private Long				IdSecteur;
	private String 				nomVoie;
	private Boolean 			equipeVoie;
	private String 				cotationVoie;
	private BN_Utilisateur		createurVoie;
	private BN_Secteur 			secteurVoie;
	
	public Long getIdVoie() {
		return idVoie;
	}
	
	public void setIdVoie(Long idVoie) {
		this.idVoie = idVoie;
	}
	
	public Long getIdCreateurVoie() {
		return IdCreateurVoie;
	}
	
	public void setIdCreateurVoie(Long IdCreateurVoie) {
		this.IdCreateurVoie = IdCreateurVoie;
	}
	
	public Long getIdSecteur() {
		return IdSecteur;
	}
	
	public void setIdSecteur(Long IdSecteur) {
		this.IdSecteur = IdSecteur;
	}
	
	public String getNomVoie() {
		return nomVoie;
	}
	
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	
	public Boolean getEquipeVoie() {
		return equipeVoie;
	}

	public void setEquipeVoie(Boolean equipeVoie) {
		this.equipeVoie = equipeVoie;
	}
	
	public String getCotationVoie() {
		return cotationVoie;
	}
	
	public void setCotationVoie(String cotationVoie) {
		this.cotationVoie = cotationVoie;
	}

	public BN_Utilisateur getCreateurVoie() {
		return createurVoie;
	}

	public void setCreateurVoie(BN_Utilisateur createurVoie) {
		this.createurVoie = createurVoie;
	}
	
	public BN_Secteur getSecteurVoie() {
		return secteurVoie;
	}

	public void setSecteurVoie(BN_Secteur secteurVoie) {
		this.secteurVoie = secteurVoie;
	}
}