package lade.dao;

import java.util.List;

import lade.beans.BN_ReservationTopo;
import lade.beans.BN_ReservationTopoInnerJoin;

public interface DAO_ReservationTopo {

	void insertionReservationTopo(BN_ReservationTopo nouvelleReservationTopo) throws DAO_Exception;
	
	List<BN_ReservationTopoInnerJoin> selectionDemandesReservationTopoInnerJoinParIdProprietaire(Long idProprietaireTopo) throws DAO_Exception;
}
