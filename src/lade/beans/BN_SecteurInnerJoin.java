package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tb_secteur")
public class BN_SecteurInnerJoin {

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
	private BN_Utilisateur createurSecteur;
	private BN_Site site;
	
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
	
	public BN_Utilisateur getCreateurSecteur() {
		return createurSecteur;
	}
	
	public void setCreateurSecteur(BN_Utilisateur createurSecteur) {
		this.createurSecteur = createurSecteur;
	}
	
	public BN_Site getSite() {
		return site;
	}
	
	public void setSite(BN_Site site) {
		this.site = site;
	}
}
