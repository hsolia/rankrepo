package com.vidyo.actions;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.vidyo.beans.User;
import com.vidyo.dtos.ParticipantEntityDTO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.services.EmailManager;
import com.vidyo.services.MeetingManager;
import com.vidyo.services.RoomManager;
import com.vidyo.webservices.common.VidyoAdminService;
import com.vidyo.webservices.common.VidyoUserService;

@ManagedBean
@SessionScoped
public class ManageMeetingAction extends BaseAction  {

	private static Logger LOGGER = Logger.getLogger(ManageMeetingAction.class);
	
	private List<ParticipantEntityDTO> participantEntityList;
	private RoomEntityDTO roomEntity;
	private boolean muteAllAudio = false;
	private boolean muteAllVideo = false;
	private boolean disconnectAll = false;
	private boolean recording = false;
	private boolean recordingPaused = false;
	private int totAtt=0;
	
	private String roomNumber;
	private String roomPin;
	private String dialOutipAddress;
	private User meetingOwner;
	
	private String userEmailTo;
	private String userEmailSubject;
	private String userEmailFrom;
	private String userEmailBody;

	
	public void initManageMeeting(){
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
			meetingOwner  = (User)session.getAttribute("user");
			
	    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    	String roomKey = params.get("key");
	    	if(roomKey!=null){
	    		getRoomByKey(roomKey);
	    	}
			
		}
	}
	
	public void getRoomByKey(String roomKey){
		byte[] buf = Base64.decodeBase64(roomKey);
		this.roomNumber= new String(buf);
		this.getRoomForManage();
	}
	
	public void initRoomUpdate(){
		this.roomPin = "";
	}
	
	public void getRoomForManage(){
		
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		RoomEntityDTO roomEntity = vidyoAdminService.getRoomByExtension(roomNumber);

		if(roomEntity==null){
			addMessage(getMsgString("RoomNotExist"), FacesMessage.SEVERITY_ERROR, "getRoomForm:roomNumber");
		}else if(!roomEntity.getOwnerName().equalsIgnoreCase(meetingOwner.getUsername())){
			addMessage(getMsgString("RoomNotOwner"), FacesMessage.SEVERITY_ERROR, "getRoomForm:roomNumber");
		}
		else{
			this.roomEntity = roomEntity;
			refreshList();
			this.roomNumber="";
		}

		
	}
	
	public void refreshList(){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		participantEntityList = vidyoAdminService.getParticipants(roomEntity);
		if(participantEntityList!=null)
		{	totAtt = participantEntityList.size();	}
		else{totAtt = 0;}
	
		System.out.println((new Date().getSeconds())+ "Total Users : "+totAtt);
		
	}

	public void testRefresh(){
		System.out.println((new Date().getSeconds())+ "Total Users : "+totAtt);
	}
	
	public void doDialOut(){
		
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		boolean success = vidyoAdminService.inviteToConference(this.roomEntity,this.dialOutipAddress);
		if(success==false){
			addMessage("System Error: Unable to call destination", FacesMessage.SEVERITY_ERROR , "dialoutForm:ipaddress");
		}else{
			this.dialOutipAddress = "";
			addMessage("Invite sent successfully.", FacesMessage.SEVERITY_INFO , "dialoutForm:ipaddress");
		}

	}
	

	public void updateRoom(){

		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
		User user = (User)session.getAttribute("user");
		
		if(this.roomPin.trim()!=""){
			roomEntity.setRoomPIN(this.roomPin);
			RoomManager roomManager =(RoomManager)getBean("roomManager");
			roomManager.updateRoom(roomEntity);
		}
	}

	
	public void loadCallInstruction(){

		MeetingManager meetingManager = (MeetingManager)getBean("meetingManager");
		this.userEmailBody = meetingManager.renderInvitationTemplate(roomEntity,this.meetingOwner);
		
		this.userEmailTo = "";
		this.userEmailSubject="Vidyo Call Instruction";
		this.userEmailFrom = this.meetingOwner.getEmailaddress();
		
	}	
	
	public void sendCallInstruction(){
		EmailManager emailManager =(EmailManager)getBean("emailManager");
		emailManager.sendUserEmail(this.userEmailBody, this.userEmailSubject, this.userEmailTo,this.userEmailFrom);
	}
	
	public void muteUnmuteAllAudio(){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		if(isMuteAllAudio()){
			for( ParticipantEntityDTO participantEntity : participantEntityList ){
				vidyoAdminService.startAudio(participantEntity.getParticipantID(),this.roomEntity);
				participantEntity.setAudio(true);
			}
			this.setMuteAllAudio(false);
		}
		else{
			for( ParticipantEntityDTO participantEntity : participantEntityList ){
				vidyoAdminService.stopAudio(participantEntity.getParticipantID(),this.roomEntity);
				participantEntity.setAudio(false);
			}
			this.setMuteAllAudio(true);
		}
	}
	


	public void muteUnmuteAllVideo(){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		if(isMuteAllVideo()){
			for( ParticipantEntityDTO participantEntity : participantEntityList ){
				vidyoAdminService.startVideo(participantEntity.getParticipantID(),this.roomEntity);
				participantEntity.setVideo(true);
			}
			this.setMuteAllVideo(false);
		}
		else{
			for( ParticipantEntityDTO participantEntity : participantEntityList ){
				vidyoAdminService.stopVideo(participantEntity.getParticipantID(),this.roomEntity);
				participantEntity.setVideo(false);
			}
			this.setMuteAllVideo(true);
		}

	}
	
	public void disconnectAll(){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
			for( ParticipantEntityDTO participantEntity : participantEntityList ){
				vidyoAdminService.disconnectParticipant(participantEntity.getParticipantID(),this.roomEntity);
				participantEntity.setStatus(false);
			}
			this.setDisconnectAll(true);
	}

	public void muteUnmuteAudio(Integer i){
		ParticipantEntityDTO participantEntityDTO = this.participantEntityList.get(i);
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		
		if(participantEntityDTO.isAudio()){
			System.out.println(vidyoAdminService.stopAudio(participantEntityDTO.getParticipantID(),this.roomEntity));
			participantEntityDTO.setAudio(false);
		}
		else{
			System.out.println(vidyoAdminService.startAudio(participantEntityDTO.getParticipantID(),this.roomEntity));
			participantEntityDTO.setAudio(true);
		}
	}
	
	public void muteUnmuteVideo(Integer i){
		ParticipantEntityDTO participantEntityDTO = this.participantEntityList.get(i);
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		
		if(participantEntityDTO.isVideo()){
			System.out.println(vidyoAdminService.stopVideo(participantEntityDTO.getParticipantID(),this.roomEntity));
			participantEntityDTO.setVideo(false);
		}
		else{
			System.out.println(vidyoAdminService.startVideo(participantEntityDTO.getParticipantID(),this.roomEntity));
			participantEntityDTO.setVideo(true);
		}
	}
	

	
	public void disconnectParticipant(Integer i){
		ParticipantEntityDTO participantEntityDTO = this.participantEntityList.get(i);
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		System.out.println(vidyoAdminService.disconnectParticipant(participantEntityDTO.getParticipantID(),this.roomEntity));
		participantEntityDTO.setStatus(false);

	}

	
	public void startStopRecording(){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		if(isRecording()){
			vidyoAdminService.stopRecording(this.roomEntity);
			this.setRecording(false);
			this.setRecordingPaused(false);
		}else{
			vidyoAdminService.startRecording(this.roomEntity);
			this.setRecording(true);
		}
	}
	
	public void puuseContinueRecording(){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		if(isRecordingPaused()){
			vidyoAdminService.resumeRecording(this.roomEntity);
			this.setRecordingPaused(false);
		}else{
			vidyoAdminService.pauseRecording(this.roomEntity);
			this.setRecordingPaused(true);
		}
	}

	public void lockUnlockRoom(){
		VidyoUserService vidyoUserService = (VidyoUserService)getBean("vidyoUserService");
		if(roomEntity.isLocked()){
			vidyoUserService.unLockRoom(roomEntity);
			roomEntity.setLocked(false);
		}else{
			vidyoUserService.lockRoom(roomEntity);
			roomEntity.setLocked(true);
		}
	}
	


	public List<ParticipantEntityDTO> getParticipantEntityList() {
		return participantEntityList;
	}

	public void setParticipantEntityList(
			List<ParticipantEntityDTO> participantEntityList) {
		this.participantEntityList = participantEntityList;
	}



	public boolean isRecording() {
		return recording;
	}

	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	public boolean isRecordingPaused() {
		return recordingPaused;
	}

	public void setRecordingPaused(boolean recordingPaused) {
		this.recordingPaused = recordingPaused;
	}


	public boolean isDisconnectAll() {
		return disconnectAll;
	}

	public void setDisconnectAll(boolean disconnectAll) {
		this.disconnectAll = disconnectAll;
	}



	public boolean isMuteAllVideo() {
		return muteAllVideo;
	}

	public void setMuteAllVideo(boolean muteAllVideo) {
		this.muteAllVideo = muteAllVideo;
	}



	public boolean isMuteAllAudio() {
		return muteAllAudio;
	}

	public void setMuteAllAudio(boolean muteAllAudio) {
		this.muteAllAudio = muteAllAudio;
	}

	public RoomEntityDTO getRoomEntity() {
		return roomEntity;
	}

	public void setRoomEntity(RoomEntityDTO roomEntity) {
		this.roomEntity = roomEntity;
	}
	public int getTotAtt() {
		return totAtt;
	}
	public void setTotAtt(int totAtt) {
		this.totAtt = totAtt;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomPin() {
		return roomPin;
	}

	public void setRoomPin(String roomPin) {
		this.roomPin = roomPin;
	}

	public String getDialOutipAddress() {
		return dialOutipAddress;
	}

	public void setDialOutipAddress(String dialOutipAddress) {
		this.dialOutipAddress = dialOutipAddress;
	}


	public String getUserEmailTo() {
		return userEmailTo;
	}


	public void setUserEmailTo(String userEmailTo) {
		this.userEmailTo = userEmailTo;
	}


	public String getUserEmailSubject() {
		return userEmailSubject;
	}


	public void setUserEmailSubject(String userEmailSubject) {
		this.userEmailSubject = userEmailSubject;
	}


	public String getUserEmailFrom() {
		return userEmailFrom;
	}


	public void setUserEmailFrom(String userEmailFrom) {
		this.userEmailFrom = userEmailFrom;
	}


	public String getUserEmailBody() {
		return userEmailBody;
	}


	public void setUserEmailBody(String userEmailBody) {
		this.userEmailBody = userEmailBody;
	}



}
