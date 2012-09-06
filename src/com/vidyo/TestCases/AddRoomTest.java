package com.vidyo.TestCases;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.vidyo.webservices.admin.AddRoomRequest;
import com.vidyo.webservices.admin.AddRoomResponse;
import com.vidyo.webservices.admin.Filter;
import com.vidyo.webservices.admin.GeneralFault_Exception;
import com.vidyo.webservices.admin.GetMembersRequest;
import com.vidyo.webservices.admin.GetMembersResponse;
import com.vidyo.webservices.admin.InvalidArgumentFault_Exception;
import com.vidyo.webservices.admin.Member;
import com.vidyo.webservices.admin.MemberAlreadyExistsFault_Exception;
import com.vidyo.webservices.admin.NotLicensedFault_Exception;
import com.vidyo.webservices.admin.ObjectFactory;
import com.vidyo.webservices.admin.Room;
import com.vidyo.webservices.admin.RoomMode;
import com.vidyo.webservices.admin.VidyoPortalAdminService;
import com.vidyo.webservices.admin.VidyoPortalAdminServicePortType;

public class AddRoomTest{

	public static void main(String args[]){
		
		
		try{

			
	    	VidyoPortalAdminService adminService = new VidyoPortalAdminService();
	    	VidyoPortalAdminServicePortType port = adminService.getVidyoPortalAdminServicePort();
			BindingProvider bindingProvider = (BindingProvider)port;
			Map requestContext = bindingProvider.getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, "rank1");
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, "rank1");
			

			ObjectFactory factory = new ObjectFactory();
			
			
			Room room = factory.createRoom();
			room.setDescription("Room Description");
			room.setGroupName("Default");
			room.setName("Room Name4");
			room.setOwnerName("harikrushna");
			room.setRoomType("Public");
			room.setExtension("12374");
			room.setRoomID(12373);
			
			RoomMode roomMode = new RoomMode();
			roomMode.setHasPin(false);
			JAXBElement<String> pin = new JAXBElement<String>(new QName("http://portal.vidyo.com/user/v1_1", "roomPIN"), String.class, "12345");

			roomMode.setIsLocked(false);
			room.setRoomMode(roomMode);
			
			AddRoomRequest addRoomRequest= factory.createAddRoomRequest();
			addRoomRequest.setRoom(room);
			AddRoomResponse addRoomResponse = factory.createAddRoomResponse();
			addRoomResponse = port.addRoom(addRoomRequest);
			
			System.out.println(room.getRoomID());
			System.out.println(addRoomResponse.getOK());

			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
}
