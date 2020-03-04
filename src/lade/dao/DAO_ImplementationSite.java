package lade.dao;

import static lade.dao.DAO_Utilitaire.fermeturesSilencieuses;
import static lade.dao.DAO_Utilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lade.beans.BN_Site;
import lade.beans.BN_SiteInnerJoin;
import lade.beans.BN_Utilisateur;

public class DAO_ImplementationSite implements DAO_Site {

	private static final String SQL_INSERT_SITE = "INSERT INTO tb_site (id_createur_site, nom_site, region_site) VALUES (?, ?, ?)";
	
	private static final String SQL_SELECT_SITE_PAR_NOM = "SELECT id_site, id_createur_site, nom_site, region_site FROM tb_site WHERE nom_site = ?";
	private static final String SQL_SELECT_SITE_INNER_JOIN_PAR_NOM = "SELECT s.id_site, s.id_createur_site, s.nom_site, s.region_site, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur FROM tb_site s INNER JOIN tb_utilisateur u ON s.nom_site = ? AND u.id_utilisateur = s.id_createur_site";
	private static final String SQL_SELECT_SITES = "SELECT s.id_site, s.id_createur_site, s.nom_site, s.region_site, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur FROM tb_site s INNER JOIN tb_utilisateur u ON u.id_utilisateur = s.id_createur_site";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationSite(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionSite(BN_Site site) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_SITE, true, site.getIdCreateurSite(), site.getNomSite(), site.getRegionSite());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création du site. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				site.setIdSite(resultat.getLong(1));
			
			} else {
				
				throw new DAO_Exception("Échec de la création du site en base. Aucun ID auto-généré retourné");
			}
		
		} catch(SQLException e) {
			
			throw new DAO_Exception(e);
		
		} finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
	}
	
	@Override
    public BN_Site selectionSiteParNom(String nom) throws DAO_Exception {
        
		return selectionSiteParNom(SQL_SELECT_SITE_PAR_NOM, nom);
    }
	
	private BN_Site selectionSiteParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_Site site = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                site = mappingSite(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return site;
    }
	
	@Override
    public BN_SiteInnerJoin selectionSiteInnerJoinParNom(String nom) throws DAO_Exception {
        
		return selectionSiteInnerJoinParNom(SQL_SELECT_SITE_INNER_JOIN_PAR_NOM, nom);
    }
	
	private BN_SiteInnerJoin selectionSiteInnerJoinParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_SiteInnerJoin site = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                site = mappingSiteInnerJoin(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return site;
    }
	
	@Override
	public List<BN_SiteInnerJoin> selectionSites() throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_SiteInnerJoin> listeSites = new ArrayList<BN_SiteInnerJoin>();
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_SELECT_SITES);
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeSites.add(mappingSiteInnerJoin(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeSites;
	}
	
	private static BN_Site mappingSite(ResultSet resultat) throws SQLException {
		
		BN_Site site = new BN_Site();
				
		site.setIdSite(resultat.getLong("id_site"));
		site.setIdCreateurSite(resultat.getLong("id_createur_site"));
		site.setNomSite(resultat.getString("nom_site"));
		site.setRegionSite(resultat.getString("region_site"));
		
		return site;
	}
	
	private static BN_SiteInnerJoin mappingSiteInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_SiteInnerJoin site = new BN_SiteInnerJoin();
		BN_Utilisateur createurSite = new BN_Utilisateur();
				
		site.setIdSite(resultat.getLong("id_site"));
		site.setIdCreateurSite(resultat.getLong("id_createur_site"));
		site.setNomSite(resultat.getString("nom_site"));
		site.setRegionSite(resultat.getString("region_site"));
		
		createurSite.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		createurSite.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		createurSite.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		createurSite.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		createurSite.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		createurSite.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		
		site.setCreateurSite(createurSite);
		
		return site;
	}
}
