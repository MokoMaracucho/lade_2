package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="tb_reservation_topo")
public class BN_ReservationTopo {
	
	@Column(name="id_reservation_topo")
	private Long idReservationTopo;
	@Column(name="id_topo")
	private Long idTopo;
	@Column(name="id_proprietaire_topo")
	private Long idProprietaireTopo;
	@Column(name="id_demandeur_reservation_topo")
	private Long idDemandeurReservationTopo;
	@Column(name="statut_reservation_topo")
	private String statutReservationTopo;
	
	public Long getIdReservationTopo() {
		return idReservationTopo;
	}
	
	public void setIdReservationTopo(Long idReservationTopo) {
		this.idReservationTopo = idReservationTopo;
	}
	
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
	
	public Long getIdDemandeurReservationTopo() {
		return idDemandeurReservationTopo;
	}
	
	public void setIdDemandeurReservationTopo(Long idDemandeurReservationTopo) {
		this.idDemandeurReservationTopo = idDemandeurReservationTopo;
	}

	public String getStatutReservationTopo() {
		return statutReservationTopo;
	}

	public void setStatutReservationTopo(String statutReservationTopo) {
		this.statutReservationTopo = statutReservationTopo;
	}
}
