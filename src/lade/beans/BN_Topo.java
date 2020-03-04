package lade.beans;

import java.sql.Timestamp;

public class BN_Topo {
	
	private Long idTopo;
	private Long idProprietaireTopo;
	private String nomTopo;
	private String regionTopo;
	private String descriptionTopo;
	private Timestamp dateParutionTopo;
	private Boolean disponibiliteTopo;

	public Long getIdTopo() {
		return idTopo;
	}
	
	public void setIdTopo(Long idTopo) {
		this.idTopo = idTopo;
	}
	
	public Long getIdProprietaireTopo() {
		return idProprietaireTopo;
	}
	
	public void setIdProprietaireTopo(Long idProprietaireTopo) {
		this.idProprietaireTopo = idProprietaireTopo;
	}
	
	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

	public String getRegionTopo() {
		return regionTopo;
	}

	public void setRegionTopo(String regionTopo) {
		this.regionTopo = regionTopo;
	}

	public String getDescriptionTopo() {
		return descriptionTopo;
	}
	
	public void setDescriptionTopo(String descriptionTopo) {
		this.descriptionTopo = descriptionTopo;
	}
	
	public Timestamp getDateParutionTopo() {
		return dateParutionTopo;
	}
	
	public void setDateParutionTopo(Timestamp dateParutionTopo) {
		this.dateParutionTopo = dateParutionTopo;
	}
	public Boolean getDisponibiliteTopo() {
		return disponibiliteTopo;
	}

	public void setDisponibiliteTopo(Boolean disponibiliteTopo) {
		this.disponibiliteTopo = disponibiliteTopo;
	}
}
