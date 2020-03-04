package lade.dao;

import java.util.List;

import lade.beans.BN_Site;
import lade.beans.BN_SiteInnerJoin;

public interface DAO_Site {

	void insertionSite(BN_Site site) throws DAO_Exception;

	BN_Site selectionSiteParNom(String nom) throws DAO_Exception;
	
	BN_SiteInnerJoin selectionSiteInnerJoinParNom(String nom) throws DAO_Exception;
	
	List<BN_SiteInnerJoin> selectionSites() throws DAO_Exception;
}
