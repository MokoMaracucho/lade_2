package lade.dao;

import java.util.List;

import lade.beans.BN_Longueur;
import lade.beans.BN_LongueurInnerJoin;

public interface DAO_Longueur {

	void insertionLongueur(BN_Longueur longueur) throws DAO_Exception;
	
	BN_Longueur selectionLongueurParNom(String nom) throws DAO_Exception;
	
	List<BN_LongueurInnerJoin> selectionLongueurs() throws DAO_Exception;
	
	List<BN_LongueurInnerJoin> selectionLongueursParIdVoie(Long idVoie) throws DAO_Exception;
}
