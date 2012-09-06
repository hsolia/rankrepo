package com.vidyo.TestCases;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.vidyo.webservices.admin.GetRoomsRequest;
import com.vidyo.webservices.admin.GetRoomsResponse;
import com.vidyo.webservices.admin.Filter;
import com.vidyo.webservices.admin.GeneralFault_Exception;
import com.vidyo.webservices.admin.GetMembersRequest;
import com.vidyo.webservices.admin.GetMembersResponse;
import com.vidyo.webservices.admin.GetRoomRequest;
import com.vidyo.webservices.admin.InvalidArgumentFault_Exception;
import com.vidyo.webservices.admin.Member;
import com.vidyo.webservices.admin.MemberAlreadyExistsFault_Exception;
import com.vidyo.webservices.admin.NotLicensedFault_Exception;
import com.vidyo.webservices.admin.ObjectFactory;
import com.vidyo.webservices.admin.Room;
import com.vidyo.webservices.admin.RoomMode;
import com.vidyo.webservices.admin.VidyoPortalAdminService;
import com.vidyo.webservices.admin.VidyoPortalAdminServicePortType;

public class SearchRoomTest{

	public static void main(String args[]){
		
		
		try{

			
	    	VidyoPortalAdminService adminService = new VidyoPortalAdminService();
	    	VidyoPortalAdminServicePortType port = adminService.getVidyoPortalAdminServicePort();
			BindingProvider bindingProvider = (BindingProvider)port;
			Map requestContext = bindingProvider.getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, "rank1");
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, "rank1");
			

			ObjectFactory factory = new ObjectFactory();
			Filter filter = factory.createFilter();
			
			filter.setQuery(factory.createFilterQuery(""));
			GetRoomsRequest getRoomsRequest= factory.createGetRoomsRequest();
			getRoomsRequest.setFilter(filter);

			GetRoomsResponse getRoomsResponse = factory.createGetRoomsResponse();
			getRoomsResponse = port.getRooms(getRoomsRequest);
			
			List<Room> roomList = getRoomsResponse.getRoom();
			System.out.println(roomList.size());

			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
}
