package com.vidyo.dtos;

public class RecordingDTO {

	private int id;
	private String dateCreated;
	private String title;
	private String desc;	
	private String duration;
	private String fileLink;
	private String playbackLink;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFileLink() {
		return fileLink;
	}
	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}
	public String getPlaybackLink() {
		return playbackLink;
	}
	public void setPlaybackLink(String playbackLink) {
		this.playbackLink = playbackLink;
	}
	
	
	
}
