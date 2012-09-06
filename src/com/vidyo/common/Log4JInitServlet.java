package com.vidyo.common;

import java.io.File;

import javax.jws.soap.InitParam;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.WebServiceRefs;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4jInint
 */
@WebServlet(urlPatterns = {"/Log4JInitServlet"} ,loadOnStartup=1, initParams = {@WebInitParam(name="log4j-prop-loc", value="/WEB-INF/config/log4j.properties")})

public class Log4JInitServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log4JInitServlet() {
    }

    public void init(ServletConfig config) throws ServletException {
    	
    	// Setting rootPath
    	System.setProperty("rootPath", config.getServletContext().getRealPath("/")); 
    	System.out.println("Log4JInitServlet is initializing log4j");
    	String log4jLocation = config.getInitParameter("log4j-prop-loc");
    	ServletContext sc = config.getServletContext();
    	if (log4jLocation == null) {
    		System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
    		BasicConfigurator.configure();
    	} else {
    		String webAppPath = sc.getRealPath("/");
    		String log4jProp = webAppPath + log4jLocation;
    		File yoMamaYesThisSaysYoMama = new File(log4jProp);

    		if (yoMamaYesThisSaysYoMama.exists()) {
    				System.out.println("Initializing log4j with: " + log4jProp);
    				PropertyConfigurator.configure(log4jProp);
    		} else {
    			System.err.println("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
    			BasicConfigurator.configure();
    		}
    	}
    	super.init(config);
    }
}






