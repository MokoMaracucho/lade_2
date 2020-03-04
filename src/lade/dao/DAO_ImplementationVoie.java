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
import lade.beans.BN_Utilisateur;
import lade.beans.BN_Voie;
import lade.beans.BN_VoieInnerJoin;

public class DAO_ImplementationVoie implements DAO_Voie {

	private static final String SQL_INSERT_VOIE = "INSERT INTO tb_voie (id_createur_voie, id_secteur, nom_voie, equipe_voie, cotation_voie) VALUES (?, ?, ?, ?, ?)";
	
	private static final String SQL_SELECT_VOIES = "SELECT v.id_voie, v.id_createur_voie, v.id_secteur, v.nom_voie, v.equipe_voie, v.cotation_voie, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, se.id_secteur, se.id_createur_secteur, se.id_site, se.nom_secteur FROM tb_voie v, tb_utilisateur u, tb_secteur se WHERE v.id_createur_voie = u.id_utilisateur AND v.id_secteur = se.id_secteur";
	private static final String SQL_SELECT_VOIE_PAR_NOM = "SELECT id_voie, id_createur_voie, id_secteur, nom_voie, equipe_voie, cotation_voie FROM tb_voie WHERE nom_voie = ?";
	private static final String SQL_SELECT_VOIES_PAR_ID_SECTEUR = "SELECT v.id_voie, v.id_createur_voie, v.id_secteur, v.nom_voie, v.equipe_voie, v.cotation_voie, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, se.id_secteur, se.id_createur_secteur, se.id_site, se.nom_secteur FROM tb_voie v, tb_utilisateur u, tb_secteur se WHERE v.id_createur_voie = u.id_utilisateur AND v.id_secteur = se.id_secteur AND v.id_secteur = ?";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationVoie(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionVoie(BN_Voie voie) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_VOIE, true, voie.getIdCreateurVoie(), voie.getIdSecteur(), voie.getNomVoie(), voie.getEquipeVoie(), voie.getCotationVoie());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création de la voie. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				voie.setIdVoie(resultat.getLong(1));
			
			} else {
				
				throw new DAO_Exception("Échec de la création de la voie en base. Aucun ID auto-généré retourné");
			}
		
		} catch(SQLException e) {
			
			throw new DAO_Exception(e);
		
		} finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
	}
	
	@Override
	public BN_Voie selectionVoieParNom(String nom) throws DAO_Exception {
        
		return selectionVoieParNom(SQL_SELECT_VOIE_PAR_NOM, nom);
    }
	
	private BN_Voie selectionVoieParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_Voie voie = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                voie = mappingVoie(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return voie;
    }
	
	@Override
	public List<BN_VoieInnerJoin> selectionVoies() throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_VoieInnerJoin> listeVoies = new ArrayList<BN_VoieInnerJoin>();
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_SELECT_VOIES);
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeVoies.add(mappingVoieInnerJoin(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeVoies;
	}
	
	@Override
	public List<BN_VoieInnerJoin> selectionVoiesParIdSecteur(Long idSecteur) throws DAO_Exception {
        
		return selectionVoiesParIdSecteur(SQL_SELECT_VOIES_PAR_ID_SECTEUR, idSecteur);
    }

	public List<BN_VoieInnerJoin> selectionVoiesParIdSecteur(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        List<BN_VoieInnerJoin> listeVoies = new ArrayList<BN_VoieInnerJoin>();

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
            	listeVoies.add(mappingVoieInnerJoin(resultat));
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return listeVoies;
	}
	
	private static BN_Voie mappingVoie(ResultSet resultat) throws SQLException {
		
		BN_Voie voie = new BN_Voie();
				
		voie.setIdVoie(resultat.getLong("id_voie"));
		voie.setIdCreateurVoie(resultat.getLong("id_createur_voie"));
		voie.setIdSecteur(resultat.getLong("id_secteur"));
		voie.setNomVoie(resultat.getString("nom_voie"));
		voie.setCotationVoie(resultat.getString("cotation_voie"));
		
		return voie;
	}
	
	private static BN_VoieInnerJoin mappingVoieInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_VoieInnerJoin voie = new BN_VoieInnerJoin();
				
		voie.setIdVoie(resultat.getLong("id_voie"));
		voie.setIdCreateurVoie(resultat.getLong("id_createur_voie"));
		voie.setIdSecteur(resultat.getLong("id_secteur"));
		voie.setNomVoie(resultat.getString("nom_voie"));
		voie.setEquipeVoie(resultat.getBoolean("equipe_voie"));
		voie.setCotationVoie(resultat.getString("cotation_voie"));
		
		BN_Utilisateur createurVoie = new BN_Utilisateur();
		
		createurVoie.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		createurVoie.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		createurVoie.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		createurVoie.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		createurVoie.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		createurVoie.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		createurVoie.setDateInscriptionUtilisateur(resultat.getTimestamp("date_inscription_utilisateur"));
		
		voie.setCreateurVoie(createurVoie);
		
		BN_Secteur secteur = new BN_Secteur();
				
		secteur.setIdSecteur(resultat.getLong("id_secteur"));
		secteur.setIdCreateurSecteur(resultat.getLong("id_createur_secteur"));
		secteur.setIdSite(resultat.getLong("id_site"));
		secteur.setNomSecteur(resultat.getString("nom_secteur"));
		
		voie.setSecteurVoie(secteur);
		
		return voie;
	}
}
