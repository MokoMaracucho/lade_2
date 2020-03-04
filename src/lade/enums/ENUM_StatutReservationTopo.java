package lade.enums;

public enum ENUM_StatutReservationTopo {
	
	ATTENTE("en attente"),
	ACCEPTEE("acceptée"), 
	REFUSEE("refusée"), 
	TERMINEE("terminée");
	   
	private String statutReservationTopo = "";
   
	ENUM_StatutReservationTopo(String statutReservationTopo){
		this.statutReservationTopo = statutReservationTopo;
	}
   
	public String toString(){
	    return statutReservationTopo;
	}
}
