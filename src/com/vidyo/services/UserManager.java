package com.vidyo.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vidyo.beans.User;
import com.vidyo.common.VidyoConfig;
import com.vidyo.common.VidyoConstants;
import com.vidyo.dtos.CallInfoDTO;
import com.vidyo.webservices.common.VidyoAdminService;



public class UserManager implements ApplicationContextAware {
    private static ApplicationContext appCtx = null;
    private UserBusiness userBusiness;

    public static final String USER_ACTIVE_STATUS="1";
    public static final String USER_UNACTIVE_STATUS="0";
    public static final String USER_ENABLED_STATUS="1";
    public static final String USER_DISABLED_STATUS="0";
	
	public UserBusiness getUserBusiness() {
		return userBusiness;
	}

	public void setUserBusiness(UserBusiness userBusiness) {
		this.userBusiness = userBusiness;
	}

	public void updateUser(User user){
		VidyoAdminService vidyoAdminService = (VidyoAdminService)appCtx.getBean("vidyoAdminService");
		vidyoAdminService.updateUser(user);
		userBusiness.updateUser(user);
		
		EmailManager emailManager = (EmailManager)appCtx.getBean("emailManager");		
		emailManager.sendUserUpdate(user);
		
	}
	
	public void deleteUser(User user){
		userBusiness.deleteUser(user);
	}

	public void createUser(User user){

		Random ran = new Random(new Date().getTime());
		Float apiPassword = ran.nextFloat();
		user.setApiPassword("!E2Ws5S"+apiPassword.toString()+"d@C^X");
		
		VidyoAdminService vidyoAdminService = (VidyoAdminService)appCtx.getBean("vidyoAdminService");
		user = vidyoAdminService.addUser(user);
		user.setSessionid(nextSessionId());
		
		userBusiness.insertUser(user);

		EmailManager emailManager = (EmailManager)appCtx.getBean("emailManager");		
		if(user.getUserrole().equalsIgnoreCase(VidyoConstants.USER_ROLE_ADMIN)){
			emailManager.sendAdminWelcome(user);
		}else{
			emailManager.sendActivationEmail(user);
		}
		
	}
	
	public void createAdmin(User user){
		userBusiness.insertUser(user);
	}
	

	
	
	
	public String nextSessionId()   {     
		 SecureRandom random = new SecureRandom(); 
		 return new BigInteger(130, random).toString(32);   
	}


	
	
	public boolean sendUsername(String emailAddress){
		
		User user = userBusiness.getUserByEmailAddress(emailAddress);
		if(user == null){
			return false;
		}
		else{
			EmailManager emailManager = (EmailManager)appCtx.getBean("emailManager");		
			emailManager.sendUsernameEmail(user,true);
			return true;	
		}

	}
	
	public boolean sendPassword(String userName){
	
		User user = userBusiness.getUserByUserName(userName);
		if(user == null){
			return false;
		}
		else{
			EmailManager emailManager = (EmailManager)appCtx.getBean("emailManager");		
			emailManager.sendPasswordEmail(user);
			return true;	
		}

	}	
	
	public User authenricateUser(String userName, String password){
		
		User usr = 	this.userBusiness.getUserByUserName(userName);
		if(usr == null){
			return null;
		}
		
		if(usr.getPassword().equals(password)){
			return usr;
		}
		else{
			return null;
		}
	}
	
	
	
	public User activateUser(String sessionId){
		
		User usr = 	this.userBusiness.getUserBySessionId(sessionId);
		if(usr == null){
			return null;
		}else		{
			usr.setStatus("1");
			userBusiness.updateUser(usr);

			EmailManager emailManager = (EmailManager)appCtx.getBean("emailManager");		
			emailManager.sendUserWelcome(usr);
			
			return usr;
		}
	}
	
	public List getUsageRecordList(List<String> userList){
		
		VidyoConfig vidyoConfig = (VidyoConfig)appCtx.getBean("vidyoConfig");

		String userNameStr="";
		Iterator<String> it =  userList.iterator();
		while(it.hasNext()){
			String userName = it.next();
			userNameStr = userNameStr.concat("'").concat(userName).concat("@").concat(vidyoConfig.getVidyoPortalApiUrl()).concat("'");

			if(it.hasNext()){
				userNameStr = userNameStr.concat(",");
			}
		}		
		
		String sqlQuery="";
		
		if(userNameStr.equals("") && userNameStr != null){
			sqlQuery="select * from ConferenceCall2";
		}else{
			sqlQuery="select * from ConferenceCall2 where ConferenceName in("+userNameStr+")";
		}
		return getUsageRecordByQuery(sqlQuery);
	}
	   
