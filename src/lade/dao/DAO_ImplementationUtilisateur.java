package lade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lade.beans.BN_Utilisateur;
import static lade.dao.DAO_Utilitaire.*;

public class DAO_ImplementationUtilisateur implements DAO_Utilisateur {
  
	private static final String SQL_INSERT_UTILISATEUR = "INSERT INTO tb_utilisateur (prenom_utilisateur, nom_utilisateur, email_utilisateur, mot_de_passe_utilisateur, numero_membre_utilisateur, date_inscription_utilisateur) VALUES (?, ?, ?, ?, ?, NOW())";
	
//	private static final String SQL_SELECT_UTILISATEUR_PAR_ID = "SELECT id_utilisateur, prenom_utilisateur, nom_utilisateur, email_utilisateur, mot_de_passe_utilisateur, numero_membre_utilisateur, date_inscription_utilisateur FROM tb_utilisateur WHERE id_utilisateur = ?";
	private static final String SQL_SELECT_UTILISATEUR_PAR_EMAIL = "SELECT id_utilisateur, prenom_utilisateur, nom_utilisateur, email_utilisateur, mot_de_passe_utilisateur, numero_membre_utilisateur, date_inscription_utilisateur FROM tb_utilisateur WHERE email_utilisateur = ?";
	private static final String SQL_SELECT_TOUS_LES_UTILISATEURS = "SELECT id_utilisateur, prenom_utilisateur, nom_utilisateur, email_utilisateur, mot_de_passe_utilisateur, numero_membre_utilisateur, date_inscription_utilisateur FROM tb_utilisateur";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationUtilisateur(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionUtilisateur(BN_Utilisateur utilisateur) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_UTILISATEUR, true, utilisateur.getPrenomUtilisateur(), utilisateur.getNomUtilisateur(), utilisateur.getEmailUtilisateur(), utilisateur.getMotDePasseUtilisateur(), utilisateur.getNumeroMembreUtilisateur());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création de l'utilisateur. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				utilisateur.setIdUtilisateur(resultat.getLong(1));
			
			} else {
				
				throw new DAO_Exception("Échec de la création de l'utilisateur en base. Aucun ID auto-généré retourné");
			}
		
		} catch(SQLException e) {
			
			throw new DAO_Exception(e);
		
		} finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
	}
	
	@Override
    public BN_Utilisateur selectionUtilisateurParEmail(String emailUtilisateur) throws DAO_Exception {
        
		return selectionUtilisateurParEmail(SQL_SELECT_UTILISATEUR_PAR_EMAIL, emailUtilisateur);
    }
	
	private BN_Utilisateur selectionUtilisateurParEmail(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_Utilisateur utilisateur = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                utilisateur = mappingUtilisateur(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return utilisateur;
    }
	
//	@Override
//    public BN_Utilisateur selectionUtilisateurParId(Long idUtilisateur) throws DAO_Exception {
//        
//		return selectionUtilisateurParId(SQL_SELECT_UTILISATEUR_PAR_ID, idUtilisateur);
//    }
//	
//	private BN_Utilisateur selectionUtilisateurParId(String sql, Object... objets) throws DAO_Exception {
//        
//		Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultat = null;
//        
//        BN_Utilisateur utilisateur = null;
//
//        try {
//
//        	connection = daoFactory.getConnection();
//
//            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
//            
//            resultat = preparedStatement.executeQuery();
//
//            if (resultat.next()) {
//            	
//                utilisateur = mappingUtilisateur(resultat);
//            }
//            
//        } catch (SQLException e) {
//        	
//            throw new DAO_Exception(e);
//            
//        } finally {
//        	
//            fermeturesSilencieuses(resultat, preparedStatement, connection);
//        }
//
//        return utilisateur;
//    }

	@Override
	public List<BN_Utilisateur> recuperationListeUtilisateurs() throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_Utilisateur> listeUtilisateurs = new ArrayList<BN_Utilisateur>();
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_SELECT_TOUS_LES_UTILISATEURS);
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeUtilisateurs.add(mappingUtilisateur(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeUtilisateurs;
	}
	
	private static BN_Utilisateur mappingUtilisateur(ResultSet resultat) throws SQLException {
		
		BN_Utilisateur utilisateur = new BN_Utilisateur();
		
		utilisateur.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		utilisateur.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		utilisateur.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		utilisateur.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		utilisateur.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		utilisateur.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		utilisateur.setDateInscriptionUtilisateur(resultat.getTimestamp("date_inscription_utilisateur"));
		
		return utilisateur;
	}
}
