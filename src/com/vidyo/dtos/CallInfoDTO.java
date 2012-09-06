package com.vidyo.dtos;

public class CallInfoDTO {

	
	private String callId;
	private String conferenceName;
	private String endpointType;
	private String callerId;
	private String joinTime;
	private String leaveTime;
	private String callState;
	
	
	
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public String getConferenceName() {
		return conferenceName.substring(0, conferenceName.indexOf("@"));
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	public String getEndpointType() {
		return endpointType;
	}
	public void setEndpointType(String endpointType) {
		this.endpointType = endpointType;
	}
	public String getCallerId() {
		return callerId;
	}
	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getCallState() {
		return callState;
	}
	public void setCallState(String callState) {
		this.callState = callState;
	}
	
	
	
}
