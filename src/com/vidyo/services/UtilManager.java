package com.vidyo.services;

public class UtilManager {

	public static String getDialInInstruction(String roomNumber, String roomPin){
		String dialInfo;
		dialInfo ="To join the the conference, Device instruction:<BR>";
		dialInfo +="<B>H.323 Endpoints</B>";
		dialInfo +="<ul><LI>Polycom or LifeSize, dial 160.79.219.122##{MeetRoomNumber}*{MeetRoomPin}</LI>";
		dialInfo +="<LI>Sony, dial 160.79.219.122#{MeetRoomNumber}*{MeetRoomPin}</LI>";
		dialInfo +="<LI>Codian, dial 160.79.219.122!{MeetRoomNumber}*{MeetRoomPin}</LI>";
		dialInfo +="<LI>Tandberg MXP, dial {MeetRoomNumber}*{MeetRoomPin}@160.79.219.122</LI>";
		dialInfo +="<LI>C series, dial {MeetRoomNumber}*{MeetRoomPin}@160.79.219.122 OR h323:{MeetRoomNumber}*{MeetRoomPin}@160.79.219.122</LI>";
		dialInfo +="<LI>2500 vB3.9 and MCU 8+8, dial 160.79.219.122,{MeetRoomNumber}{MeetRoomPin}</LI>";
		dialInfo +="</ul><BR>";
		dialInfo +="<B>SIP Endpoint</b><BR>";
		dialInfo +="<ul><LI>To join the conference, dial {MeetRoomNumber}*{MeetRoomPin}@160.79.219.122</LI></ul>";
		
		if(roomNumber!=null && !roomNumber.equals("")){
			dialInfo = dialInfo.replace("{MeetRoomNumber}", roomNumber);
		}
		else{
			dialInfo = dialInfo.replace("{MeetRoomNumber}", "");
		}
		
		if(roomPin!=null && !roomPin.equals("")){

			dialInfo = dialInfo.replace("{MeetRoomPin}", roomPin);
		}
		else{
			dialInfo = dialInfo.replace("{MeetRoomPin}", "");
		}
		
		return dialInfo;
	}
	
	public static String getVoiceCallInfo(){
		
		String dialInfo;
		dialInfo ="To join from a telephone, please dial the following number:<BR>";
		dialInfo +="<BR>US Toll-Free dial 800-507-9079 X 102156539";
		dialInfo +="<BR>For US Local Number dial +1 (201) 289-5557 X 102156539";
		dialInfo +="<BR>For International callers:";
		dialInfo +="<BR>London, United Kingdom: +44 2033182610 X 102156539";
		dialInfo +="<BR>France: +33 975181444 X 102156539";
		dialInfo +="<BR>Milan, Italy: +39 0247921659 X 102156539";
		dialInfo +="<BR>Tokyo, Japan: +81 345209469 X 102156539";
		dialInfo +="<BR>Netherlands: +31 858880319 X 102156539";
		
		return dialInfo;
		
	}
}
