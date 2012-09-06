package com.vidyo.ws;

import java.util.Enumeration;

import javax.jws.WebService;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.vidyo.beans.User;
import com.vidyo.services.UserManager;
import com.vidyo.webservices.common.VidyoUserService;

@WebService(endpointInterface="com.vidyo.ws.PortalService")
public class PortalServiceImpl extends CommonUtil
{
	
	private static final Logger LOGGER = Logger.getLogger(PortalServiceImpl.class);
	public LoginResponse loginRequest()
	{
    
	  LoginResponse response = new LoginResponse();
	  
	  LOGGER.debug("Start Login");
	  MessageContext mc = wsContext.getMessageContext();
	  String username = (String)mc.get(BindingProvider.USERNAME_PROPERTY);
      String password = (String)mc.get(BindingProvider.PASSWORD_PROPERTY);

		UserManager userManager = (UserManager)getBean("userManager");
		User user = userManager.authenricateUser(username, password);
		if(user == null){
			response.status ="error";
			response.result = getMsgString("AutheticationFailed");
			
		}else if(user.getStatus().equals("0")){
			response.status ="error";
			response.result = getMsgString("UnActivated");
		}else if(user.getEnabled().equals("0")){
			response.status ="error";
			response.result = getMsgString("UserLocked");
		}		
		else{
			setUserInsession(user);
			response.status ="OK";
			response.setResult("Successfully login");
		}

	  return response;
  }

	
	
  public ScheduleMeetingResponse scheduleMeeting(ScheduleMeetingRequest request)
  {
    ScheduleMeetingResponse response = new ScheduleMeetingResponse();
    
    if(!isLoggedIn()){
        response.setStatus("error");
        response.setMeetingTemplate("Please loging first");

    }else{
    	response.setStatus("OK");
    	response.setMeetingTemplate("HTML Templates comes here");
    }
    
    return response;
  }

  public LogoutResponse logoutRequest()
  {
	  LogoutResponse response = new LogoutResponse();
	  HttpSession session = getCurrentSession();

	    if(!isLoggedIn()){
	  	  response.setStatus("error");
		  response.setResult("Please loging first");

	    }else{
	  	  if(session!=null){
	  		session.invalidate();
	  	  }
	    	response.setStatus("OK");
	    	response.setResult("Successfully logout");
	    }
	  
    
	  return response;
  }
  

  
}