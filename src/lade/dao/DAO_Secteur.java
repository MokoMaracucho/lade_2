package lade.dao;

import java.util.List;

import lade.beans.BN_Secteur;
import lade.beans.BN_SecteurInnerJoin;

public interface DAO_Secteur {

	void insertionSecteur(BN_Secteur secteur) throws DAO_Exception;
	
	BN_Secteur selectionSecteurParNom(String nom) throws DAO_Exception;
	
	List<BN_SecteurInnerJoin> selectionSecteurs() throws DAO_Exception;
	
	List<BN_SecteurInnerJoin> selectionSecteursParIdSite(Long idSite) throws DAO_Exception;
}
