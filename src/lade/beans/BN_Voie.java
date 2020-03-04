package lade.beans;

public class BN_Voie {

	private Long			idVoie;
	private Long			idCreateurVoie;
	private Long			idSecteur;
	private String 			nomVoie;
	private Boolean			equipeVoie;
	private String 			cotationVoie;
	
	public Long getIdVoie() {
		return idVoie;
	}
	
	public void setIdVoie(Long idVoie) {
		this.idVoie = idVoie;
	}
	
	public Long getIdCreateurVoie() {
		return idCreateurVoie;
	}
	
	public void setIdCreateurVoie(Long idCreateurVoie) {
		this.idCreateurVoie = idCreateurVoie;
	}
	
	public Long getIdSecteur() {
		return idSecteur;
	}
	
	public void setIdSecteur(Long idSecteur) {
		this.idSecteur = idSecteur;
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
}