package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_Secteur;
import lade.beans.BN_VoieInnerJoin;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Secteur;
import lade.dao.DAO_Voie;

@WebServlet("/ListeVoies")
public class SRV_ListeVoies extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String VUE_LISTE_VOIES				= "/jsp_liste_voies.jsp";
	
	public static final String ATT_LISTE_VOIES 				= "listeVoies";
	
	public static final String CONFIGURATION_DAO_FACTORY 	= "daoFactory";
	
	private DAO_Secteur daoSecteur;
	private DAO_Voie daoVoie;
    
    public void init() throws ServletException {

        this.daoSecteur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSecteur();
        this.daoVoie = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoVoie();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomSecteur = request.getParameter("nom");
		
		if(nomSecteur == null) {
		
			List<BN_VoieInnerJoin> listeVoies = daoVoie.selectionVoies();
			
			request.setAttribute(ATT_LISTE_VOIES, listeVoies);
			
		} else {
			
			BN_Secteur secteur = daoSecteur.selectionSecteurParNom(nomSecteur);
			
			Long idSecteur = secteur.getIdSecteur();
			System.out.println(idSecteur);
			
			List<BN_VoieInnerJoin> listeVoies = daoVoie.selectionVoiesParIdSecteur(idSecteur);
			
			request.setAttribute(ATT_LISTE_VOIES, listeVoies);
		}
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_VOIES).forward(request, response);
	}
}
