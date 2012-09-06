package com.vidyo.TestCases;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.vidyo.webservices.admin.AddMemberRequest;
import com.vidyo.webservices.admin.AddMemberResponse;
import com.vidyo.webservices.admin.Filter;
import com.vidyo.webservices.admin.GeneralFault_Exception;
import com.vidyo.webservices.admin.GetMembersRequest;
import com.vidyo.webservices.admin.GetMembersResponse;
import com.vidyo.webservices.admin.InvalidArgumentFault_Exception;
import com.vidyo.webservices.admin.Member;
import com.vidyo.webservices.admin.MemberAlreadyExistsFault_Exception;
import com.vidyo.webservices.admin.NotLicensedFault_Exception;
import com.vidyo.webservices.admin.ObjectFactory;
import com.vidyo.webservices.admin.VidyoPortalAdminService;
import com.vidyo.webservices.admin.VidyoPortalAdminServicePortType;

public class AddMemberTest {

	public static void main(String args[]){
		
/*		   try
		    {
		      Service service = new Service();
		      
		      Call call = (Call)service.createCall();
		      call.setUsername("rank1");
		      call.setPassword("rank1");
		      call.setTargetEndpointAddress(new URL("http://dev20.vidyo.com/services/v1_1/VidyoPortalAdminService"));
		      call.setOperationName(new QName("http://portal.vidyo.com/admin/v1_1", "addMember"));
		      
		      
		      call.addParameter("name", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN); 
		      call.addParameter("password", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
		      call.addParameter("displayName", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
		      call.addParameter("extension", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);		      
		      call.addParameter("Language", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);		      
		      call.addParameter("RoleName", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
		      call.addParameter("groupName", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
		      call.addParameter("emailAddress", org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
		      
		      String responseLocation = (String)call.invoke(new Object[] {"Mayur3","123456","Mayur Mayur","123456789","en","Normal","Default","hk_apm789@yahoo.com" });
		    }
		   catch(Exception ex){
			 ex.printStackTrace();  
		   }*/
		
		
		try{

			
	    	VidyoPortalAdminService adminService = new VidyoPortalAdminService();
	    	VidyoPortalAdminServicePortType port = adminService.getVidyoPortalAdminServicePort();
			BindingProvider bindingProvider = (BindingProvider)port;
			Map requestContext = bindingProvider.getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, "rank1");
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, "rank1");
			

			ObjectFactory factory = new ObjectFactory();
			
			GetMembersRequest getMembersRequest= factory.createGetMembersRequest();

			Filter filter = factory.createFilter();
			String username = "Mayur";
			filter.setQuery(factory.createFilterQuery(username));
			getMembersRequest.setFilter(filter);
			GetMembersResponse getMembersResponse = factory.createGetMembersResponse();
			getMembersResponse = port.getMembers(getMembersRequest);
			List<Member> members= getMembersResponse.getMember();		

			
			System.out.println(getMembersResponse.getTotal());

			Member member = factory.createMember();// members.get(0);			
			
			//member.setMemberID(1111);
			member.setName("Mayur111");
			member.setDisplayName("Nilam Solia");
			
			StringBuilder extension = new StringBuilder();
			Date date = new Date();
			extension.append(date.getMonth());
			extension.append(date.getDay());
			extension.append(date.getHours());
			extension.append(date.getMinutes());
			extension.append(date.getSeconds());
			member.setExtension(extension.toString());
			
			
			member.setPassword(factory.createMemberPassword("1"));			
			member.setLanguage("en");
			member.setRoleName("Admin");
			member.setGroupName("Default");
			member.setEmailAddress("hk_apm789@yahoo.com");
			member.setAllowCallDirect(true);
			member.setAllowPersonalMeeting(true);
			
			AddMemberRequest addMemberRequest= factory.createAddMemberRequest();
			addMemberRequest.setMember(member);
			AddMemberResponse addMemberResponse = factory.createAddMemberResponse();
			addMemberResponse = port.addMember(addMemberRequest);
			
			System.out.println(addMemberResponse.getOK());

			
		}
		catch(MemberAlreadyExistsFault_Exception ex){
			ex.printStackTrace();
		}
		
		catch(NotLicensedFault_Exception ex){
			ex.printStackTrace();
		}
		catch(InvalidArgumentFault_Exception ex){
			ex.printStackTrace();
		}
		catch(GeneralFault_Exception ex){
			ex.printStackTrace();
		}
	}
	
}
