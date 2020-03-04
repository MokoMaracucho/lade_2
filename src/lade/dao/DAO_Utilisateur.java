package lade.dao;

import java.util.List;

import lade.beans.BN_Utilisateur;

public interface DAO_Utilisateur {

	void insertionUtilisateur(BN_Utilisateur utilisateur) throws DAO_Exception;
	
//	BN_Utilisateur selectionUtilisateurParId(Long idUtilisateur) throws DAO_Exception;

	BN_Utilisateur selectionUtilisateurParEmail(String emailUtilisateur) throws DAO_Exception;

	List<BN_Utilisateur> recuperationListeUtilisateurs() throws DAO_Exception;
}
