package net.ddns.buenaondalab.bch.hacker;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class BCHContextListener
 *
 */
@WebListener
public class BCHContextListener implements ServletContextListener {
	
	@EJB
	private HackerTimerTask task;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BCHContextListener.class);
	
	private static final long INTERVAL = 1000*60*60*3; // executes every 6 hour 
	

    /**
     * Default constructor. 
     */
    public BCHContextListener() {}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	LOGGER.info("BCHContextListener shutdown.");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
        
    	task.execute(10000, INTERVAL);
    	
    }
	
}
