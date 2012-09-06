package com.vidyo.dtos;

public class ParticipantEntityDTO {

	private String displayName;
	private Integer participantID;
	private boolean status = true;
	private boolean audio;
	private boolean video;
	private String iPAddress;
	

	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getParticipantID() {
		return participantID;
	}
	public void setParticipantID(Integer participantID) {
		this.participantID = participantID;
	}
	public boolean isAudio() {
		return audio;
	}
	public void setAudio(boolean audio) {
		this.audio = audio;
	}
	public boolean isVideo() {
		return video;
	}
	public void setVideo(boolean video) {
		this.video = video;
	}
	
	
}
