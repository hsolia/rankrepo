package com.vidyo.actions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.vidyo.beans.Device;
import com.vidyo.beans.User;
import com.vidyo.daos.RoomDAO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.services.RoomManager;
import com.vidyo.webservices.common.VidyoAdminService;
import com.vidyo.webservices.common.VidyoUserService;

@ManagedBean(name="dialOutAction")
@ViewScoped
public class DialOutAction  extends BaseAction implements Serializable {

	private String actionType = "init";
	private String roomNumber;
	private String roomPin;
	private String dialOutipAddress;
	private RoomEntityDTO roomEntity;
	
	public void initDialOut(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
			User user = (User)session.getAttribute("user");

		}
	}
	
	public void initGuestDialOut(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			
	    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    	String roomKey = params.get("key");
	    	
	    	if(roomKey!=null){
	    		byte[] buf = Base64.decodeBase64(roomKey);
	    		this.roomNumber= new String(buf);
	    		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
	    		roomEntity = vidyoAdminService.getRoomByExtension(roomNumber);
	    		if(roomEntity==null){
	    			addMessage(getMsgString("RoomNotExist"), FacesMessage.SEVERITY_ERROR, "dialoutform");
	    		}
	    	}
		}
	}
	
	public void getRoomByKey(String roomKey){

	}
	
	public void initDialin(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
			User user = (User)session.getAttribute("user");
		}
	}


	


	public void doDialIn(){
		if(validatePin()){
			
			VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
			boolean success = vidyoAdminService.inviteToConference(roomEntity.getRoomID()  ,this.dialOutipAddress);
			if(success==false){
				addMessage("System Error: Unable to call destination", FacesMessage.SEVERITY_ERROR , "dialoutform");
			}else{
				addMessage("Dialed out your device successfully.", FacesMessage.SEVERITY_INFO , "dialoutform");
			}
		}
	}
	
	public boolean validatePin(){
		if(this.roomPin.equalsIgnoreCase(roomEntity.getRoomPIN())){
			return true;
		}else{
			addMessage("Invalid Room PIN! Please enter valid Room PIN", FacesMessage.SEVERITY_ERROR , "dialoutform");
			return false;
		}
	}

	
	
	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}



	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getRoomPin() {
		return roomPin;
	}


	public void setRoomPin(String roomPin) {
		this.roomPin = roomPin;
	}


	public String getDialOutipAddress() {
		return dialOutipAddress;
	}

	public void setDialOutipAddress(String dialOutipAddress) {
		this.dialOutipAddress = dialOutipAddress;
	}
}