	public List getUsageRecordList(List<String> userList, Date bDate, Date eDate){

		VidyoConfig vidyoConfig = (VidyoConfig)appCtx.getBean("vidyoConfig");
		
		SimpleDateFormat sqlformatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//'YYYY-MM-DD HH:MM:SS'
		SimpleDateFormat sqlformatterEnd = new SimpleDateFormat("yyyy-MM-dd 24:00:00");//'YYYY-MM-DD HH:MM:SS'
		String joinDate = sqlformatter.format(bDate);
		String leaveDate = sqlformatterEnd.format(eDate);
		String userNameStr="";
		Iterator<String> it =  userList.iterator();
		while(it.hasNext()){
			String userName = it.next();
			userNameStr = userNameStr.concat("'").concat(userName).concat("@").concat(vidyoConfig.getVidyoPortalApiUrl()).concat("'");

			if(it.hasNext()){
				userNameStr = userNameStr.concat(",");
			}
		}
		
		
		String sqlQuery="";
		if(userNameStr.equals("") && userNameStr != null){
			sqlQuery="select * from ConferenceCall2 where JoinTime>='"+joinDate+"' and LeaveTime<='"+leaveDate +"'";
		}else{
			sqlQuery = "select * from ConferenceCall2 where ConferenceName in("+userNameStr+") and JoinTime>='"+joinDate +"' and LeaveTime<='"+leaveDate+"'";
		}
		
		return getUsageRecordByQuery(sqlQuery);
	}

	public List getUsageRecordList(List<User> userList, String bDate, String eDate)throws Exception{

		SimpleDateFormat sqlformatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//'YYYY-MM-DD HH:MM:SS'
		SimpleDateFormat sqlformatterEnd = new SimpleDateFormat("yyyy-MM-dd 24:00:00");//'YYYY-MM-DD HH:MM:SS'
		SimpleDateFormat jsfformatter = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy");
		String userNameStr="";
		
		VidyoConfig vidyoConfig = (VidyoConfig)appCtx.getBean("vidyoConfig");
		
		Iterator<User> it =  userList.iterator();
		while(it.hasNext()){
			User user = it.next();
			userNameStr = userNameStr.concat("'").concat(user.getUsername()).concat("@").concat(vidyoConfig.getVidyoPortalApiUrl()).concat("'");

			if(it.hasNext()){
				userNameStr = userNameStr.concat(",");
			}
		}
		

		
		String sqlQuery="";
		sqlQuery = "select * from ConferenceCall2 where ConferenceName in ("+userNameStr+") ";
		if(!bDate.equalsIgnoreCase("")){
			String joinDate = sqlformatter.format(jsfformatter.parse(bDate));
			sqlQuery = sqlQuery.concat(" and JoinTime>='"+joinDate +"' "); 
		}

		if(!eDate.equalsIgnoreCase("")){
			
			String leaveDate = sqlformatterEnd.format(jsfformatter.parse(eDate));
			sqlQuery = sqlQuery.concat(" and LeaveTime<='"+leaveDate+"' "); 
		}

		
		return getUsageRecordByQuery(sqlQuery);
	}
	
	private List getUsageRecordByQuery(String sqlQuery){
		
 
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");//'YYYY-MM-DD HH:MM:SS'
		SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm");//'YYYY-MM-DD HH:MM:SS'
		
		List<CallInfoDTO> callinfoList = null;
		BasicDataSource vidyoDS =null;
		
		vidyoDS =  (BasicDataSource)appCtx.getBean("myVidyoDataSource");
		Connection conn=null;

		
		try{
			conn = vidyoDS.getConnection();
			PreparedStatement stm;
		
			stm = conn.prepareStatement(sqlQuery);
			ResultSet rs = stm.executeQuery();

		if(rs != null){
			callinfoList = new ArrayList();
		}
		while(rs.next()){
			CallInfoDTO callInfoDTO = new CallInfoDTO();
			callInfoDTO.setCallId(((Integer)rs.getInt("CallID")).toString());
			callInfoDTO.setConferenceName(rs.getString("ConferenceName"));
			callInfoDTO.setEndpointType(rs.getString("EndpointType"));
			callInfoDTO.setCallerId(rs.getString("CallerID"));
			if(rs.getDate("LeaveTime")!=null){
				
				callInfoDTO.setLeaveTime(dateformat.format(rs.getDate("LeaveTime")).toString()+" "+timeformat.format(rs.getTime("LeaveTime")).toString());
			}
			if(rs.getDate("JoinTime")!=null){
				
				callInfoDTO.setJoinTime(dateformat.format(rs.getDate("JoinTime")).toString()+" "+timeformat.format(rs.getTime("JoinTime")).toString());
			}
			callInfoDTO.setCallState(rs.getString("CallState"));	
			
			callinfoList.add(callInfoDTO);
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(conn!=null){
				try{
					conn.close();
				}
				catch(Exception ex){}
			}
			
		}

		return callinfoList;
	}

	public List getUserListByCriteria(String query){
		List userList = null;
		try{
			userList = userBusiness.getUserListByCriteria(query);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return userList;
	}

	
	public List getAdminUserList(){
		List userList = null;
		try{
			userList = userBusiness.getAdminUserList();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return userList;
	}
	
	  public static ApplicationContext getApplicationContext() {
	        return appCtx;
	    }
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	         this.appCtx = applicationContext;
	    }
	
	


	
}
