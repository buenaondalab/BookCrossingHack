package net.ddns.buenaondalab.bch.hacker;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class BCHContextListener
 *
 */
@WebListener
public class BCHContextListener implements ServletContextListener {
	
	@EJB
	private HackerTimerTask task;
	
	private static final long INTERVAL = 1000*60*60; // executes every hour 
	

    /**
     * Default constructor. 
     */
    public BCHContextListener() {}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("BCHContextListener shutdown.");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
        
    	task.execute(1000, INTERVAL);
    	System.out.println("HackerTask scheduled every " + INTERVAL +  "ms.");
    }
	
}
