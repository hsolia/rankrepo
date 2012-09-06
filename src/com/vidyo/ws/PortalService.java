package com.vidyo.ws;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.vidyo.beans.User;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.services.MeetingManager;
import com.vidyo.services.TemplateManager;
import com.vidyo.services.UserManager;
import com.vidyo.services.VidyoAPIManager;
import com.vidyo.webservices.common.VidyoAdminService;

@WebService(serviceName="PortalService",name="PortalServicePortType",portName="PortalServicePort")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class PortalService extends CommonUtil
{
	
	private static final Logger LOGGER = Logger.getLogger(PortalServiceImpl.class);
	private String serverResponse="";
	private User user = null;
	
	@WebMethod(operationName="LogInRequest")
	@WebResult(name="LoginResponse")
	public LoginResponse loginRequest()
	{
		LoginResponse response = new LoginResponse();
		User user = authenticate();

		if(user!=null){
			response.setStatus("OK");
			response.setResult("Sucessfully logged in");
		}else{
			response.setStatus("Error");
			response.setResult(serverResponse);
		}
			
		return response;
	}

	@WebMethod(operationName="ScheduleMeeting")
	@WebResult(name="ScheduleMeetingResponse")
	public ScheduleMeetingResponse scheduleMeeting(@WebParam(name="ScheduleMeetingRequest")ScheduleMeetingRequest request)
	{
		ScheduleMeetingResponse response = new ScheduleMeetingResponse();
		User user = authenticate();
		if(user!=null){


			MeetingManager meetingManager = (MeetingManager)getBean("meetingManager");
			
			RoomEntityDTO newPubRoom = new RoomEntityDTO();

			if(!request.meetingPin.equals("")){
				newPubRoom.setHasPin(true);
				newPubRoom.setRoomPIN(request.meetingPin);
			}
			
			newPubRoom = meetingManager.createMeeting(newPubRoom, user);
			String invitaionDesc = meetingManager.renderInvitationTemplate(newPubRoom, user);

			byte[] buf = Base64.encodeBase64(invitaionDesc.getBytes());
			String encodedTemplate = new String(buf);
			response.setMeetingNumber(newPubRoom.getExtension());
			response.setMeetingPIN(newPubRoom.getRoomPIN());
	    	response.setMeetingTemplate(encodedTemplate);
	    	response.setStatus("OK");
	    	
		}else{
			response.setStatus("Error");
			response.setMeetingTemplate(serverResponse);
		}
	    
	    return response;
	}


	@WebMethod(operationName="GetRoomUrl")
	@WebResult(name="GetRoomUrlResponse")
	public GetRoomUrlResponse getRoomUrl(@WebParam(name="GetRoomUrlRequest")GetRoomUrlRequest request)
	{
		GetRoomUrlResponse response =  new GetRoomUrlResponse();
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		RoomEntityDTO myRoom = vidyoAdminService.getRoomByExtension(request.getRoomNumber());
		if(myRoom!=null){
			response.setRoomUrl(myRoom.getRoomURL());
			response.setStatus("OK");
			response.setResult("Success");
		}else{
			response.setStatus("Error");
			response.setResult("Invalid Room Number!");
		}
	    
	    return response;
	}


	
	@WebMethod(operationName="LogOutRequest")
	@WebResult(name="LogoutResponse")
	public LogoutResponse logoutRequest()
	{
		LogoutResponse response = new LogoutResponse();
		HttpSession session = getCurrentSession();
		
		User user = authenticate();
		if(user!=null){
			response.setStatus("OK");
			response.setResult("Successfully logged out");
		}else{
			response.setStatus("Error");
			response.setResult(serverResponse);
		}
		
		return response;
	}
	
	@WebMethod(exclude=true)
	public User authenticate(){

		  LOGGER.debug("Start Login");
		  MessageContext mc = wsContext.getMessageContext();
		  Map http_headers = (Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);

		  ArrayList list = (ArrayList) http_headers.get("Authorization");
		  if (list == null || list.size() == 0) {
				serverResponse = "Authetication required: Please enter Username and Password!";
				return null;
		  }
		  
		  String userpass = (String) list.get(0);
		  userpass = userpass.substring(5);
		  byte[] buf = Base64.decodeBase64(userpass.getBytes());
		  String credentials = new String(buf);
		  
		  String username = null;
		  String password = null;
		  int p = credentials.indexOf(":");

		  if (p > -1) {
			  username = credentials.substring(0, p);
			  password = credentials.substring(p+1);
		  }else {
				serverResponse = "There was an error while decoding the Authentication!";
				return null;
		  }

		UserManager userManager = (UserManager)getBean("userManager");
		User user = userManager.authenricateUser(username, password);
		if(user == null){
			serverResponse = "Authetication failed: Username or Password does not match!";
			return null;
		}else if(user.getStatus().equals("0")){
			serverResponse = "Your account has not been activated. Please follow the activation email instructions";
			return null;
			
		}else if(user.getEnabled().equals("0")){
			serverResponse = "Your account is locked, please contact support for further information.";
			return null;
		}
		this.user = user;
		return user;
	}
	
	
}