package lade.dao;

import static lade.dao.DAO_Utilitaire.fermeturesSilencieuses;
import static lade.dao.DAO_Utilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lade.beans.BN_Voie;
import lade.beans.BN_Utilisateur;
import lade.beans.BN_Longueur;
import lade.beans.BN_LongueurInnerJoin;

public class DAO_ImplementationLongueur implements DAO_Longueur {

	private static final String SQL_INSERT_LONGUEUR = "INSERT INTO tb_longueur (id_createur_longueur, id_voie, nom_longueur, cotation_longueur) VALUES (?, ?, ?, ?)";
	
	private static final String SQL_SELECT_LONGUEURS = "SELECT l.id_longueur, l.id_createur_longueur, l.id_voie, l.nom_longueur, l.cotation_longueur, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, v.id_voie, v.id_createur_voie, v.id_secteur, v.nom_voie FROM tb_longueur l, tb_utilisateur u, tb_voie v WHERE l.id_createur_longueur = u.id_utilisateur AND l.id_voie = v.id_voie";
	private static final String SQL_SELECT_LONGUEUR_PAR_NOM = "SELECT id_Longueur, id_createur_Longueur, id_voie, nom_Longueur, cotation_Longueur FROM tb_Longueur WHERE nom_Longueur = ?";
	private static final String SQL_SELECT_LONGUEURS_PAR_ID_VOIE = "SELECT l.id_longueur, l.id_createur_longueur, l.id_voie, l.nom_longueur, l.cotation_longueur, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, v.id_voie, v.id_createur_voie, v.id_secteur, v.nom_voie FROM tb_longueur l, tb_utilisateur u, tb_voie v WHERE l.id_createur_Longueur = u.id_utilisateur AND l.id_site = v.id_voie AND v.id_voie = ?";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationLongueur(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionLongueur(BN_Longueur Longueur) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_LONGUEUR, true, Longueur.getIdCreateurLongueur(), Longueur.getIdVoie(), Longueur.getNomLongueur(), Longueur.getCotationLongueur());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création de la Longueur. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				Longueur.setIdLongueur(resultat.getLong(1));
			
			} else {
				
				throw new DAO_Exception("Échec de la création de la Longueur en base. Aucun ID auto-généré retourné");
			}
		
		} catch(SQLException e) {
			
			throw new DAO_Exception(e);
		
		} finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
	}
	
	@Override
	public BN_Longueur selectionLongueurParNom(String nom) throws DAO_Exception {
        
		return selectionLongueurParNom(SQL_SELECT_LONGUEUR_PAR_NOM, nom);
    }
	
	private BN_Longueur selectionLongueurParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_Longueur Longueur = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                Longueur = mappingLongueur(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return Longueur;
    }
	
	@Override
	public List<BN_LongueurInnerJoin> selectionLongueurs() throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_LongueurInnerJoin> listeLongueurs = new ArrayList<BN_LongueurInnerJoin>();
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_SELECT_LONGUEURS);
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeLongueurs.add(mappingLongueurInnerJoin(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeLongueurs;
	}
	
	@Override
	public List<BN_LongueurInnerJoin> selectionLongueursParIdVoie(Long idVoie) throws DAO_Exception {
        
		return selectionLongueursParIdVoie(SQL_SELECT_LONGUEURS_PAR_ID_VOIE, idVoie);
    }

	public List<BN_LongueurInnerJoin> selectionLongueursParIdVoie(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        List<BN_LongueurInnerJoin> listeLongueurs = new ArrayList<BN_LongueurInnerJoin>();

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
            	listeLongueurs.add(mappingLongueurInnerJoin(resultat));
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return listeLongueurs;
	}
	
	private static BN_Longueur mappingLongueur(ResultSet resultat) throws SQLException {
		
		BN_Longueur Longueur = new BN_Longueur();
				
		Longueur.setIdLongueur(resultat.getLong("id_Longueur"));
		Longueur.setIdCreateurLongueur(resultat.getLong("id_createur_Longueur"));
		Longueur.setIdVoie(resultat.getLong("id_Voie"));
		Longueur.setNomLongueur(resultat.getString("nom_Longueur"));
		Longueur.setCotationLongueur(resultat.getString("cotation_Longueur"));
		
		return Longueur;
	}
	
	private static BN_LongueurInnerJoin mappingLongueurInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_LongueurInnerJoin Longueur = new BN_LongueurInnerJoin();
				
		Longueur.setIdLongueur(resultat.getLong("id_longueur"));
		Longueur.setIdCreateurLongueur(resultat.getLong("id_createur_longueur"));
		Longueur.setIdVoie(resultat.getLong("id_voie"));
		Longueur.setNomLongueur(resultat.getString("nom_longueur"));
		Longueur.setCotationLongueur(resultat.getString("cotation_longueur"));
		
		BN_Utilisateur createurLongueur = new BN_Utilisateur();
		
		createurLongueur.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		createurLongueur.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		createurLongueur.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		createurLongueur.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		createurLongueur.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		createurLongueur.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		createurLongueur.setDateInscriptionUtilisateur(resultat.getTimestamp("date_inscription_utilisateur"));
		
		Longueur.setCreateurLongueur(createurLongueur);
		
		BN_Voie Voie = new BN_Voie();
				
		Voie.setIdVoie(resultat.getLong("id_voie"));
		Voie.setIdCreateurVoie(resultat.getLong("id_createur_voie"));
		Voie.setIdSecteur(resultat.getLong("id_secteur"));
		Voie.setNomVoie(resultat.getString("nom_voie"));
		
		Longueur.setVoieLongueur(Voie);
		
		return Longueur;
	}
}
