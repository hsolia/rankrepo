package com.vidyo.services;

import java.io.Serializable;

import com.vidyo.beans.Meeting;

public interface IMeetingManager extends Serializable {

	public boolean createMeeting(Meeting meeting);
}
