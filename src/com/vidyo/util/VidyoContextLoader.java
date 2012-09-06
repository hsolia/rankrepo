package com.vidyo.util;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.vidyo.common.VidyoConstants;

/**
 * Application Lifecycle Listener implementation class VidyoContextLoader
 *
 */
@WebListener
public class VidyoContextLoader implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public VidyoContextLoader() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    	
    	System.out.println(servletContextEvent.getServletContext().getServerInfo());
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
