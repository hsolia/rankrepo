package com.vidyo.ws;

public class ScheduleMeetingResponse
{
  String status;
  String meetingNumber;
  String meetingPIN;
  String meetingTemplate;

  public String getStatus()
  {
    return this.status;
  }
  public void setStatus(String status) {
    this.status = status;
  }


  

	public String getMeetingNumber() {
		return meetingNumber;
	}
	public void setMeetingNumber(String meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	
public String getMeetingTemplate() {
    return this.meetingTemplate;
  }
  public void setMeetingTemplate(String meetingTemplate) {
    this.meetingTemplate = meetingTemplate;
  }
public String getMeetingPIN() {
	return meetingPIN;
}
public void setMeetingPIN(String meetingPIN) {
	this.meetingPIN = meetingPIN;
}
}