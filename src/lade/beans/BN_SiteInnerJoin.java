package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="tb_site")
public class BN_SiteInnerJoin {

	@Column(name="id_site")
	private Long idSite;
	@Column(name="id_createur_site")
	private Long IdCreateurSite;
	@Column(name="nom_site")
	private String nomSite;
	@Column(name="region_site")
	private String regionSite;
	private BN_Utilisateur 	createurSite;

	public Long getIdSite() {
		return idSite;
	}
	
	public void setIdSite(Long id) {
		this.idSite = id;
	}
	public Long getIdCreateurSite() {
		return IdCreateurSite;
	}

	public void setIdCreateurSite(Long IdCreateurSite) {
		this.IdCreateurSite = IdCreateurSite;
	}
	
	public String getNomSite() {
		return nomSite;
	}
	
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	
	public String getRegionSite() {
		return regionSite;
	}
	
	public void setRegionSite(String regionSite) {
		this.regionSite = regionSite;
	}
	
	public BN_Utilisateur getCreateurSite() {
		return createurSite;
	}

	public void setCreateurSite(BN_Utilisateur createurSite) {
		this.createurSite = createurSite;
	}
}
