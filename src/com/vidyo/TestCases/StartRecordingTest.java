package com.vidyo.TestCases;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.vidyo.webservices.user.*;
import com.vidyo.webservices.user.GetParticipantsRequest;
import com.vidyo.webservices.user.GetParticipantsResponse;
import com.vidyo.webservices.user.MyAccountRequest;
import com.vidyo.webservices.user.MyAccountResponse;
import com.vidyo.webservices.user.ObjectFactory;
import com.vidyo.webservices.user.StartRecordingRequest;
import com.vidyo.webservices.user.StartRecordingResponse;
import com.vidyo.webservices.user.VidyoPortalUserService;
import com.vidyo.webservices.user.VidyoPortalUserServicePortType;

public class StartRecordingTest {

	public static void main(String args[]){
		
		try{
			ObjectFactory factory = new ObjectFactory();
			
			
	    	VidyoPortalUserService adminService = new VidyoPortalUserService();
	    	VidyoPortalUserServicePortType port = adminService.getVidyoPortalUserServicePort();
			BindingProvider bindingProvider = (BindingProvider)port;
			Map requestContext = bindingProvider.getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, "mayur");
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, "1");
			
			GetParticipantsRequest request1 = factory.createGetParticipantsRequest();
			GetParticipantsResponse response1 = factory.createGetParticipantsResponse();
			
			
			MyAccountRequest accountReq = factory.createMyAccountRequest();
			
		
			MyAccountResponse accountRes = port.myAccount(accountReq);
			Entity entity= accountRes.getEntity();
			
			
			
			PauseRecordingRequest request = factory.createPauseRecordingRequest();
			PauseRecordingResponse response = factory.createPauseRecordingResponse();
			
			GetRecordingProfilesRequest request2 = factory.createGetRecordingProfilesRequest();
			GetRecordingProfilesResponse response2 = factory.createGetRecordingProfilesResponse();
			
			response2 = port.getRecordingProfiles(request2);
			

			
			request.setConferenceID(entity.getEntityID());
			request.setRecorderID(1);
			
			
			response = port.pauseRecording(request);
			System.out.println("Recording Started "+response.getOK());
		
		}
		catch(Throwable ex){
			ex.printStackTrace();
		}
		
	}
	
}
