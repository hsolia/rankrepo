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
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.webservices.common.VidyoAdminService;

public class TemplateManager  implements ApplicationContextAware  {

	public static Logger LOGGER = Logger.getLogger(TemplateManager.class);
	public Map<String, String> emailTemplateMap;
	
	public static final String ACTIVATION_EMAIL="ActivationEmail";
	public static final String USER_WELCOME_EMAIL="UserWelcomeEmail";	
	public static final String ADMIN_WELCOME_EMAIL="AdminWelcomeEmail";
	public static final String LOGIN_DETAIL_EMAIL="LoginDetailEmail";
	public static final String INVITATAION_EMAIL="InvitationEmail";
	public static final String ROOM_UPDATE_EMAIL="AccountRoomUpdateEmail";	
	public static final String VOICE_INSTRUCTION_WITH_PIN="VoiceCallInstructionWithPin";
	public static final String VOICE_INSTRUCTION_WITHOUT_PIN="VoiceCallInstructionWithoutPin";
	public static final String CALL_INSTRUCTION_WITH_PIN="CallInstructionWithPin";
	public static final String CALL_INSTRUCTION_WITHOUT_PIN="CallInstructionWithoutPin";
	public static final String CALL_INSTRUCTION_EMAIL="CallInstructionEmail";
	public static final String RECORDED_MEETING_LINK_EMAIL="RecordedMeetingLinkEmail";
	
	private WebApplicationContext ctx;
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {

		    this.ctx = (WebApplicationContext)ctx;
	}

	public TemplateManager(){
		emailTemplateMap = new HashMap<String, String>();
		String baseLocation=  VidyoConstants.EMAIL_TEMPLATE_DIR;
		
		emailTemplateMap.put(ACTIVATION_EMAIL, baseLocation+"/activationEmail.html");
		emailTemplateMap.put(USER_WELCOME_EMAIL, baseLocation+"/userWelcomeEmail.html");
		emailTemplateMap.put(ADMIN_WELCOME_EMAIL, baseLocation+"/adminWelcomeEmail.html");
		emailTemplateMap.put(LOGIN_DETAIL_EMAIL, baseLocation+"/loginDetailEmail.html");
		emailTemplateMap.put(INVITATAION_EMAIL, baseLocation+"/invitationEmail.html");		
		emailTemplateMap.put(ROOM_UPDATE_EMAIL, baseLocation+"/roomUpdateEmail.html");		
		emailTemplateMap.put(CALL_INSTRUCTION_EMAIL, baseLocation+"/callInstructionEmail.html");		
		emailTemplateMap.put(VOICE_INSTRUCTION_WITH_PIN, baseLocation+"/voiceInstructionWithPin.html");
		emailTemplateMap.put(VOICE_INSTRUCTION_WITHOUT_PIN, baseLocation+"/voiceInstructionWithoutPin.html");		
		emailTemplateMap.put(CALL_INSTRUCTION_WITH_PIN, baseLocation+"/callInstructionWithPin.html");
		emailTemplateMap.put(CALL_INSTRUCTION_WITHOUT_PIN, baseLocation+"/callInstructionWithoutPin.html");		
		emailTemplateMap.put(RECORDED_MEETING_LINK_EMAIL, baseLocation+"/recordedMeetingLinkEmail.html");		

	}
	
	
	public String renderHTMLTemplate(String templateID, User user){
		
		LOGGER.info("Template rendering "+templateID);
		
		String tempBody =getTemplateAsString(templateID,user);
		tempBody =renderSimpleElement(tempBody, user);
		
		
		
		return tempBody;
	}
	
	
	public String renderSimpleElement(String tempBody,User user){
		
        VidyoConfig vidyoConfig =(VidyoConfig)ctx.getBean("vidyoConfig");
        
        tempBody = tempBody.replace("[USERNAME]", user.getUsername()!=null ? user.getUsername(): "");
        tempBody = tempBody.replace("[PASSWORD]", user.getPassword()!=null ? user.getPassword(): "");
        tempBody = tempBody.replace("[FIRSTNAME]", user.getFirstname()!=null ? user.getFirstname(): "");
        tempBody = tempBody.replace("[LASTNAME]", user.getLastname()!=null ? user.getLastname(): "");
        
        tempBody = tempBody.replace("[GATEWAYIP]", vidyoConfig.getVidyoGatewayIP()!=null ? vidyoConfig.getVidyoGatewayIP() : "");
        tempBody = tempBody.replace("[GATEWAYURL]", vidyoConfig.getVidyoGatewayUrl()!=null ? vidyoConfig.getVidyoGatewayUrl() : "");
        
        tempBody = tempBody.replace("[USERPORTALURL]", vidyoConfig.getUserPortalUrl()!=null ? vidyoConfig.getUserPortalUrl() : "");
        tempBody = tempBody.replace("[ADMINPORTALURL]", vidyoConfig.getAdminPortalUrl()!=null ? vidyoConfig.getAdminPortalUrl() : "");

       
		return tempBody;
	}
	

	

	

	
	public String renderComplexElement(String tempBody,RoomEntityDTO roomEntity, User user){
	  	
		if(tempBody.contains("[CALLINSTRUCTIONS]")){
			String callInstruction="";	
			if(roomEntity.getRoomPIN()== null){
				callInstruction = getTemplateAsString(TemplateManager.CALL_INSTRUCTION_WITHOUT_PIN,user);
			}else if(roomEntity.getRoomPIN().equals("")){
				callInstruction = getTemplateAsString(TemplateManager.CALL_INSTRUCTION_WITHOUT_PIN,user);
			}else{
				callInstruction = getTemplateAsString(TemplateManager.CALL_INSTRUCTION_WITH_PIN,user);
			}
			callInstruction = renderSimpleElement(callInstruction, user);
			tempBody = tempBody.replace("[CALLINSTRUCTIONS]", callInstruction);
		}

		if(tempBody.contains("[VOICEINSTRUCTIONS]")){
			String voiceInstruction="";
			if(roomEntity.getRoomPIN()== null){
				voiceInstruction = getTemplateAsString(TemplateManager.CALL_INSTRUCTION_WITHOUT_PIN,user);
				
			}else if(roomEntity.getRoomPIN().equals("")){
				voiceInstruction = getTemplateAsString(TemplateManager.VOICE_INSTRUCTION_WITHOUT_PIN,user);
			}else{
				voiceInstruction = getTemplateAsString(TemplateManager.VOICE_INSTRUCTION_WITH_PIN,user);
			}
			voiceInstruction = renderSimpleElement(voiceInstruction, user);
			tempBody = tempBody.replace("[VOICEINSTRUCTIONS]", voiceInstruction);
		}
		
		return tempBody;
	}
	
	public String getTemplateAsString(String templateID, User user){
		String tempBody ="";
		try{
			String tempLocation = this.emailTemplateMap.get(templateID);
			
			InputStream is = ctx.getResource(tempLocation).getInputStream();
	
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        
			String line;
			
	          while ((line = br.readLine()) != null) {
	        	  tempBody = tempBody.concat(line);
	       	  } 
	          br.close();

        	 return tempBody; 
		}
		catch(Exception ex){
			LOGGER.error("error in formatEmailTemplate(): ", ex);
		}
		return tempBody.toString();
	}
	
	

	
}
