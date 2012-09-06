package com.vidyo.ws;

public class ScheduleMeetingRequest
{
  String meetingName;
  String meetingPin;

  public String getMeetingName() {
    return this.meetingName;
  }
  public void setMeetingName(String meetingName) {
    this.meetingName = meetingName;
  }
  
	public String getMeetingPin() {
		return meetingPin;
	}
	public void setMeetingPin(String meetingPin) {
		this.meetingPin = meetingPin;
	}



}