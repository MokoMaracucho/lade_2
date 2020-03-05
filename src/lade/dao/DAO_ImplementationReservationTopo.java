package lade.dao;

import static lade.dao.DAO_Utilitaire.fermeturesSilencieuses;
import static lade.dao.DAO_Utilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lade.beans.BN_ReservationTopo;
import lade.beans.BN_ReservationTopoInnerJoin;
import lade.beans.BN_Topo;
import lade.beans.BN_Utilisateur;

public class DAO_ImplementationReservationTopo implements DAO_ReservationTopo {

	private static final String SQL_INSERT_RESERVATION_TOPO = "INSERT INTO tb_reservation_topo (id_topo_reservation_topo, id_proprietaire_topo, id_demandeur_reservation_topo, statut_reservation_topo) VALUES (?, ?, ?, ?)";

	private static final String SQL_SELECT_RESERVATIONS_TOPO_INNER_JOIN_PAR_ID_PROPRIETAIRE = "SELECT r.id_reservation_topo, r.id_topo_reservation_topo, r.id_proprietaire_topo, r.id_demandeur_reservation_topo, r.statut_reservation_topo, t.id_topo, t.id_proprietaire_topo, t.nom_topo, t.region_topo, t.description_topo, t.date_parution_topo, t.disponibilite_topo, p.id_utilisateur, p.prenom_utilisateur, p.nom_utilisateur, p.email_utilisateur, p.mot_de_passe_utilisateur, p.numero_membre_utilisateur, d.id_utilisateur, d.prenom_utilisateur, d.nom_utilisateur, d.email_utilisateur, d.mot_de_passe_utilisateur, d.numero_membre_utilisateur FROM tb_reservation_topo r INNER JOIN tb_topo t ON r.id_topo_reservation_topo = t.id_topo INNER JOIN tb_utilisateur p ON r.id_proprietaire_topo = p.id_utilisateur INNER JOIN tb_utilisateur d ON r.id_demandeur_reservation_topo = d.id_utilisateur WHERE r.id_proprietaire_topo = ?";
	
	private DAO_Factory daoFactory;
	
	DAO_ImplementationReservationTopo(DAO_Factory daoFactory) {
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void insertionReservationTopo(BN_ReservationTopo nouvelleReservationTopo) throws DAO_Exception {
		
		Connection 			connection 			= null;
		PreparedStatement 	preparedStatement 	= null;
		ResultSet 			resultat 			= null;
		
		try {
			
			connection = daoFactory.getConnection();
			
			preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_RESERVATION_TOPO, true, nouvelleReservationTopo.getIdTopo(), nouvelleReservationTopo.getIdProprietaireTopo(), nouvelleReservationTopo.getIdDemandeurReservationTopo(), nouvelleReservationTopo.getStatutReservationTopo());
			
			int statut = preparedStatement.executeUpdate();
			
			if(statut == 0) {
				
				throw new DAO_Exception("Échec de la création de demande de réservation. Aucune ligne ajoutée à la table.");
			}
			
			resultat = preparedStatement.getGeneratedKeys();
			
			if(resultat.next()) {
				
				nouvelleReservationTopo.setIdReservationTopo(resultat.getLong(1));
			
			} else {
				
				throw new DAO_Exception("Échec de la création de la demande de réservation en base. Aucun ID auto-généré retourné");
			}
		
		} catch(SQLException e) {
			
			throw new DAO_Exception(e);
		
		} finally {
			
			fermeturesSilencieuses(resultat, preparedStatement, connection);
		}
	}

	@Override
	public List<BN_ReservationTopoInnerJoin> selectionDemandesReservationTopoInnerJoinParIdProprietaire(Long idProprietaireTopo) throws DAO_Exception {
	        
		return selectionDemandesReservationTopoInnerJoinParIdProprietaire(SQL_SELECT_RESERVATIONS_TOPO_INNER_JOIN_PAR_ID_PROPRIETAIRE, idProprietaireTopo);
    }
	
	private List<BN_ReservationTopoInnerJoin> selectionDemandesReservationTopoInnerJoinParIdProprietaire(String sql, Object... objets) throws DAO_Exception {
        
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;
        
        List<BN_ReservationTopoInnerJoin> listeReservationsTopo = new ArrayList<BN_ReservationTopoInnerJoin>();

        try {

        	connection = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee(connection, sql, false, objets);
            
            resultat = preparedStatement.executeQuery();

            if (resultat.next()) {
            	
            	listeReservationsTopo.add(mappingReservationTopoInnerJoin(resultat));
            }
            
        } catch (SQLException e) {
        	
            throw new DAO_Exception(e);
            
        } finally {
        	
            fermeturesSilencieuses(resultat, preparedStatement, connection);
        }

        return listeReservationsTopo;
    }
	
	private static BN_ReservationTopoInnerJoin mappingReservationTopoInnerJoin(ResultSet resultat) throws SQLException {
		
		BN_ReservationTopoInnerJoin reservationTopo = new BN_ReservationTopoInnerJoin();
				
		reservationTopo.setIdReservationTopo(resultat.getLong("id_reservation_topo"));
		reservationTopo.setIdTopo(resultat.getLong("id_topo"));
		reservationTopo.setIdProprietaireTopo(resultat.getLong("id_proprietaire_topo"));
		reservationTopo.setIdDemandeurReservationTopo(resultat.getLong("id_demandeur_reservation_topo"));
		reservationTopo.setStatutReservationTopo(resultat.getString("statut_reservation_topo"));
		
		BN_Topo topoReservationTopo = new BN_Topo();
				
		topoReservationTopo.setIdTopo(resultat.getLong("id_topo"));
		topoReservationTopo.setIdProprietaireTopo(resultat.getLong("id_proprietaire_topo"));
		topoReservationTopo.setNomTopo(resultat.getString("nom_topo"));
		topoReservationTopo.setRegionTopo(resultat.getString("region_topo"));
		topoReservationTopo.setDescriptionTopo(resultat.getString("description_topo"));
		topoReservationTopo.setDateParutionTopo(resultat.getTimestamp("date_parution_topo"));
		topoReservationTopo.setDisponibiliteTopo(resultat.getBoolean("disponibilite_topo"));
		
		reservationTopo.setTopoReservationTopo(topoReservationTopo);
		
		BN_Utilisateur proprietaireTopo = new BN_Utilisateur();
		
		proprietaireTopo.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		proprietaireTopo.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		proprietaireTopo.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		proprietaireTopo.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		proprietaireTopo.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		proprietaireTopo.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		
		reservationTopo.setProprietaireTopo(proprietaireTopo);
		
		BN_Utilisateur demandeurReservationTopo = new BN_Utilisateur();
		
		demandeurReservationTopo.setIdUtilisateur(resultat.getLong("id_utilisateur"));
		demandeurReservationTopo.setPrenomUtilisateur(resultat.getString("prenom_utilisateur"));
		demandeurReservationTopo.setNomUtilisateur(resultat.getString("nom_utilisateur"));
		demandeurReservationTopo.setEmailUtilisateur(resultat.getString("email_utilisateur"));
		demandeurReservationTopo.setMotDePasseUtilisateur(resultat.getString("mot_de_passe_utilisateur"));
		demandeurReservationTopo.setNumeroMembreUtilisateur(resultat.getString("numero_membre_utilisateur"));
		
		reservationTopo.setDemandeurReservationTopo(demandeurReservationTopo);
		
		return reservationTopo;
	}
}
