package com.vidyo.beans;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryImpl;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.parameter.TzId;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.UidGenerator;

import org.apache.log4j.Logger;

public class Meeting implements Serializable {

	private static Logger LOGGER = Logger.getLogger(Meeting.class);
	public static final String OCCUR_END_NEVER="never";
	public static final String OCCUR_END_COUNT="count";
	public static final String OCCUR_END_UNTIL="until";	
	
	private Integer meetingId;
	private String meetingTitle;
	private String meetingDesc;
	private Date  startDate = new Date();
	private Date startTime = new Date();
	private Integer duration;
	private String timeZone;
	private String frequency;
	private Integer feqInterval;	
	private String freqEndType;
	private String[] feqDays=new String[7];
	private Date rcurUntil = new Date();
	private Integer rcurCount = 5;
	private User owner;
	private Set<Participant> participantList = new HashSet<Participant>(0);
	
	
	public String buildICal(){
	
		String icalString="";
		
		try{
		
	
			CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_OUTLOOK_COMPATIBILITY, true);
			
			TimeZoneRegistry registry = new TimeZoneRegistryImpl("zoneinfo-outlook/");
			//TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
			TimeZone fortuneTZ  = registry.getTimeZone(this.timeZone);
			VTimeZone tz = fortuneTZ.getVTimeZone();
			TimeZone timezone =  new net.fortuna.ical4j.model.TimeZone(tz);
			
			this.startDate.setHours(this.startTime.getHours());
			this.startDate.setMinutes(this.startTime.getMinutes());
			
			// generate unique identifier..
			UidGenerator ug = new UidGenerator("uidvidyoInc");
			Uid uid = ug.generateUid();
			
			DateTime dtStart = new DateTime(this.startDate.getTime());
			Dur dur = new Dur(0, 0, this.duration, 0);
			
			VEvent meeting = new VEvent(dtStart, dur,"Vidyo Meeting:"+this.meetingTitle);
			meeting.getProperties().add(uid);
			meeting.getProperties().add(new Location("Vidyo Portal"));
			//meeting.getProperties().add(new Description("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n<HTML>\n<HEAD>\n<META NAME=\"Generator\" CONTENT=\"MS Exchange Server ve	rsion 14.02.5004.000\">\n<TITLE></TITLE>\n</HEAD>\n<BODY>\n[MEETINGTEMPLATE]\n</BODY>\n</HTML>"));
			
			//
			
			TzId tzParam = new TzId(tz.getProperties().getProperty(Property.TZID).getValue());
			meeting.getProperties().getProperty(Property.DTSTART).getParameters().add(tzParam);
			
			DtStamp dtStamp = meeting.getDateStamp();

			
			// add attendees..
			for(Participant parti : this.getParticipantList() ){
				Attendee attd = new Attendee(URI.create("mailto:"+parti.getParticipantEmail()));
				attd.getParameters().add(new Cn(parti.getParticipantName()));
				attd.getParameters().add(Role.REQ_PARTICIPANT);
				attd.getParameters().add(Rsvp.TRUE);
				meeting.getProperties().add(attd);
			}

			//add the organizer to the vevent.

			Organizer organizer = new Organizer(URI.create("mailto:"+this.owner.getEmailaddress()));
			organizer.getParameters().add(new Cn(owner.getFirstname()+" "+owner.getLastname()));
			meeting.getProperties().add(organizer);			
			
			if(this.frequency.equalsIgnoreCase("daily") )
			{	Recur recur = new Recur(Recur.DAILY ,null);
				recur.setInterval(this.feqInterval);
			//recur.setCount(10); 
				RRule rule = new RRule(recur); 
				meeting.getProperties().add(rule);
			}

			if(this.frequency.equalsIgnoreCase("weekly") )
			{	Recur recur = new Recur(Recur.WEEKLY,null); 
				recur.setInterval(this.feqInterval);
			//recur.setCount(10); 
				RRule rule = new RRule(recur); 
				meeting.getProperties().add(rule);
			}


			if(this.frequency.equalsIgnoreCase("monthly") )
			{	Recur recur = new Recur(Recur.MONTHLY,null); 
				recur.setInterval(this.feqInterval);
				//recur.setCount(10); 
				RRule rule = new RRule(recur); 
				meeting.getProperties().add(rule);
			}


			XProperty xprop1 = new XProperty("X-ALT-DESC;FMTTYPE=text/html", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\">\n<HTML>\n<HEAD>\n<META NAME=\"Generator\" CONTENT=\"MS Exchange Server ve	rsion 14.02.5004.000\">\n<TITLE></TITLE>\n</HEAD>\n<BODY>\n[MEETINGTEMPLATE]\n</BODY>\n</HTML>");
			meeting.getProperties().add(xprop1);
			
			XProperty xprop2 = new XProperty("X-MICROSOFT-CDO-BUSYSTATUS", "BUSY");
			meeting.getProperties().add(xprop2);
			
			XProperty xprop3 = new XProperty("X-MICROSOFT-CDO-IMPORTANCE", "1");
			meeting.getProperties().add(xprop3);
			
			XProperty xprop4 = new XProperty("X-MS-OLK-AUTOFILLLOCATION", "TRUE");
			meeting.getProperties().add(xprop4);

			XProperty xprop5 = new XProperty("X-MS-OLK-FORCEINSPECTOROPEN", "TRUE");
			meeting.getProperties().add(xprop5);
			
			
			VAlarm alarm = new VAlarm(new Dur(0, 0, -10, 0));
			alarm.getProperties().add(Action.DISPLAY);
			meeting.getAlarms().add(alarm);
			
			net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
			icsCalendar.getProperties().add(new ProdId("-//Microsoft Corporation//Outlook 14.0 MIMEDIR//EN"));
			icsCalendar.getProperties().add( net.fortuna.ical4j.model.property.Version.VERSION_2_0);
			icsCalendar.getProperties().add(CalScale.GREGORIAN);
			icsCalendar.getProperties().add(net.fortuna.ical4j.model.property.Method.REQUEST);
			
			
			
			// Add the event and print
			icsCalendar.getComponents().add(tz);
			icsCalendar.getComponents().add(meeting);
			icalString = icsCalendar.toString();
			
			
			String test ="BEGIN:VCALENDAR\n";
			test +="PRODID:-//Microsoft Corporation//Outlook 14.0 MIMEDIR//EN\n";
			test +="VERSION:2.0\n";
			test +="CALSCALE:GREGORIAN\n";
			test +="METHOD:REQUEST\n";
/*			test +="BEGIN:VTIMEZONE\n";
			test +="TZID:India Standard Time\n";
			test +="BEGIN:STANDARD\n";
			test +="DTSTART:16010101T000000\n";
			test +="TZOFFSETFROM:+0630\n";
			test +="TZOFFSETTO:+0530\n";
			test +="END:STANDARD\n";
			test +="END:VTIMEZONE\n";*/
			
			test +="BEGIN:VTIMEZONE\n";
			test +="TZID:Asia/Kolkata\n";
			test +="TZURL:http://tzurl.org/zoneinfo/Asia/Kolkata\n";
			test +="X-LIC-LOCATION:Asia/Kolkata\n";
/*			test +="BEGIN:STANDARD\n";
			test +="TZOFFSETFROM:+055328\n";
			test +="TZOFFSETTO:+055320\n";
			test +="TZNAME:HMT\n";
			test +="DTSTART:18800101T000000\n";
			test +="RDATE:18800101T000000\n";
			test +="END:STANDARD\n";
			test +="BEGIN:STANDARD\n";
			test +="TZOFFSETFROM:+055320\n";
			test +="TZOFFSETTO:+0630\n";
			test +="TZNAME:BURT\n";
			test +="DTSTART:19411001T000000\n";
			test +="RDATE:19411001T000000\n";
			test +="END:STANDARD\n";*/
			test +="BEGIN:STANDARD\n";
			test +="TZOFFSETFROM:+0630\n";
			test +="TZOFFSETTO:+0530\n";
			test +="TZNAME:IST\n";
			test +="DTSTART:19420515T000000\n";
			test +="RDATE:19420515T000000\n";
			test +="RDATE:19451015T000000\n";
			test +="END:STANDARD\n";
			test +="BEGIN:DAYLIGHT\n";
			test +="TZOFFSETFROM:+0530\n";
			test +="TZOFFSETTO:+0630\n";
			test +="TZNAME:IST\n";
			test +="DTSTART:19420901T000000\n";
			test +="RDATE:19420901T000000\n";
			test +="END:DAYLIGHT\n";
			test +="END:VTIMEZONE\n";
			
			test +="BEGIN:VEVENT\n";
			test +="ATTENDEE;CN=Harikrushna;RSVP=TRUE:mailto:hsolia@kristeksoftware.com\n";
			test +="CLASS:PUBLIC\n";
			test +="CREATED:20120420T190935Z\n";
			test +="DESCRIPTION:\n";
			test +="DTEND;TZID=Asia/Kolkata:20120421T013000\n";
			test +="DTSTAMP:20120420T190935Z\n";
			test +="DTSTART;TZID=Asia/Kolkata:20120421T010000\n";
			test +="LAST-MODIFIED:20120420T190935Z\n";
			test +="LOCATION:Harikrusna\n";
			test +="ORGANIZER;CN=Harikrushna G:mailto:harikrushna.solia@gmail.com\n";
			test +="PRIORITY:5\n";
			test +="SEQUENCE:0\n";
			test +="SUMMARY;LANGUAGE=en-in:\n";
			test +="TRANSP:OPAQUE\n";
			test +="UID:040000008200E00074C5B7101A82E0080000000040E3A933571FCD01000000000000000010000000FCDD6236EC46E54D8FA22DF60B415F00\n";
			test +="X-ALT-DESC;FMTTYPE=text/html:\n";
			
			test +="X-MICROSOFT-CDO-BUSYSTATUS:BUSY\n";
			test +="X-MICROSOFT-CDO-IMPORTANCE:1\n";
			test +="X-MICROSOFT-DISALLOW-COUNTER:FALSE\n";
			test +="X-MS-OLK-AUTOFILLLOCATION:FALSE\n";
			test +="X-MS-OLK-CONFTYPE:0\n";
			test +="BEGIN:VALARM\n";
			test +="TRIGGER:-PT15M\n";
			test +="ACTION:DISPLAY\n";
			test +="DESCRIPTION:Reminder\n";
			test +="END:VALARM\n";
			test +="END:VEVENT\n";
			test +="END:VCALENDAR\n";
			//icalString = test;
			
			//icalString = icalString.replace("Asia/Kolkata", "India Standard Time");
		
		}
		catch(Exception ex){
			LOGGER.error("error in buildICal", ex);
		}
		
