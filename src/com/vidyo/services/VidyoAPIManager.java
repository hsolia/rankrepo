package com.vidyo.services;

import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vidyo.daos.CommonDAO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.webservices.admin.Room;
import com.vidyo.webservices.common.VidyoAdminService;



public class VidyoAPIManager implements ApplicationContextAware {
    private static ApplicationContext appCtx = null;

    
    
    public String getNextRoomExtension(){
    	
    	VidyoAdminService vidyoAdminService = (VidyoAdminService)appCtx.getBean("vidyoAdminService");
    	
    	Integer roomNumber;
		CommonDAO commonDao = (CommonDAO)appCtx.getBean("commonDao");
		Random randomGenerator = new Random();
		do{
			//roomNumber = commonDao.getNextRoomNumber().toString();
			do{
				roomNumber = (randomGenerator.nextInt(1999999)+1000000) ;
			}while(roomNumber < 1000000 || roomNumber >2000000);
		}
		while(vidyoAdminService.getRoomByExtension(roomNumber.toString())!=null);
    	
    	return roomNumber.toString();
    }
    
	  public static ApplicationContext getApplicationContext() {
	        return appCtx;
	    }
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	         this.appCtx = applicationContext;
	    }
	
	


	
}
