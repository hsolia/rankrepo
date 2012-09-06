package com.vidyo.webservices.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.lowagie.text.RomanList;
import com.vidyo.beans.User;
import com.vidyo.daos.CommonDAO;
import com.vidyo.dtos.ParticipantEntityDTO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.exception.WSException;
import com.vidyo.util.BaseBean;
import com.vidyo.webservices.admin.*;



public class VidyoAdminService extends BaseBean  {

	VidyoPortalAdminServicePortType port;
	private static Logger LOGGER = Logger.getLogger(VidyoAdminService.class);
	ObjectFactory factory;
    public VidyoAdminService(VidyoPortalAdminServicePortType port, String username, String password) {
    	factory = new ObjectFactory();
    	
    	LOGGER.info("AdminVidyoService started");
    	this.port = port;
		BindingProvider bindingProvider = (BindingProvider)this.port;
		Map requestContext = bindingProvider.getRequestContext();
		requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
    }
	
    public boolean inviteToConference(Integer confID, String IPAddress){
		
    	// Note : Now serviceExt removed, no more required to add prefix when dialling out.
    	boolean success=false;
    	
		try{
		
		InviteToConferenceRequest request = factory.createInviteToConferenceRequest();
		InviteToConferenceResponse response = factory.createInviteToConferenceResponse();
		
		request.setConferenceID(confID);
		request.setInvite(IPAddress);
		response = this.port.inviteToConference(request);
		
		if("OK".equals(response.getOK())){
			success=true;
		}
		LOGGER.info(response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error while inviteToConference", ex);
		}
		
		return success;
		
    }
    
    public List<String> getRoomListByUser(String userName){
    	
    	List<String> usernameList = null;
		
    	try{
			GetRoomsRequest roomsReqest =factory.createGetRoomsRequest();
			GetRoomsResponse roomsResponse = factory.createGetRoomsResponse();
			
			
			Filter filter = factory.createFilter();
			
			filter.setQuery(factory.createFilterQuery(userName));
			roomsReqest.setFilter(filter);
			roomsResponse = port.getRooms(roomsReqest);
			List<Room> rooms = roomsResponse.getRoom();
			
			if(rooms.size()>0)usernameList =  new ArrayList<String>(rooms.size());
			
			for(Room myRoom : rooms){
				if(myRoom.getOwnerName().equalsIgnoreCase(userName)){
					usernameList.add(myRoom.getName());
				}
			}
		}
		catch(Exception ex){
			LOGGER.error("Error in getRoomListByUser ", ex);
		}

    	return usernameList;
    }

    
    public RoomEntityDTO getRoomByExtension(String roomNumber){
    	
    	RoomEntityDTO roomEntityDto =  null;
		
    	try{
			GetRoomsRequest roomsReqest =factory.createGetRoomsRequest();
			GetRoomsResponse roomsResponse = factory.createGetRoomsResponse();
			
			
			Filter filter = factory.createFilter();
			
			filter.setQuery(factory.createFilterQuery(roomNumber));
			roomsReqest.setFilter(filter);
			
			roomsResponse = port.getRooms(roomsReqest);
			List<Room> rooms = roomsResponse.getRoom();
			for(Room myRoom : rooms){
				if(myRoom.getExtension().equalsIgnoreCase(roomNumber)){
					roomEntityDto = new RoomEntityDTO();
					roomEntityDto.setExtension(myRoom.getExtension());
					roomEntityDto.setRoomID(myRoom.getRoomID());
			    	roomEntityDto.setOwnerName(myRoom.getOwnerName());
			    	roomEntityDto.setName(myRoom.getName());
			    	roomEntityDto.setDescription(myRoom.getDescription());
					roomEntityDto.setRoomURL(myRoom.getRoomMode().getRoomURL().getValue());
			    	roomEntityDto.setRoomPIN(myRoom.getRoomMode().getRoomPIN().getValue());
			    	roomEntityDto.setHasPin(myRoom.getRoomMode().isHasPin());
			    	roomEntityDto.setLocked(myRoom.getRoomMode().isIsLocked());
					break;
				}
			}
		}
		catch(Exception ex){
			LOGGER.error("Error in getRoomByExtension ", ex);
		}

    	return roomEntityDto;
    }


    
    public boolean inviteToConference(RoomEntityDTO roomEntity, String IPAddress){
		
    	boolean success=false;
		try{
			ObjectFactory factory = new ObjectFactory();
			
			InviteToConferenceRequest request = factory.createInviteToConferenceRequest();
			InviteToConferenceResponse response = factory.createInviteToConferenceResponse();
			
			 
			request.setConferenceID(roomEntity.getRoomID());
			request.setInvite(IPAddress);
			response = this.port.inviteToConference(request);
			
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info(response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in inviteToConference", ex);
		}
		
		return success;
		
    }
    

    
	public void UpdateVidyoRoom(RoomEntityDTO roomEntity){
		try{

			GetRoomRequest roomReqest =factory.createGetRoomRequest();
			GetRoomResponse roomResponse = factory.createGetRoomResponse();
			
			roomReqest.setRoomID(roomEntity.getRoomID());
			roomResponse = port.getRoom(roomReqest);
			
			Room myRoom  =roomResponse.getRoom();
			
			myRoom.setExtension(roomEntity.getExtension());
			
			if(roomEntity.getRoomPIN().equalsIgnoreCase("") || roomEntity.getRoomPIN()==null){

				RemoveRoomPINRequest roomPINReqest =factory.createRemoveRoomPINRequest();
				RemoveRoomPINResponse roomPINResponse = factory.createRemoveRoomPINResponse();

				myRoom.getRoomMode().setHasPin(false);
				roomPINReqest.setRoomID(myRoom.getRoomID());
				
				roomPINResponse = port.removeRoomPIN(roomPINReqest);
				LOGGER.info("Remove Room PIN "+roomPINResponse.getOK());

				
			}else{
				CreateRoomPINRequest roomPINReqest =factory.createCreateRoomPINRequest();
				CreateRoomPINResponse roomPINResponse = factory.createCreateRoomPINResponse();
				myRoom.getRoomMode().setHasPin(true);
				roomPINReqest.setRoomID(myRoom.getRoomID());
				roomPINReqest.setPIN(roomEntity.getRoomPIN());
				
				roomPINResponse = port.createRoomPIN(roomPINReqest);
				LOGGER.info("Set Room PIN "+roomPINResponse.getOK());
				
			}
			

			UpdateRoomRequest updateRoomReqest =factory.createUpdateRoomRequest();
			UpdateRoomResponse updateRoomResponse = factory.createUpdateRoomResponse();

			updateRoomReqest.setRoomID(myRoom.getRoomID());
			updateRoomReqest.setRoom(myRoom);

			updateRoomResponse = port.updateRoom(updateRoomReqest);
			LOGGER.info("Update Room "+updateRoomResponse.getOK());

		}
		catch(Exception ex){
			LOGGER.error("Error in UpdateVidyoRoom ", ex);
		}
	}

	public boolean vidyoUserExist(String username){
		try{
    	
			GetMembersRequest getMembersRequest= factory.createGetMembersRequest();
	
			Filter filter = factory.createFilter();
	
			filter.setQuery(factory.createFilterQuery(username));
			getMembersRequest.setFilter(filter);
			GetMembersResponse getMembersResponse = factory.createGetMembersResponse();
			getMembersResponse = port.getMembers(getMembersRequest);
			List<Member> members= getMembersResponse.getMember();
			for(Member member : members){
				if(username.equalsIgnoreCase(member.getName())){
					return true;
				}
			}
		}
		catch(Throwable t){
			LOGGER.error("Error in vidyoUserExistCheck ", t);
		}
    	return false;
    }
    
    
    public boolean removeUser(User user){
    	try{
    		ObjectFactory factory = new ObjectFactory();
    		
    		DeleteMemberRequest deleteMemberRequest = factory.createDeleteMemberRequest();
    		DeleteMemberResponse deleteMemberResponse = factory.createDeleteMemberResponse();
    		
    		
    		deleteMemberRequest.setMemberID(user.getEntityId());
    		
    		deleteMemberResponse = port.deleteMember(deleteMemberRequest);
    		LOGGER.info("Member removed from VidyoAPI: "+deleteMemberResponse.getOK());
    	}
		catch(Throwable ex){
			LOGGER.error("Error in removeUser", ex);
		}
    	return true;
    }

    
	public User addUser(User user){
		String roomNumber="";
		try{

			
			GetMembersRequest getMembersRequest= factory.createGetMembersRequest();

			Member member = factory.createMember();
			member.setName(user.getUsername());
			member.setDisplayName(user.getFirstname()+" "+user.getLastname());
			

			
			CommonDAO commonDao = (CommonDAO)appCtx.getBean("commonDao");
			do{
				roomNumber = commonDao.getNextRoomNumber().toString();
				member.setExtension(roomNumber);
			}
			while(getRoomByExtension(roomNumber)!=null);
			

			member.setPassword(factory.createMemberPassword(user.getApiPassword()));			
			member.setLanguage("en");
			member.setRoleName("Normal");
			member.setGroupName("Default");
			member.setEmailAddress(user.getEmailaddress());
			member.setLocationTag("Default");
			
			AddMemberRequest addMemberRequest= factory.createAddMemberRequest();
			addMemberRequest.setMember(member);
			AddMemberResponse addMemberResponse = factory.createAddMemberResponse();

			addMemberResponse = port.addMember(addMemberRequest);

			LOGGER.debug("Add Member in VidyoAPI "+addMemberResponse.getOK());

			user.setEntityId(getUserEntityID(user));
			
			return user; 
		}
		catch(Throwable ex){
			LOGGER.error("error while adding Member in VidyoAPI", ex);
		}

		return user; 
	}

	public void addRoom(RoomEntityDTO roomEntity){

		try{

			
			GetMembersRequest getMembersRequest= factory.createGetMembersRequest();

			Room room = factory.createRoom();

			room.setExtension(roomEntity.getExtension());
			room.setName(roomEntity.getName());
			room.setGroupName(roomEntity.getGroupName());
			room.setName(roomEntity.getName());
			room.setDescription(roomEntity.getDescription());
			room.setOwnerName(roomEntity.getOwnerName());
			room.setRoomType(roomEntity.getRoomType());
			
			RoomMode roomMode = new RoomMode();
			roomMode.setHasPin(true);
			JAXBElement<String> pin = new JAXBElement<String>(new QName("http://portal.vidyo.com/admin/v1_1", "roomPIN"), String.class, null);
			pin.setValue(roomEntity.getRoomPIN());
			roomMode.setRoomPIN(pin);
			roomMode.setIsLocked(false);
			room.setRoomMode(roomMode);


			

			AddRoomRequest addRoomRequest= factory.createAddRoomRequest();
			addRoomRequest.setRoom(room);
			AddRoomResponse addRoomResponse = factory.createAddRoomResponse();
			addRoomResponse = port.addRoom(addRoomRequest);
			
			LOGGER.debug("addRoom: "+ addRoomResponse.getOK());
		}
		catch(Throwable ex){
			LOGGER.error("Error in addRoom", ex);
		}

	}

	
    public List getParticipants(RoomEntityDTO myRoom){
    	
    	List<ParticipantEntityDTO> entityDtoList = null;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			GetParticipantsRequest request = factory.createGetParticipantsRequest();
			GetParticipantsResponse response = factory.createGetParticipantsResponse();
			
			Filter filter = factory.createFilter();
		
			request.setConferenceID(myRoom.getRoomID());
			response = this.port.getParticipants(request);
			

			if(response.getTotal() > 0){
				entityDtoList = new ArrayList<ParticipantEntityDTO>();
				List<Entity> parti = response.getEntity();
				
				for(Entity participat : parti){
					JAXBElement<Boolean>  audio = participat.getAudio();
					JAXBElement<Boolean>  video = participat.getVideo();
					JAXBElement<Integer>  participantID = participat.getParticipantID();
					String  displayName = participat.getDisplayName();
					String  iPAddress = participat.getExtension();
					String status = participat.getMemberStatus();
					
					ParticipantEntityDTO partiDto =  new ParticipantEntityDTO();
					partiDto.setDisplayName(displayName);
					partiDto.setAudio(audio.getValue());
					partiDto.setVideo(video.getValue());
					partiDto.setParticipantID(participantID.getValue());
					//partiDto.setStatus(status);
					partiDto.setiPAddress(iPAddress);
					entityDtoList.add(partiDto);
				}

			}
			
			
			}
			catch(Throwable ex){
				ex.printStackTrace();
			}
			
    	return entityDtoList;
    }

	
	public Integer getUserEntityID(User user){
		
		Integer entityID = 0;
		
		try{
			ObjectFactory factory = new ObjectFactory();
			
			GetMembersRequest getMembersRequest= factory.createGetMembersRequest();
	
			Filter filter = factory.createFilter();
			String username = "";
			filter.setQuery(factory.createFilterQuery(username));
			getMembersRequest.setFilter(filter);
			GetMembersResponse getMembersResponse = factory.createGetMembersResponse();
			getMembersResponse = port.getMembers(getMembersRequest);
			List<Member> members= getMembersResponse.getMember();		
	
			
			LOGGER.info(getMembersResponse.getTotal());
			
			for(Member member :  members){
				if(member.getName().equals(user.getUsername())){
					entityID = member.getMemberID();
					break;
				}
			}
		}
		catch(Throwable ex){
			LOGGER.error("Error in getVidyoUserEntityID",ex);
		}


		return entityID;
	}
	
	public void updateUser(User user){

		try{
			ObjectFactory factory = new ObjectFactory();

			GetMemberRequest getRequest = factory.createGetMemberRequest();
			GetMemberResponse getResponse = factory.createGetMemberResponse();
			
			getRequest.setMemberID(user.getEntityId());
			getResponse = port.getMember(getRequest);
			
			Member member= getResponse.getMember();
	
			member.setMemberID(user.getEntityId());
			member.setDisplayName(user.getFirstname()+" "+user.getLastname());
			member.setPassword(factory.createMemberPassword(user.getApiPassword()));			
			member.setEmailAddress(user.getEmailaddress());
			UpdateMemberRequest updateMemberRequest= factory.createUpdateMemberRequest();
			
			updateMemberRequest.setMember(member);
			updateMemberRequest.setMemberID(user.getEntityId());
			UpdateMemberResponse updateMemberResponse = factory.createUpdateMemberResponse();
			
			updateMemberResponse = port.updateMember(updateMemberRequest);
			
			LOGGER.info(updateMemberResponse.getOK());
 
		}
		catch(Throwable ex){
			LOGGER.error("Error in UpdateVidyoUser",ex);
		}
	}
	

	/*************************/
	
    

    public boolean disconnectParticipant(Integer participantID, RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			
			LeaveConferenceRequest request = factory.createLeaveConferenceRequest();
			LeaveConferenceResponse response = factory.createLeaveConferenceResponse();
			request.setConferenceID(roomEntity.getRoomID());
			request.setParticipantID(participantID);
			
			response = this.port.leaveConference(request);
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Disconnected "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in disconnect participant",ex);
		}
		
