package com.vidyo.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vidyo.beans.User;
import com.vidyo.daos.UserDAO;
import com.vidyo.dtos.CallInfoDTO;
import com.vidyo.webservices.admin.AddMemberRequest;
import com.vidyo.webservices.admin.AddMemberResponse;
import com.vidyo.webservices.admin.Filter;
import com.vidyo.webservices.admin.GeneralFault_Exception;
import com.vidyo.webservices.admin.GetMembersRequest;
import com.vidyo.webservices.admin.GetMembersResponse;
import com.vidyo.webservices.admin.InvalidArgumentFault_Exception;
import com.vidyo.webservices.admin.Member;
import com.vidyo.webservices.admin.MemberAlreadyExistsFault_Exception;
import com.vidyo.webservices.admin.MemberNotFoundFault_Exception;
import com.vidyo.webservices.admin.NotLicensedFault_Exception;
import com.vidyo.webservices.admin.ObjectFactory;
import com.vidyo.webservices.admin.UpdateMemberRequest;
import com.vidyo.webservices.admin.UpdateMemberResponse;
import com.vidyo.webservices.admin.VidyoPortalAdminServicePortType;
import com.vidyo.webservices.common.VidyoAdminService;



public class UserBusiness implements ApplicationContextAware {
    private static Logger LOGGER = Logger.getLogger(UserBusiness.class);
	private static ApplicationContext appCtx = null;
    public UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public void updateUser(User user){
		User usr = new User();
		
		BeanUtils.copyProperties(user, usr);
		usr.setPassword(EncriptDecript.encrypt(usr.getPassword()));
		
		userDao.updateUser(usr);
	}
	
	public void deleteUser(User user){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)appCtx.getBean("vidyoAdminService");
		vidyoAdminService.removeUser(user);
		
		userDao.deleteUser(user);
	}

	public void insertUser(User user){
		User usr = new User();
		
		BeanUtils.copyProperties(user, usr);
		usr = encryptUser(usr);
		
		userDao.insertUser(usr);
	}
	
	public User getUserByEmailAddress(String emailAddress){
		
		try{
			List<User> userList = userDao.getUserByCriteria("emailaddress", emailAddress);
			
			if(userList.size()>0){
				User user = userList.get(0);
				user = decryptUser(user);
				return user;
			}
		}catch(Exception ex){
			LOGGER.error("getUserByEmailAddress failed:", ex);
		}
		
		return null;
	}

	public User getUserByUserName(String userName){
		
		try{
			List<User> userList = userDao.getUserByCriteria("username", userName);
			
			if(userList.size()>0){
				User user = userList.get(0);
				user = decryptUser(user);
				return user;
			}
		}catch(Exception ex){
			LOGGER.error("getUserByUserName failed:", ex);
		}
		
		return null;
	}

	
	public User getUserBySessionId(String sessionId){
		try{
			List<User> userList = userDao.getUserByCriteria("sessionid", sessionId);
			
			if(userList.size()>0){
				User user = userList.get(0);
				user = decryptUser(user);
				return user;
			}
		}catch(Exception ex){
			LOGGER.error("getUserBySessionId failed:", ex);
		}
		
		return null;
	}
	
	public List getAdminUserList(){
		List<User> usrList = null;
		try{
			usrList = userDao.getUserByCriteria("userrole", "ADMIN");
			for(User user : usrList){
				user = decryptUser(user);
			}
		}
		catch(Exception ex){
			LOGGER.error("getAdminUserList failed:", ex);
		}
		return usrList;
	}
	

	public List getUserListByCriteria(String query){
		List<User> usrList = null;
		try{
			usrList = userDao.getUserListByName(query);
			for(User user : usrList){
				user = decryptUser(user);
			}
		}
		catch(Exception ex){
			LOGGER.error("getUserListByCriteria failed:", ex);
		}
		return usrList;
	}
	
	
  public static ApplicationContext getApplicationContext() {
        return appCtx;
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.appCtx = applicationContext;
    }
	
	
    public User encryptUser(User user){
    	user.setPassword(EncriptDecript.encrypt(user.getPassword()));
    	user.setApiPassword(EncriptDecript.encrypt(user.getApiPassword()));
    	return user;
    }
    
    public User decryptUser(User user){
    	user.setPassword(EncriptDecript.decrypt(user.getPassword()));
    	user.setApiPassword(EncriptDecript.decrypt(user.getApiPassword()));
    	return user;
    }

	
}
