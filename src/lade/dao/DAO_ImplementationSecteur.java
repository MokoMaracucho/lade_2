package lade.dao;

import static lade.dao.DAO_Utilitaire.fermeturesSilencieuses;
import static lade.dao.DAO_Utilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lade.beans.BN_Secteur;
import lade.beans.BN_SecteurInnerJoin;
import lade.beans.BN_Site;
import lade.beans.BN_Utilisateur;

public class DAO_ImplementationSecteur implements DAO_Secteur {

	private static final String SQL_INSERT_SITE = "INSERT INTO tb_secteur (id_createur_secteur, id_site, nom_secteur) VALUES (?, ?, ?)";
	
	private static final String SQL_SELECT_SECTEUR_PAR_NOM = "SELECT id_secteur, id_createur_secteur, id_site, nom_secteur FROM tb_secteur WHERE nom_secteur = ?";
	private static final String SQL_SELECT_SECTEURS = "SELECT se.id_secteur, se.id_createur_secteur, se.id_site, se.nom_secteur, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, si.id_site, si.id_createur_site, si.nom_site, si.region_site FROM tb_secteur se, tb_utilisateur u, tb_site si WHERE se.id_createur_secteur = u.id_utilisateur AND se.id_site = si.id_site";
	private static final String SQL_SELECT_SECTEURS_PAR_ID_SITE = "SELECT se.id_secteur, se.id_createur_secteur, se.id_site, se.nom_secteur, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, si.id_site, si.id_createur_site, si.nom_site, si.region_site FROM tb_secteur se, tb_utilisateur u, tb_site si WHERE se.id_createur_secteur = u.id_utilisateur AND se.id_site = si.id_site AND se.id_site = ?";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationSecteur(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionSecteur(BN_Secteur secteur) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_SITE, true, secteur.getIdCreateurSecteur(), secteur.getIdSite(), secteur.getNomSecteur());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création du secteur. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				secteur.setIdSecteur(resultat.getLong(1));
			
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
	public BN_Secteur selectionSecteurParNom(String nom) throws DAO_Exception {
        
		return selectionSecteurParNom(SQL_SELECT_SECTEUR_PAR_NOM, nom);
    }
	
	private BN_Secteur selectionSecteurParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_Secteur secteur = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
            	secteur = mappingSecteur(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return secteur;
    }
	
	@Override
	public List<BN_SecteurInnerJoin> selectionSecteurs() throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_SecteurInnerJoin> listeSecteurs = new ArrayList<BN_SecteurInnerJoin>();
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_SELECT_SECTEURS);
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeSecteurs.add(mappingSecteurInnerJoin(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeSecteurs;
	}
	
	@Override
	public List<BN_SecteurInnerJoin> selectionSecteursParIdSite(Long idSite) throws DAO_Exception {
        
		return selectionVoiesParIdSite(SQL_SELECT_SECTEURS_PAR_ID_SITE, idSite);
    }

	public List<BN_SecteurInnerJoin> selectionVoiesParIdSite(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        List<BN_SecteurInnerJoin> listeSecteurs = new ArrayList<BN_SecteurInnerJoin>();

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
            	listeSecteurs.add(mappingSecteurInnerJoin(resultat));
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return listeSecteurs;
	}
	
	private static BN_Secteur mappingSecteur(ResultSet resultat) throws SQLException {
		
		BN_Secteur secteur = new BN_Secteur();
				
		secteur.setIdSecteur(resultat.getLong("id_secteur"));
		secteur.setIdCreateurSecteur(resultat.getLong("id_createur_secteur"));
		secteur.setNomSecteur(resultat.getString("nom_secteur"));
		
		return secteur;
	}
	
	private static BN_SecteurInnerJoin mappingSecteurInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_SecteurInnerJoin secteur = new BN_SecteurInnerJoin();
				
		secteur.setIdSecteur(resultat.getLong("id_secteur"));
		secteur.setIdCreateurSecteur(resultat.getLong("id_createur_secteur"));
		secteur.setIdSite(resultat.getLong("id_site"));
		secteur.setNomSecteur(resultat.getString("nom_secteur"));
		
		BN_Utilisateur createurSecteur = new BN_Utilisateur();
		
		createurSecteur.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		createurSecteur.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		createurSecteur.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		createurSecteur.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		createurSecteur.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		createurSecteur.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		createurSecteur.setDateInscriptionUtilisateur(resultat.getTimestamp("date_inscription_utilisateur"));
		
		secteur.setCreateurSecteur(createurSecteur);
		
		BN_Site site = new BN_Site();
				
		site.setIdSite(resultat.getLong("id_site"));
		site.setIdCreateurSite(resultat.getLong("id_createur_site"));
		site.setRegionSite(resultat.getString("region_site"));
		
		secteur.setSite(site);
		
		return secteur;
	}
}
