package com.vidyo.services;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vidyo.beans.Meeting;
import com.vidyo.beans.User;
import com.vidyo.common.VidyoConfig;
import com.vidyo.common.VidyoConstants;
import com.vidyo.daos.MeetingDAO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.exception.ServiceException;
import com.vidyo.webservices.common.VidyoAdminService;

public class MeetingManager implements ApplicationContextAware {


	private static ApplicationContext appCtx = null;


	public RoomEntityDTO createMeeting(RoomEntityDTO roomEntity ,User user){
		try{
			VidyoAdminService vidyoAdminService = (VidyoAdminService)appCtx.getBean("vidyoAdminService");
			VidyoAPIManager vidyoAPIManager = (VidyoAPIManager)appCtx.getBean("vidyoAPIManager");
			
			String roomNumber = vidyoAPIManager.getNextRoomExtension();
			
			roomEntity.setExtension(roomNumber);
			roomEntity.setName(user.getUsername()+"_"+roomNumber);
			roomEntity.setGroupName("Default");
			roomEntity.setDescription(roomEntity.getDescription());
			roomEntity.setOwnerName(user.getUsername());
			roomEntity.setRoomType("Public");
			
			if(!roomEntity.isHasPin()){
				roomEntity.setHasPin(true);
				Random randomGenerator = new Random();
				Integer randomPass = randomGenerator.nextInt(8999)+1000;
				roomEntity.setRoomPIN(randomPass.toString());	
			}
			
			vidyoAdminService.addRoom(roomEntity);
			roomEntity = vidyoAdminService.getRoomByExtension(roomNumber);
			return roomEntity;
			
		}catch(Exception e){
			throw new ServiceException("ServiceException: "+e.getMessage(), e);
		}
		
	}
	
	public String renderInvitationTemplate(RoomEntityDTO roomEntity, User user){
		
		try{
			TemplateManager templateManager = (TemplateManager)appCtx.getBean("templateManager");
			
			String invitaionDesc = templateManager.renderHTMLTemplate(TemplateManager.INVITATAION_EMAIL, user);
			invitaionDesc = templateManager.renderComplexElement(invitaionDesc,roomEntity, user);
			invitaionDesc = invitaionDesc.replace("[MEETINGTITLE]", roomEntity.getDescription() !=null ? roomEntity.getDescription(): "");
			invitaionDesc = invitaionDesc.replace("[ROOMNUMBER]", roomEntity.getExtension() !=null ? roomEntity.getExtension(): "");
			invitaionDesc = invitaionDesc.replace("[ROOMPIN]", roomEntity.getRoomPIN() !=null ? roomEntity.getRoomPIN() : "");	        	  
	        invitaionDesc = invitaionDesc.replace("[DIALOUTGUESTROOMURL]", renderDialoutUrl(roomEntity));
	        invitaionDesc = invitaionDesc.replace("[MANAGEMEETINGURL]", renderManageMeetingUrl(roomEntity));
	        invitaionDesc = invitaionDesc.replace("[IOSGUESTLINK]", renderiOsLinkUrl(roomEntity));

			return invitaionDesc;

		}catch(Exception e){
			throw new ServiceException("ServiceException: "+e.getMessage(), e);
		}
	}
	
	public String renderiOsLinkUrl(RoomEntityDTO roomEntity){
		VidyoConfig vidyoConfig =(VidyoConfig)appCtx.getBean("vidyoConfig");
		
		String roomUrl = roomEntity.getRoomURL();
		roomUrl = roomUrl.replace("http", vidyoConfig.getiOSAppType());
		roomUrl = roomUrl.replace("https", vidyoConfig.getiOSAppType());

		return roomUrl;
		
	}

	public String renderManageMeetingUrl(RoomEntityDTO roomEntity){
		String roomExtension = roomEntity.getExtension();
		String dialoutRoomUrl = VidyoConstants.PORTAL_URL.concat(VidyoConstants.MANAGE_MEETING_PAGE_LOCATION);
		byte[] buf = Base64.encodeBase64(roomExtension.getBytes());
		String key = new String(buf);
		key = key.substring(0, key.length()-2);
		dialoutRoomUrl +="?key="+(key);
		return dialoutRoomUrl;
		
	}

	public String renderDialoutUrl(RoomEntityDTO roomEntity){
		String roomExtension = roomEntity.getExtension();
		String dialoutRoomUrl = VidyoConstants.PORTAL_URL.concat(VidyoConstants.GUESTROOM_DIALOUT_PAGE_LOCATION);   
		byte[] buf = Base64.encodeBase64(roomExtension.getBytes());
		String key = new String(buf);
		key = key.substring(0, key.length()-2);
		dialoutRoomUrl +="?key="+(key);

		return dialoutRoomUrl;
		
	}
	
	  public static ApplicationContext getApplicationContext() {
	        return appCtx;
	    }
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	         this.appCtx = applicationContext;
	    }
	
	
}
