package lade.beans;

public class BN_Site {

	private Long					idSite;
	private Long					IdCreateurSite;
	private String 					nomSite;
	private String 					regionSite;

	public Long getIdSite() {
		return idSite;
	}
	
	public void setIdSite(Long idSite) {
		this.idSite = idSite;
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
}
