package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tb_topo")
public class BN_Voie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_voie")
	private Long idVoie;
	@Column(name="id_createur_voie")
	private Long idCreateurVoie;
	@Column(name="id_secteur_voie")
	private Long idSecteurVoie;
	@Column(name="nom_voie")
	private String nomVoie;
	@Column(name="equipe_voie")
	private Boolean	equipeVoie;
	@Column(name="cotation_voie")
	private String cotationVoie;
	
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
		return idSecteurVoie;
	}
	
	public void setIdSecteur(Long idSecteurVoie) {
		this.idSecteurVoie = idSecteurVoie;
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