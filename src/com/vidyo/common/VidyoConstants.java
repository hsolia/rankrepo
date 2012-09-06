package com.vidyo.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class VidyoConstants {

	private static Logger LOGGER = Logger.getLogger(VidyoConstants.class);
	public static final String USER_ROLE_ADMIN="ADMIN";
	public static final String USER_ROLE_USER="USER";
	
	public static String ACTIVATION_PAGE_LOCATION="/pages/activation.jsf";
	public static String GUESTROOM_DIALOUT_PAGE_LOCATION="/pages/guestdialout.jsf";	
	public static String MANAGE_MEETING_PAGE_LOCATION="/user/managemeeting.jsf";
	public static String EMAIL_TEMPLATE_DIR="/emailTemplate";
	public static String PORTAL_URL="";
	public static String LEGAL_TERMSCONDITION_FILE="";
	
	
	
	
	
	static{
		
		try{
			Properties prop =  new Properties();
			InputStream inputStream = VidyoConstants.class.getClassLoader().getResourceAsStream("vidyoConstants.properties");
			prop.load(inputStream);
			
			ACTIVATION_PAGE_LOCATION = prop.getProperty("ActivationPageLocation");
			GUESTROOM_DIALOUT_PAGE_LOCATION = prop.getProperty("GuestRoomDialOutPageLocation");		
			MANAGE_MEETING_PAGE_LOCATION = prop.getProperty("ManageMeetingPageLocation");
			
			EMAIL_TEMPLATE_DIR= prop.getProperty("EmailTemplateDir");
			PORTAL_URL= prop.getProperty("PortalUrl");
			LEGAL_TERMSCONDITION_FILE= prop.getProperty("LegalTermAndConditionFile");
		}
		catch(Exception ex){
			LOGGER.error("error in VidyoConstants static block loading",ex);
		}
	}
	
}
