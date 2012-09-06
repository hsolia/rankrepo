package com.vidyo.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OLD_MeetingRoom implements Serializable {

	private Integer roomId;
	private String roomnumber;
	private String roompin;
	private String roomUrl;
	
		
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}
	public String getRoompin() {
		return roompin;
	}
	public void setRoompin(String roompin) {
		this.roompin = roompin;
	}
	public String getRoomUrl() {
		return roomUrl;
	}
	public void setRoomUrl(String roomUrl) {
		this.roomUrl = roomUrl;
	}
	
}
