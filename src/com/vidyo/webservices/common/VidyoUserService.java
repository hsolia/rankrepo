package com.vidyo.webservices.common;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.exception.WSException;
import com.vidyo.webservices.user.*;



public class VidyoUserService  {

	private static Logger LOGGER = Logger.getLogger(VidyoUserService.class);
	private VidyoPortalUserServicePortType userServicePort;
	private String _userEntityID;
	private String userName;
	private String webcastUrl;
	
	public VidyoUserService(VidyoPortalUserServicePortType userServicePort) {
    	
    	System.out.println("UserService started");
    	this.userServicePort = userServicePort;
    }
    
    public VidyoPortalUserServicePortType getInstance(){

    	return userServicePort;
    }
    
    public void setAuthentication(String userName, String password){
		BindingProvider bindingProvider = (BindingProvider)userServicePort;
		Map requestContext = bindingProvider.getRequestContext();
		requestContext.put(BindingProvider.USERNAME_PROPERTY, userName);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
		
		ObjectFactory factory = new ObjectFactory();
/*		try{
			MyAccountRequest accountReq = factory.createMyAccountRequest();
			MyAccountResponse accountRes = this.userServicePort.myAccount(accountReq);
			Entity entity= accountRes.getEntity();
			this.userEntityID = entity.getEntityID();

		}
		catch(Throwable ex){
			System.out.println("Unable to get Entity ID from Vidyo API");
			ex.printStackTrace();
		}*/
    }
	
    public String getWebcastUrl() {
		return webcastUrl;
	}

	public void setWebcastUrl(String webcastUrl) {
		this.webcastUrl = webcastUrl;
	}

	public String getVidyoUserName(){
    	
    	return this.userName;
    }


    public boolean lockRoom(RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
		
		
		ObjectFactory factory = new ObjectFactory();

		LockRoomRequest request = factory.createLockRoomRequest();
		LockRoomResponse response = factory.createLockRoomResponse();
		
		request.setRoomID(roomEntity.getRoomID().toString());

		response = this.userServicePort.lockRoom(request);

		if("OK".equals(response.getOK())){
			success=true;
		}
			LOGGER.info("Room Locked "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in lockRoom",ex);
		}
		
		return success;
    }
    
    public boolean unLockRoom(RoomEntityDTO roomEntity){
		
    	boolean success=false;
    	
		try{
		
		
		ObjectFactory factory = new ObjectFactory();
		
		UnlockRoomRequest request = factory.createUnlockRoomRequest();
		UnlockRoomResponse response = factory.createUnlockRoomResponse();
		
		request.setRoomID(roomEntity.getRoomID().toString());
		response = this.userServicePort.unlockRoom(request);

		if("OK".equals(response.getOK())){
			success=true;
		}
			
		LOGGER.info("Room UnLocked "+response.getOK());
		
		}
		catch(Throwable ex){
			LOGGER.error("Error in unLockRoom",ex);
			throw new WSException("WebService Exception", ex);
		}
		
		return success;
    }


	
}
