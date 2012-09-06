package com.vidyo.services;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vidyo.actions.BaseAction;
import com.vidyo.beans.Device;
import com.vidyo.beans.User;
import com.vidyo.daos.RoomDAO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.webservices.admin.CreateRoomPINRequest;
import com.vidyo.webservices.admin.CreateRoomPINResponse;
import com.vidyo.webservices.admin.Filter;
import com.vidyo.webservices.admin.GetRoomsRequest;
import com.vidyo.webservices.admin.GetRoomsResponse;
import com.vidyo.webservices.admin.ObjectFactory;
import com.vidyo.webservices.admin.Room;
import com.vidyo.webservices.admin.UpdateRoomRequest;
import com.vidyo.webservices.admin.UpdateRoomResponse;
import com.vidyo.webservices.admin.VidyoPortalAdminServicePortType;
import com.vidyo.webservices.common.VidyoAdminService;

public class RoomManager extends BaseAction {

	private RoomDAO roomDao;
	
	
	public void updateRoom(RoomEntityDTO roomEntity){
		
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
		User user = (User)session.getAttribute("user");
		
		
		VidyoAdminService vidyoAdminService = (VidyoAdminService)getBean("vidyoAdminService");
		vidyoAdminService.UpdateVidyoRoom(roomEntity);
		roomDao.update(roomEntity);
		
		EmailManager emailManager = (EmailManager)getBean("emailManager");		
		emailManager.sendUserUpdate(user);
		
	}
	
	public void getDeviceList(){
		
	}




	
	public RoomDAO getRoomDao() {
		return roomDao;
	}


	public void setRoomDao(RoomDAO roomDao) {
		this.roomDao = roomDao;
	}


}
