package com.vidyo.actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vidyo.beans.Meeting;
import com.vidyo.beans.Participant;
import com.vidyo.beans.User;
import com.vidyo.services.EmailManager;
import com.vidyo.services.TemplateManager;

@ManagedBean
@ViewScoped
public class MeetingAction extends BaseAction  {

	private static Logger LOOGER = Logger.getLogger(MeetingAction.class);
	
	private String actionType="init";
	private List<Participant> invitationList = new ArrayList<Participant>(0);
	private String participantName;
	private String participantEmail;
	private Meeting meeting;
	
	public void initCreateMeeting(){
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
			User owner = (User)session.getAttribute("user");
			meeting = new Meeting();
			meeting.setOwner(owner);
		}
	}



	public void sendInvitation(){
		
		LOOGER.info("sendInvitation start");
		this.addParticipant();
		EmailManager emailManager = (EmailManager)getBean("emailManager");
		meeting.setParticipantList(new HashSet(this.invitationList));
		emailManager.sendInvitation(meeting);
		LOOGER.info("sendInvitation end");
		
		
	}

	public void exportInvite(){
		try{

			LOOGER.info("exportInvitaion start");
			TemplateManager templateManager = (TemplateManager)getBean("templateManager");
			String invitaionDesc = templateManager.renderHTMLTemplate(TemplateManager.INVITATAION_EMAIL, this.meeting.getOwner());
			
			Random ran = new Random();
			int p1 = ran.nextInt(100);
			
			
			String invitaion ="BEGIN:VCALENDAR\n";
			invitaion +="VERSION:2.0\n";
			invitaion +="PRODID:-//vidyo.com//EN\n";
			invitaion +="BEGIN:VEVENT\n";
			invitaion +="UID:14b150e26c3a88470c16a84690c7ce342860b970"+p1+"\n";
			//invitaion +="LOCATION:"+templateManager.renderiOsLinkUrl(this.meeting.getOwner())+"\n";
			invitaion +="X-ALT-DESC;FMTTYPE=text/html:"+invitaionDesc+"\n";
			invitaion +="END:VEVENT\n";
			invitaion +="END:VCALENDAR\n";

			
			FacesContext context = FacesContext.getCurrentInstance();
			ResponseWriter writer = context.getResponseWriter();
			String fileRealPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/invite.ics");
			String exportedFile = fileRealPath; 
			
			BufferedWriter bw =null;
			bw = new BufferedWriter(new FileWriter(fileRealPath));
			bw.write(invitaion);
			if(bw != null){
				try{bw.close();}
				catch(Exception ex){}
			}

			
			FacesContext.getCurrentInstance().getExternalContext().redirect("../invite.ics");
			FacesContext.getCurrentInstance().renderResponse();
			
			LOOGER.info("exportInvitaion end");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	
/*	public void createMeeting(){
		try{
		Meeting meeting =  new Meeting();
		meeting.setMeetingTitle(this.meetingTitle);
		meeting.setMeetingDesc(this.meetingDesc);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date date ;//= sdf.parse(this.meetingSchedule);
		meeting.setScheduleTime(new Timestamp(meetingSchedule.getTime()));
		meeting.setRepeat(this.meetingRepeat);
		meeting.setRepeatPeriod(this.repeatPeriod);
		meeting.setRepeatUpto(new Timestamp(repeatUpto.getTime()));
		
		for(Participant parti: invitationList){
			parti.setMeeting(meeting);
		}
		
		meeting.setParticipantList(new HashSet(this.invitationList));
		meeting.setOwner(this.owner);
		
		owner.getMeetingList().add(meeting);

		MeetingManager meetingManager =(MeetingManager)getBean("meetingManager");  
		meetingManager.createMeeting(meeting);
		
		this.actionType="done";
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}*/


	
	public void createQuickMeeting(ActionEvent actionEvent){
/*		this.addParticipant(actionEvent);
		try{
		Meeting meeting =  new Meeting();
		meeting.setMeetingTitle(this.meetingTitle);
		meeting.setMeetingDesc(this.meetingDesc);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		//Date date = sdf.parse(this.meetingSchedule);
		meeting.setScheduleTime(new Timestamp(new Date().getTime()));
		meeting.setRepeat(this.meetingRepeat);
		meeting.setRepeatPeriod(this.repeatPeriod);
		meeting.setRepeatUpto(new Timestamp(new Date().getTime()));
		
		for(Participant parti: invitationList){
			parti.setMeeting(meeting);
		}
		
		meeting.setParticipantList(new HashSet(this.invitationList));
		meeting.setOwner(this.owner);
		
		owner.getMeetingList().add(meeting);

		MeetingManager meetingManager =(MeetingManager)getBean("meetingManager");  
		meetingManager.createMeeting(meeting);
		
		this.actionType="done";
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		try{
			FacesContext.getCurrentInstance().getExternalContext().redirect("createmeeting.jsf");
		}
		catch(Exception ex){
			
		}*/
	}

	public void addParticipant(){
		if(this.participantName.trim()!="" && this.participantEmail.trim()!="" ){
			Participant parti = new Participant();
			parti.setParticipantName(this.participantName);
			parti.setParticipantEmail(this.participantEmail);
			this.participantName = "";
			this.participantEmail = "";
			invitationList.add(parti);
		}
	}
	




	public String getActionType() {
		return actionType;
	}



	public void setActionType(String actionType) {
		this.actionType = actionType;
	}



	public List<Participant> getInvitationList() {
		return invitationList;
	}



	public void setInvitationList(List<Participant> invitationList) {
		this.invitationList = invitationList;
	}



	public String getParticipantName() {
		return participantName;
	}



	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}



	public String getParticipantEmail() {
		return participantEmail;
	}



	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}



	public Meeting getMeeting() {
		return meeting;
	}



	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}


	
}
