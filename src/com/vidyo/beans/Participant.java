package com.vidyo.beans;

import java.io.Serializable;



public class Participant implements Serializable  {

	private int id;
	private String participantName;
	private String participantIp;
	private String participantEmail;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public String getParticipantIp() {
		return participantIp;
	}
	public void setParticipantIp(String participantIp) {
		this.participantIp = participantIp;
	}
	public String getParticipantEmail() {
		return participantEmail;
	}
	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}


	
}