		return success;
		

    }
    
    public boolean startAudio(Integer participantID,RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			UnmuteAudioRequest request = factory.createUnmuteAudioRequest();
			UnmuteAudioResponse response = factory.createUnmuteAudioResponse();
			
			request.setConferenceID(roomEntity.getRoomID());
			request.setParticipantID(participantID);
			
			response = this.port.unmuteAudio(request);
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Audio Started "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in startAudio",ex);
		}
		
		return success;
		
    }
   
    public boolean stopAudio(Integer participantID,RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			MuteAudioRequest request = factory.createMuteAudioRequest();
			MuteAudioResponse response = factory.createMuteAudioResponse();
			
			request.setConferenceID(roomEntity.getRoomID());
			request.setParticipantID(participantID);
			
			response = this.port.muteAudio(request);
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Audio Stoped "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in stopAudio",ex);
		}
		
		return success;
		
    }    

    
    public boolean startVideo(Integer participantID,RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			StartVideoRequest request = factory.createStartVideoRequest();
			StartVideoResponse response = factory.createStartVideoResponse();
			
			request.setConferenceID(roomEntity.getRoomID());
			request.setParticipantID(participantID);
			response = this.port.startVideo(request);
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Video Started "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in startVideo",ex);
		}
		
		return success;
		
    }
   
    public boolean stopVideo(Integer participantID,RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			StopVideoRequest request = factory.createStopVideoRequest();
			StopVideoResponse response = factory.createStopVideoResponse();
			
			request.setConferenceID(roomEntity.getRoomID());
			request.setParticipantID(participantID);
			
			response = this.port.stopVideo(request);
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Video Stoped "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in stopVideo",ex);
		}
		
		return success;
		
    }    

    public boolean startRecording(RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();
			
			StartRecordingRequest request = factory.createStartRecordingRequest();
			StartRecordingResponse response = factory.createStartRecordingResponse();
			
			request.setConferenceID(roomEntity.getRoomID());
			request.setRecorderPrefix("02");
			
			response = this.port.startRecording(request);
			
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Recording Started "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in startRecording",ex);
		}
		
		return success;
		
    }

    
    public boolean stopRecording(RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
		
		
		ObjectFactory factory = new ObjectFactory();
		
		GetParticipantsRequest parRequest = factory.createGetParticipantsRequest();
		GetParticipantsResponse parresponse = factory.createGetParticipantsResponse();
		
		Filter filter = factory.createFilter();
	
		parRequest.setConferenceID(roomEntity.getRoomID());
		parresponse = this.port.getParticipants(parRequest);
		JAXBElement<Integer> recorderID = parresponse.getRecorderID();
		
		StopRecordingRequest request = factory.createStopRecordingRequest();
		StopRecordingResponse response = factory.createStopRecordingResponse();
		
		request.setConferenceID(roomEntity.getRoomID());
		request.setRecorderID(recorderID.getValue());
		response = this.port.stopRecording(request);
		if("OK".equals(response.getOK())){
			success=true;
		}
		LOGGER.info("Recording Stoped "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in stopRecording",ex);
		}
		
		return success;
    }
    
    public boolean pauseRecording(RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
			ObjectFactory factory = new ObjectFactory();

			GetParticipantsRequest parRequest = factory.createGetParticipantsRequest();
			GetParticipantsResponse parresponse = factory.createGetParticipantsResponse();
			
			Filter filter = factory.createFilter();
		
			parRequest.setConferenceID(roomEntity.getRoomID());
			parresponse = this.port.getParticipants(parRequest);
			JAXBElement<Integer> recorderID = parresponse.getRecorderID();			
			
			PauseRecordingRequest request = factory.createPauseRecordingRequest();
			PauseRecordingResponse response = factory.createPauseRecordingResponse();
			
			request.setConferenceID(roomEntity.getRoomID());
			request.setRecorderID(recorderID.getValue());
			response = this.port.pauseRecording(request);
			if("OK".equals(response.getOK())){
				success=true;
			}
			LOGGER.info("Recording Paused "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in pauseRecording",ex);
		}
		
		return success;
		
    }

    
    public boolean resumeRecording(RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
		
		
		ObjectFactory factory = new ObjectFactory();
		
		GetParticipantsRequest parRequest = factory.createGetParticipantsRequest();
		GetParticipantsResponse parresponse = factory.createGetParticipantsResponse();
		
		Filter filter = factory.createFilter();
	
		parRequest.setConferenceID(roomEntity.getRoomID());
		parresponse = this.port.getParticipants(parRequest);
		JAXBElement<Integer> recorderID = parresponse.getRecorderID();			
		
		ResumeRecordingRequest request = factory.createResumeRecordingRequest();
		ResumeRecordingResponse response = factory.createResumeRecordingResponse();
		
		request.setConferenceID(roomEntity.getRoomID());
		request.setRecorderID(recorderID.getValue());
		response = this.port.resumeRecording(request);

		if("OK".equals(response.getOK())){
			success=true;
		}
		
		LOGGER.info("Recording Resumed "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in resumeRecording",ex);
		}
		
		return success;
    }
    
}
