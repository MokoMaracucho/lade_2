package lade.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lade.dao.DAO_Factory;

public class CONF_InitialisationDaoFactory implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "daoFactory";
	
	private DAO_Factory daoFactory;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext servletContext = event.getServletContext();
		
		this.daoFactory = DAO_Factory.getInstance();
		
		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
