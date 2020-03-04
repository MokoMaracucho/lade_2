package lade.beans;

public class BN_SecteurInnerJoin {

	private Long 			idSecteur;
	private Long 			idCreateurSecteur;
	private Long 			idSite;
	private String 			nomSecteur;
	private BN_Utilisateur 	createurSecteur;
	private BN_Site 		site;
	
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
		return idSite;
	}
	
	public void setIdSite(Long idSite) {
		this.idSite = idSite;
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
