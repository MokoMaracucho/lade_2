package lade.dao;

import static lade.dao.DAO_Utilitaire.fermeturesSilencieuses;
import static lade.dao.DAO_Utilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lade.beans.BN_Commentaire;
import lade.beans.BN_CommentaireInnerJoin;
import lade.beans.BN_Site;
import lade.beans.BN_Utilisateur;

public class DAO_ImplementationCommentaireSite implements DAO_Commentaire {

	private static final String SQL_INSERT_COMMENTAIRE = "INSERT INTO tb_commentaire (id_auteur_commentaire, id_site_commentaire, commentaire) VALUES (?, ?, ?)";

	private static final String SQL_SELECT_COMMENTAIRES_INNER_JOIN_PAR_ID_SITE = "SELECT c.id_commentaire, c.id_auteur_commentaire, c.id_site_commentaire, c.commentaire, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur, u.date_inscription_utilisateur, si.id_site, si.id_createur_site, si.nom_site, si.region_site FROM tb_commentaire c, tb_utilisateur u, tb_site si WHERE c.id_auteur_commentaire = u.id_utilisateur AND c.id_site_commentaire = si.id_site AND c.id_site_commentaire = ?";

	private DAO_Factory daoFactory;
	
	DAO_ImplementationCommentaireSite(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionCommentaireSite(BN_Commentaire commentaire) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_COMMENTAIRE, true, commentaire.getIdAuteurCommentaire(), commentaire.getIdSiteCommentaire(), commentaire.getCommentaire());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création du site. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				commentaire.setIdCommentaire(resultat.getLong(1));
			
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
    public List<BN_CommentaireInnerJoin> selectionCommentairesInnerJoinParIdSite(Long idSite) throws DAO_Exception {
        
		return selectionCommentairesInnerJoinParIdSite(SQL_SELECT_COMMENTAIRES_INNER_JOIN_PAR_ID_SITE, idSite);
    }
	
	public List<BN_CommentaireInnerJoin> selectionCommentairesInnerJoinParIdSite(String sql, Object... objets) throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_CommentaireInnerJoin> listeCommentaires = new ArrayList<BN_CommentaireInnerJoin>();
		
		try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeCommentaires.add(mappingCommentaireInnerJoin(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeCommentaires;
	}
	
	private static BN_CommentaireInnerJoin mappingCommentaireInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_CommentaireInnerJoin commentaire = new BN_CommentaireInnerJoin();
		
		commentaire.setIdCommentaire(resultat.getLong("id_commentaire"));
		commentaire.setIdAuteurCommentaire(resultat.getLong("id_auteur_commentaire"));
		commentaire.setIdSiteCommentaire(resultat.getLong("id_site_commentaire"));
		commentaire.setCommentaire(resultat.getString("commentaire"));
		
		BN_Utilisateur createurSecteur = new BN_Utilisateur();
		
		createurSecteur.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		createurSecteur.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		createurSecteur.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		createurSecteur.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		createurSecteur.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		createurSecteur.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		createurSecteur.setDateInscriptionUtilisateur(resultat.getTimestamp("date_inscription_utilisateur"));
		
		commentaire.setAuteurCommentaire(createurSecteur);
		
		BN_Site site = new BN_Site();
				
		site.setIdSite(resultat.getLong("id_site"));
		site.setIdCreateurSite(resultat.getLong("id_createur_site"));
		site.setRegionSite(resultat.getString("region_site"));
		
		commentaire.setSiteCommentaire(site);
		
		return commentaire;
	}
}
