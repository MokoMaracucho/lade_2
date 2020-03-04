package lade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO_Utilitaire {

    private DAO_Utilitaire() {
    	
    }

    public static void fermetureSilencieuse(ResultSet resultat) {
    	
        if (resultat != null) {
        	
            try {
            	
            	resultat.close();
            	
            } catch (SQLException e) {
            	
                System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
            }
        }
    }

    public static void fermetureSilencieuse(Statement statement) {
    	
        if (statement != null) {
        	
            try {
            	
                statement.close();
                
            } catch (SQLException e) {
            	
                System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
            }
        }
    }

    public static void fermetureSilencieuse(Connection connection) {
    	
        if (connection != null) {
        	
            try {
            	
            	connection.close();
            	
            } catch (SQLException e) {
            	
                System.out.println("Échec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    public static void fermeturesSilencieuses(Statement statement, Connection connection) {
    	
        fermetureSilencieuse(statement);
        
        fermetureSilencieuse(connection);
    }

    public static void fermeturesSilencieuses(ResultSet resultat, Statement statement, Connection connection) {
    	
        fermetureSilencieuse(resultat);
        
        fermetureSilencieuse(statement);
        
        fermetureSilencieuse(connection);
    }
    
    public static PreparedStatement initialisationRequetePreparee(Connection connection, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
    	
        PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        
        for (int i = 0; i < objets.length; i++) {
        	
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }
}
