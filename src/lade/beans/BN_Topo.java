package lade.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tb_topo")
public class BN_Topo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_topo")
	private Long idTopo;
	@Column(name="id_proprietaire_topo")
	private Long idProprietaireTopo;
	@Column(name="nom_topo")
	private String nomTopo;
	@Column(name="region_topo")
	private String regionTopo;
	@Column(name="description_topo")
	private String descriptionTopo;
	@Column(name="date_parution_topo")
	private Timestamp dateParutionTopo;
	@Column(name="disponibilite_topo")
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
