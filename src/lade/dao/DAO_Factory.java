package lade.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAO_Factory {

	private static final String FICHIER_PROPERTIES 			= "/lade/dao/dao.properties";
	private static final String PROPERTY_URL 				= "url";
	private static final String PROPERTY_DRIVER 			= "driver";
	private static final String PROPERTY_ADMINISTRATEUR 	= "administrateur";
	private static final String PROPERTY_MOT_DE_PASSE 		= "motdepasse";
	
	private String url;
	private String administrateur;
	private String motDePasse;
	
	DAO_Factory(String url, String administrateur, String motDePasse) {
		
		this.url = url;
		this.administrateur = administrateur;
		this.motDePasse = motDePasse;
	}
	
	public static DAO_Factory getInstance() throws DAO_ConfigurationException {
		
		Properties properties = new Properties();
		
		String url;
		String driver;
		String administrateur;
		String motDePasse;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);
		
		if(fichierProperties == null) {
			
			throw new DAO_ConfigurationException("Le fichier properties" + FICHIER_PROPERTIES + " est introuvable");
		}
		
		try {
			
			properties.load(fichierProperties);
			
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			administrateur = properties.getProperty(PROPERTY_ADMINISTRATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		
		} catch(IOException e) {
			
			throw new DAO_ConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
		}
		
		try {
			
			Class.forName(driver);
			
		} catch(ClassNotFoundException e) {
		
			throw new DAO_ConfigurationException("Le chemin est introuvable dans le ClassPath.", e);
		}
		
		DAO_Factory instanceDAOFactory = new DAO_Factory(url, administrateur, motDePasse);
			
		return instanceDAOFactory;
	}
		
	Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(url, administrateur, motDePasse);
	}
	
	public DAO_Utilisateur getDaoUtilisateur() {
		
		return new DAO_ImplementationUtilisateur(this);
	}
	
	public DAO_Site getDaoSite() {
		
		return new DAO_ImplementationSite(this);
	}
	
	public DAO_Commentaire getDaoCommentaire() {
		
		return new DAO_ImplementationCommentaireSite(this);
	}
//	
//	public DAO_Secteur getDaoSecteur() {
//		
//		return new DAO_ImplementationSecteur(this);
//	}
//	
//	public DAO_Voie getDaoVoie() {
//		
//		return new DAO_ImplementationVoie(this);
//	}
//	
	
//	
//	public DAO_Longueur getDaoLongueur() {
//		
//		return new DAO_ImplementationLongueur(this);
//	}
//	
//	public DAO_Topo getDaoTopo() {
//		
//		return new DAO_ImplementationTopo(this);
//	}
//	
//	public DAO_ReservationTopo getDaoReservationTopo() {
//		
//		return new DAO_ImplementationReservationTopo(this);
//	}
//	
//	public DAO_Recherche getDaoRecherche() {
//		
//		return new DAO_ImplementationRecherche(this);
//	}
}
