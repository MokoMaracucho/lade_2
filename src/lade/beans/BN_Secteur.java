package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tb_secteur")
public class BN_Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_secteur")
	private Long idSecteur;
	@Column(name="id_createur_secteur")
	private Long idCreateurSecteur;
	@Column(name="id_site_secteur")
	private Long idSiteSecteur;
	@Column(name="nom_secteur")
	private String nomSecteur;

	public Long getIdSecteur() {
		return idSecteur;
	}
	
	public void setIdSecteur(Long idSecteur) {
		this.idSecteur = idSecteur;
	}
	
	public Long getIdCreateurSecteur() {
		return idCreateurSecteur;
	}
	
	public void setIdCreateurSecteur(Long idCreateurSecteur) {
		this.idCreateurSecteur = idCreateurSecteur;
	}
	
	public Long getIdSite() {
		return idSiteSecteur;
	}
	
	public void setIdSite(Long idSiteSecteur) {
		this.idSiteSecteur = idSiteSecteur;
	}
	
	public String getNomSecteur() {
		return nomSecteur;
	}
	
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
}
