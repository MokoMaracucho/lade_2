package lade.dao;

import java.util.List;

import lade.beans.BN_Voie;
import lade.beans.BN_VoieInnerJoin;

public interface DAO_Voie {

	void insertionVoie(BN_Voie voie) throws DAO_Exception;
	
	BN_Voie selectionVoieParNom(String nom) throws DAO_Exception;
	
	List<BN_VoieInnerJoin> selectionVoies() throws DAO_Exception;
	
	List<BN_VoieInnerJoin> selectionVoiesParIdSecteur(Long idSecteur) throws DAO_Exception;
}