		return icalString;
	
	}
	
	private RRule buildRcur(){
		
			Recur recur = new Recur(this.frequency,null); 
			recur.setInterval(this.feqInterval);
			
			//recur.setCount(10); 
			
			RRule rule = new RRule(recur); 
		
		return rule;
	}
	
	private String getWeekDays(){
		String weekDays="";
		
		
		return weekDays;
	}
	
	public Integer getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	public String getMeetingTitle() {
		return meetingTitle;
	}
	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}
	public String getMeetingDesc() {
		return meetingDesc;
	}
	public void setMeetingDesc(String meetingDesc) {
		this.meetingDesc = meetingDesc;
	}

	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<Participant> getParticipantList() {
		return participantList;
	}
	public void setParticipantList(Set<Participant> participantList) {
		this.participantList = participantList;
	}

	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String[] getFeqDays() {
		return feqDays;
	}
	public void setFeqDays(String[] feqDays) {
		this.feqDays = feqDays;
	}
	public Date  getStartDate() {
		return startDate;
	}
	public void setStartDate(Date  startDate) {
		this.startDate = startDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getFeqInterval() {
		return feqInterval;
	}

	public void setFeqInterval(Integer feqInterval) {
		this.feqInterval = feqInterval;
	}

	public String getFreqEndType() {
		return freqEndType;
	}

	public void setFreqEndType(String freqEndType) {
		this.freqEndType = freqEndType;
	}

	public Date getRcurUntil() {
		return rcurUntil;
	}

	public void setRcurUntil(Date rcurUntil) {
		this.rcurUntil = rcurUntil;
	}

	public Integer getRcurCount() {
		return rcurCount;
	}

	public void setRcurCount(Integer rcurCount) {
		this.rcurCount = rcurCount;
	}


}
