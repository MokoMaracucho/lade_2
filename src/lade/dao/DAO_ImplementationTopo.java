package lade.dao;

import static lade.dao.DAO_Utilitaire.fermeturesSilencieuses;
import static lade.dao.DAO_Utilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lade.beans.BN_Topo;
import lade.beans.BN_TopoInnerJoin;
import lade.beans.BN_Utilisateur;

public class DAO_ImplementationTopo implements DAO_Topo {

	private static final String SQL_INSERT_TOPO = "INSERT INTO tb_topo (id_proprietaire_topo, nom_topo, region_topo, description_topo, date_parution_topo, disponibilite_topo) VALUES (?, ?, ?, ?, NOW(), ?)";
	
	private static final String SQL_SELECT_TOPO_PAR_NOM = "SELECT id_topo, id_proprietaire_topo, nom_topo, region_topo, description_topo, date_parution_topo, disponibilite_topo FROM tb_topo WHERE nom_topo = ?";
	private static final String SQL_SELECT_TOPOS_PAR_ID_PROPRIETAIRE = "SELECT id_topo, id_proprietaire_topo, nom_topo, region_topo, description_topo, date_parution_topo, disponibilite_topo FROM tb_topo WHERE id_proprietaire_topo = ?";
	private static final String SQL_SELECT_TOPO_INNER_JOIN_PAR_NOM = "SELECT t.id_topo, t.id_proprietaire_topo, t.nom_topo, t.region_topo, t.description_topo, t.disponibilite_topo, t.date_parution_topo, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur FROM tb_topo t INNER JOIN tb_utilisateur u ON u.id_utilisateur = t.id_proprietaire_topo AND t.nom_topo = ?";
	private static final String SQL_SELECT_TOPOS = "SELECT t.id_topo, t.id_proprietaire_topo, t.nom_topo, t.region_topo, t.description_topo, t.disponibilite_topo, t.date_parution_topo, u.id_utilisateur, u.prenom_utilisateur, u.nom_utilisateur, u.email_utilisateur, u.mot_de_passe_utilisateur, u.numero_membre_utilisateur FROM tb_topo t INNER JOIN tb_utilisateur u ON u.id_utilisateur = t.id_proprietaire_topo";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationTopo(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionTopo(BN_Topo topo) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_TOPO, true, topo.getIdProprietaireTopo(), topo.getNomTopo(), topo.getRegionTopo(), topo.getDescriptionTopo(), topo.getDisponibiliteTopo());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création du topo. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				topo.setIdTopo(resultat.getLong(1));
			
			} else {
				
				throw new DAO_Exception("Échec de la création du topo en base. Aucun ID auto-généré retourné");
			}
		
		} catch(SQLException e) {
			
			throw new DAO_Exception(e);
		
		} finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
	}
	
	@Override
    public BN_Topo selectionTopoParNom(String nomTopo) throws DAO_Exception {
        
		return selectionTopoParNom(SQL_SELECT_TOPO_PAR_NOM, nomTopo);
    }
	
	private BN_Topo selectionTopoParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_Topo topo = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                topo = mappingTopo(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return topo;
    }
	
	@Override
    public BN_TopoInnerJoin selectionTopoInnerJoinParNom(String nomTopo) throws DAO_Exception {
        
		return selectionTopoInnerJoinParNom(SQL_SELECT_TOPO_INNER_JOIN_PAR_NOM, nomTopo);
    }
	
	private BN_TopoInnerJoin selectionTopoInnerJoinParNom(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        BN_TopoInnerJoin topo = null;

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
                topo = mappingTopoInnerJoin(resultat);
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return topo;
    }
	
	@Override
    public List<BN_Topo> selectionToposParIdProprietaire(Long idProprietaireTopo) throws DAO_Exception {
        
		return selectionToposParIdProprietaire(SQL_SELECT_TOPOS_PAR_ID_PROPRIETAIRE, idProprietaireTopo);
    }
	
	private List<BN_Topo> selectionToposParIdProprietaire(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        List<BN_Topo> listeTopos = new ArrayList<BN_Topo>();

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
            	listeTopos.add(mappingTopo(resultat));
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return listeTopos;
    }
	
	@Override
	public List<BN_TopoInnerJoin> selectionToposInnerJoin() throws DAO_Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		List<BN_TopoInnerJoin> listeTopos = new ArrayList<BN_TopoInnerJoin>();
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(SQL_SELECT_TOPOS);
			
			resultat = preparedStatement.executeQuery();
			
			while(resultat.next()) {
				
				listeTopos.add(mappingTopoInnerJoin(resultat));
			}
			
		} catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
		
		return listeTopos;
	}
	
	private static BN_Topo mappingTopo(ResultSet resultat) throws SQLException {
		
		BN_Topo topo = new BN_Topo();
				
		topo.setIdTopo(resultat.getLong("id_topo"));
		topo.setIdProprietaireTopo(resultat.getLong("id_proprietaire_topo"));
		topo.setNomTopo(resultat.getString("nom_topo"));
		topo.setRegionTopo(resultat.getString("region_topo"));
		topo.setDescriptionTopo(resultat.getString("description_topo"));
		topo.setDateParutionTopo(resultat.getTimestamp("date_parution_topo"));
		topo.setDisponibiliteTopo(resultat.getBoolean("disponibilite_topo"));
		
		return topo;
	}
	
	private static BN_TopoInnerJoin mappingTopoInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_TopoInnerJoin topo = new BN_TopoInnerJoin();
		
		topo.setIdTopo(resultat.getLong("id_topo"));
		topo.setIdProprietaireTopo(resultat.getLong("id_proprietaire_topo"));
		topo.setNomTopo(resultat.getString("nom_topo"));
		topo.setRegionTopo(resultat.getString("region_topo"));
		topo.setDescriptionTopo(resultat.getString("description_topo"));
		topo.setDateParutionTopo(resultat.getTimestamp("date_parution_topo"));
		topo.setDisponibiliteTopo(resultat.getBoolean("disponibilite_topo"));
		
		BN_Utilisateur proprietaireTopo = new BN_Utilisateur();
		
		proprietaireTopo.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		proprietaireTopo.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		proprietaireTopo.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		proprietaireTopo.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		proprietaireTopo.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		proprietaireTopo.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		
		topo.setProprietaireTopo(proprietaireTopo);
		
		return topo;
	}
}
