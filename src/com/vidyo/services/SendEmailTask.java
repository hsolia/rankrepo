package com.vidyo.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import com.vidyo.common.VidyoConfig;
import com.vidyo.util.BaseBean;

public class SendEmailTask extends BaseBean implements Runnable {
	private static Logger LOGGER = Logger.getLogger(SendEmailTask.class);
	public static final String MIME_TYPE_SIMPLE = "simple";
	public static final String MIME_TYPE_EVENT = "event";
	
	public String fromEmailAddr;
	public List<String> toEmailAddrs = new ArrayList<String>();
	public List<String> ccEmailAddrs = new ArrayList<String>();
	public List<String> bccEmailAddrs = new ArrayList<String>();	
	public  String subject;
	public String body;
	public String calBody;	
	private String emailType=SendEmailTask.MIME_TYPE_SIMPLE;
	
	public SendEmailTask(){
		VidyoConfig vidyoConfig =(VidyoConfig) appCtx.getBean("vidyoConfig");
		fromEmailAddr = vidyoConfig.getSystemEmailAddress();
		
	}
	
	public SendEmailTask(String emailType){
		this();
		this.emailType = emailType; 
	}
	

	
	@Override
	public void run() {
		if(this.emailType.equals(SendEmailTask.MIME_TYPE_SIMPLE)){
			sendEmail();
		}else if(this.emailType.equals(SendEmailTask.MIME_TYPE_EVENT)){
			sendEvent();
		}
	}

	  public void sendEvent(){
		  LOGGER.info("Sending event");  
		  Session session = Session.getDefaultInstance( fMailServerConfig, null );
		    MimeMessage message = new MimeMessage( session );
		    try {
		    	message.addHeaderLine("method=REQUEST");
		    	message.addHeaderLine("charset=UTF-8");
		    	message.addHeaderLine("component=VEVENT");

		    	MimeBodyPart calBodyPart = new MimeBodyPart();
		    	calBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
		    	//calBodyPart.setHeader("Content-ID","calendar_message");
		    	calBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(calBody.toString(), "text/calendar;method=REQUEST;charset=\"UTF-8\"")));
		    	
		    	//part 2, html text
		    	MimeBodyPart htmlBodyPart = new MimeBodyPart();
		        htmlBodyPart.setContent( this.body, "text/html");

		    	Multipart mp = new MimeMultipart(); 
		    	mp.addBodyPart(calBodyPart); 
		    	mp.addBodyPart(htmlBodyPart);
		        
		        message.setContent(mp);
		    	this.setRecivers(message);
		     
		    	message.setFrom(new InternetAddress(fromEmailAddr));
		    	message.setSubject( subject );
		     
		    	Transport.send( message );
		    }
		    catch (Exception ex){
		      System.err.println("Cannot send email. " + ex);
		      LOGGER.error("sendEvent", ex);
		    }
		  }
		
	
	  public void sendEmail(){
	    Session session = Session.getDefaultInstance( fMailServerConfig, null );
	    MimeMessage message = new MimeMessage( session );
	    try {

			Multipart mp = new MimeMultipart(); 
			String html = this.body; 
			MimeBodyPart htmlPart = new MimeBodyPart(); 
			htmlPart.setContent(html, "text/html"); 
			mp.addBodyPart(htmlPart); 

			message.setContent(mp);
			
			this.setRecivers(message);
			
			message.setFrom(new InternetAddress(this.fromEmailAddr));
			message.setSubject(this.subject );
			 
			Transport.send( message );
	    }
	    catch (MessagingException ex){
	      LOGGER.error("sendEmail", ex);
	    }
	  }

	  public void setRecivers(MimeMessage message)throws MessagingException{
		  for(String aToEmailAddr : toEmailAddrs){
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(aToEmailAddr));
	    	}
	      
		  for(String aCcEmailAddr : ccEmailAddrs){
			    message.addRecipient(Message.RecipientType.CC, new InternetAddress(aCcEmailAddr));
		  }
		  for(String aBccEmailAddr : bccEmailAddrs){
		    message.addRecipient(Message.RecipientType.BCC , new InternetAddress(aBccEmailAddr));
    	  }

	  }
	  

	  
	  public static void refreshConfig() {
		    fMailServerConfig.clear();
		    fetchConfig();
		  }

		  // PRIVATE //

		  private static Properties fMailServerConfig = new Properties();

		  static {
		    fetchConfig();
		  }

		  /**
		  * Open a specific text file containing mail server
		  * parameters, and populate a corresponding Properties object.
		  */
		  private static void fetchConfig() {
		    InputStream input = null;
		    try {
		      //input = new FileInputStream( "C:\\VideoConf\\workspace\\Vidyo\\src\\mail.properties");
		      //fMailServerConfig.load( input );
		    }
		    catch (Exception ex ){
		      System.err.println("Cannot open and load mail server properties file.");
		    }
		    finally {
		      try {
		        if ( input != null ) input.close();
		      }
		      catch ( IOException ex ){
		        System.err.println( "Cannot close mail server properties file." );
		      }
		    }
		  }


	  
}
