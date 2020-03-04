package lade.dao;

import java.util.List;

import lade.beans.BN_Topo;
import lade.beans.BN_TopoInnerJoin;

public interface DAO_Topo {

	void insertionTopo(BN_Topo topo) throws DAO_Exception;
	
	BN_Topo selectionTopoParNom(String nomTopo) throws DAO_Exception;
	
	List<BN_Topo> selectionToposParIdProprietaire(Long idProprietaireTopo) throws DAO_Exception;
	
	BN_TopoInnerJoin selectionTopoInnerJoinParNom(String nomTopo) throws DAO_Exception;
	
	List<BN_TopoInnerJoin> selectionToposInnerJoin() throws DAO_Exception;
}
