package com.vidyo.TestCases;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.vidyo.webservices.user.JoinConferenceRequest;
import com.vidyo.webservices.user.JoinConferenceResponse;
import com.vidyo.webservices.user.ObjectFactory;
import com.vidyo.webservices.user.VidyoPortalUserService;
import com.vidyo.webservices.user.VidyoPortalUserServicePortType;

public class DialOutTest {

	
	
	public static void main(String args[]){
		
		try{
    	VidyoPortalUserService adminService = new VidyoPortalUserService();
    	VidyoPortalUserServicePortType port = adminService.getVidyoPortalUserServicePort();
		BindingProvider bindingProvider = (BindingProvider)port;
		Map requestContext = bindingProvider.getRequestContext();
		requestContext.put(BindingProvider.USERNAME_PROPERTY, "rank1");
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, "rank1");
		
		
		ObjectFactory factory = new ObjectFactory();
		
		
		JoinConferenceRequest request = factory.createJoinConferenceRequest();
		JoinConferenceResponse  response = factory.createJoinConferenceResponse();
		
		
		
/*		MyAccountRequest accountReq = factory.createMyAccountRequest();
		MyAccountResponse accountRes = port.myAccount(accountReq);
		Entity entity= accountRes.getEntity();*/
		
		
		//request.setConferenceID("445");
		
		//request.setInvite("02140.242.250.200");
		
		//esponse = port.joinIPCConference(request);
		
		System.out.println(response.getOK());
		
		}
		catch(Throwable ex){
			ex.printStackTrace();
		}
		
	}
}

