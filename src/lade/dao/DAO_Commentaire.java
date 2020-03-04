package lade.dao;

import java.util.List;

import lade.beans.BN_Commentaire;
import lade.beans.BN_CommentaireInnerJoin;

public interface DAO_Commentaire {

	void insertionCommentaireSite(BN_Commentaire commentaire) throws DAO_Exception;
	
	List<BN_CommentaireInnerJoin> selectionCommentairesInnerJoinParIdSite(Long idSite) throws DAO_Exception;
}
