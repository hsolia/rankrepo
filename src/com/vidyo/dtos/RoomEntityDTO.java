package com.vidyo.dtos;

public class RoomEntityDTO {

	

    protected Integer roomID;
    protected String name;
    protected String roomType;
    protected String ownerName;
    protected String extension;
    protected String groupName;
    protected String description;
    protected String roomURL;
    protected boolean isLocked;
    protected boolean hasPin;
    protected String roomPIN;
    protected boolean roomEmpty;

    public Integer getRoomID() {
		return roomID;
	}


	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomURL() {
		return roomURL;
	}
	public void setRoomURL(String roomURL) {
		this.roomURL = roomURL;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public boolean isHasPin() {
		return hasPin;
	}
	public void setHasPin(boolean hasPin) {
		this.hasPin = hasPin;
	}
	public String getRoomPIN() {
		return roomPIN;
	}
	public void setRoomPIN(String roomPIN) {
		this.roomPIN = roomPIN;
	}


	public boolean isRoomEmpty() {
		return roomEmpty;
	}


	public void setRoomEmpty(boolean roomEmpty) {
		this.roomEmpty = roomEmpty;
	}

}
