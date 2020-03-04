package lade.beans;

public class BN_Secteur {

	private Long 	idSecteur;
	private Long 	idCreateurSecteur;
	private Long 	idSite;
	private String 	nomSecteur;

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
}
