package com.vidyo.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.WebApplicationContext;

import com.vidyo.beans.Meeting;
import com.vidyo.beans.Participant;
import com.vidyo.beans.User;
import com.vidyo.common.VidyoConfig;
import com.vidyo.common.VidyoConstants;
import com.vidyo.webservices.common.VidyoAdminService;

public class EmailManager  implements ApplicationContextAware  {

	public static Logger LOGGER = Logger.getLogger(EmailManager.class);
	
	private WebApplicationContext ctx;
	private TemplateManager templateManager;
	


	
	 private TaskExecutor taskExecutor;

	  public EmailManager(TaskExecutor taskExecutor) {
		  this.taskExecutor = taskExecutor;
	  }
	
	public void setTemplateManager(TemplateManager templateManager) {
		this.templateManager = templateManager;
	}




	public void setApplicationContext(ApplicationContext ctx) throws BeansException {

		    this.ctx = (WebApplicationContext)ctx;
	}
	
	
	public void sendBlastEmail(List toEmailAddrs, String ccEmail,  String mailBody, String subject){

		SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
		
		emailTask.ccEmailAddrs.add(ccEmail);
		emailTask.bccEmailAddrs = toEmailAddrs;
		emailTask.subject = subject;
		emailTask.body=mailBody;

		taskExecutor.execute(emailTask);
	}
	
	public void sendUserEmail(String mailBody, String subject, String emailAddress){

		SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
		
		emailTask.toEmailAddrs.add(emailAddress);
		emailTask.subject = subject;
		emailTask.body=mailBody;

		taskExecutor.execute(emailTask);
	}
	
	public void sendUserEmail(String mailBody, String subject, String emailAddress, String fromAddress){

		SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
		emailTask.toEmailAddrs.add(emailAddress);
		emailTask.fromEmailAddr = fromAddress;
		emailTask.subject= subject;
		emailTask.body=mailBody;
		
		taskExecutor.execute(emailTask);
	}
	
	public void sendActivationEmail(User user){
		try{
			SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
			
			emailTask.toEmailAddrs.add(user.getEmailaddress());
			emailTask.subject = "Vidyo registration activation";
			String aBody=templateManager.renderHTMLTemplate(TemplateManager.ACTIVATION_EMAIL,user);
			
			String activationUrl = VidyoConstants.PORTAL_URL.concat(VidyoConstants.ACTIVATION_PAGE_LOCATION);
			activationUrl = activationUrl.concat("?sessionid="+user.getSessionid());

			emailTask.body = aBody.replace("[ACTIVATIONLINK]",activationUrl);
			
			taskExecutor.execute(emailTask);
		}
		catch(Exception ex){
			LOGGER.error("error in sendActivationEmail", ex);
		}
	}
	
	
	public void sendUserUpdate(User user){
		try{
			SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
			emailTask.toEmailAddrs.add(user.getEmailaddress());
			emailTask.subject= "Vidyo:: Your account details updated";
			emailTask.body=templateManager.renderHTMLTemplate(TemplateManager.ROOM_UPDATE_EMAIL  ,user);
			
			taskExecutor.execute(emailTask);
		}
		catch(Exception ex){
			LOGGER.error("error in sendActivationEmail", ex);
		}
	}
	
	public void sendAdminWelcome(User user){
		try{
			SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
			emailTask.toEmailAddrs.add(user.getEmailaddress());
			emailTask.subject= "Vidyo registration activation";
			emailTask.body=templateManager.renderHTMLTemplate(TemplateManager.ADMIN_WELCOME_EMAIL ,user);
			
			taskExecutor.execute(emailTask);
		}
		catch(Exception ex){
			LOGGER.error("error in sendActivationEmail", ex);
		}
	}

	public void sendUserWelcome(User user){
		try{
			
			SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
			
			emailTask.toEmailAddrs.add(user.getEmailaddress());
			emailTask.subject = "Vidyo registration activation";
			emailTask.body=templateManager.renderHTMLTemplate(TemplateManager.USER_WELCOME_EMAIL ,user);
			
			taskExecutor.execute(emailTask);
		}
		catch(Exception ex){
			LOGGER.error("error in sendUserWelcome", ex);
		}
	}
	
	
	
	

	public void sendUsernameEmail(User user,boolean both){
		
		try{
			
			SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
			
			emailTask.toEmailAddrs.add(user.getEmailaddress());
			emailTask.subject = "Login information :: Vidyo";
			emailTask.body=templateManager.renderHTMLTemplate(TemplateManager.LOGIN_DETAIL_EMAIL ,user);

			
			taskExecutor.execute(emailTask);
		}
		catch(Exception ex){
			LOGGER.error("error in sendUsernameEmail", ex);
		}
		
	}

	public void sendPasswordEmail(User user){
		try{
			SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_SIMPLE);
			
			emailTask.toEmailAddrs.add(user.getEmailaddress());
			emailTask.subject = "Login information :: Vidyo";
			emailTask.body=templateManager.renderHTMLTemplate(TemplateManager.LOGIN_DETAIL_EMAIL ,user);

			
			taskExecutor.execute(emailTask);
		}
		catch(Exception ex){
			LOGGER.error("error in sendUsernameEmail", ex);
		}
	}

	
	public void sendInvitation(Meeting meeting){
		try{
		User owner = meeting.getOwner();
		Set<Participant> participantList = meeting.getParticipantList();
		
		String iCalBody = meeting.buildICal();
		String emailBody = templateManager.renderHTMLTemplate(TemplateManager.INVITATAION_EMAIL , owner);
		//String 
		iCalBody = iCalBody.replace("[MEETINGTEMPLATE]", emailBody);
		
		SendEmailTask emailTask = new SendEmailTask(SendEmailTask.MIME_TYPE_EVENT);
		emailTask.fromEmailAddr= owner.getEmailaddress();
		emailTask.ccEmailAddrs.add(owner.getEmailaddress());
		emailTask.subject = "Vidyo Meeting:"+meeting.getMeetingTitle();
		emailTask.calBody = iCalBody;
		emailTask.body = emailBody;
		for(Participant parti : meeting.getParticipantList()){
			emailTask.toEmailAddrs.add(parti.getParticipantEmail());
		}

		taskExecutor.execute(emailTask);
		
		}catch(Exception ex){
			LOGGER.debug(ex);
		}
	}
	

}
