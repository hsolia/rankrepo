package com.vidyo.TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;

import com.vidyo.dtos.ParticipantEntityDTO;
import com.vidyo.webservices.user.*;

public class GetParticipatTest {

	
	
	public static void main(String args[]){
		
		List<ParticipantEntityDTO> entityDtoList = null;
		
		try{
    	VidyoPortalUserService adminService = new VidyoPortalUserService();
    	VidyoPortalUserServicePortType port = adminService.getVidyoPortalUserServicePort();
		BindingProvider bindingProvider = (BindingProvider)port;
		Map requestContext = bindingProvider.getRequestContext();
		requestContext.put(BindingProvider.USERNAME_PROPERTY, "Mayur");
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, "1");
		
		
		ObjectFactory factory = new ObjectFactory();
		
		GetParticipantsRequest request = factory.createGetParticipantsRequest();
		GetParticipantsResponse response = factory.createGetParticipantsResponse();
		
		
		MyAccountRequest accountReq = factory.createMyAccountRequest();
		
	
		MyAccountResponse accountRes = port.myAccount(accountReq);
		Entity entity= accountRes.getEntity();
		
		
		
		
		
		Filter filter = factory.createFilter();
	
		request.setConferenceID(entity.getEntityID());
		response = port.getParticipants(request);
		
		System.out.println(response.getTotal());
		System.out.println("Done");
		
				
		if(response.getTotal() > 0){
			entityDtoList = new ArrayList<ParticipantEntityDTO>();
			List<Entity> parti = response.getEntity();
			
			for(Entity participat : parti){
				JAXBElement<Boolean>  audio = participat.getAudio();
				JAXBElement<Boolean>  video = participat.getVideo();
				JAXBElement<String>  participantID = participat.getParticipantID();
				String  displayName = participat.getDisplayName();
				String status = participat.getMemberStatus();
				
				ParticipantEntityDTO partiDto =  new ParticipantEntityDTO();
				partiDto.setDisplayName(displayName);
				partiDto.setAudio(audio.getValue());
				partiDto.setVideo(video.getValue());
				//partiDto.setParticipantID(participantID.getValue());
				//partiDto.setStatus(status);
				entityDtoList.add(partiDto);
			}

		}
		
		StopVideoRequest request1 = factory.createStopVideoRequest();
		StopVideoResponse response1 = factory.createStopVideoResponse();
		
		
		//request1.setConferenceID(entity.getEntityID());
		//request1.se
		
		//port.stopVideo(parameter)
		
		}
		catch(InvalidArgumentFault_Exception ex){
			ex.printStackTrace();
		}
		
		catch(NotLicensedFault_Exception ex){
			ex.printStackTrace();
		}
		catch(SeatLicenseExpiredFault_Exception ex){
			ex.printStackTrace();
		}

		catch(GeneralFault_Exception ex){
			ex.printStackTrace();
		}
		
	}
}
