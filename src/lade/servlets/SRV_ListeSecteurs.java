package lade.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lade.beans.BN_Site;
import lade.beans.BN_SecteurInnerJoin;
import lade.dao.DAO_Factory;
import lade.dao.DAO_Secteur;
import lade.dao.DAO_Site;

@WebServlet("/ListeSecteurs")
public class SRV_ListeSecteurs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public static final String VUE_LISTE_SECTEURS				= "/jsp_liste_secteurs.jsp";

	public static final String ATT_LISTE_SECTEURS 				= "listeSecteurs";
	
	public static final String CONFIGURATION_DAO_FACTORY 		= "daoFactory";
	
	private DAO_Site daoSite;
	private DAO_Secteur daoSecteur;
    
    public void init() throws ServletException {

        this.daoSite = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSite();
        this.daoSecteur = ((DAO_Factory) getServletContext().getAttribute(CONFIGURATION_DAO_FACTORY)).getDaoSecteur();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomSite = request.getParameter("nom");
		
		if(nomSite == null) {
		
			List<BN_SecteurInnerJoin> listeSecteurs = daoSecteur.selectionSecteurs();
			
			request.setAttribute(ATT_LISTE_SECTEURS, listeSecteurs);
			
		} else {
			
			BN_Site site = daoSite.selectionSiteParNom(nomSite);
			
			Long idSite = site.getIdSite();
			
			List<BN_SecteurInnerJoin> listeSecteurs = daoSecteur.selectionSecteursParIdSite(idSite);
			
			request.setAttribute(ATT_LISTE_SECTEURS, listeSecteurs);
		}
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE_SECTEURS).forward(request, response);
	}
}
