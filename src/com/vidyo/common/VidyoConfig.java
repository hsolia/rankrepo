package com.vidyo.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.vidyo.beans.ConfigBean;
import com.vidyo.daos.ConfigDAO;
import com.vidyo.util.BaseBean;

public class VidyoConfig extends BaseBean{

	private Logger LOGGER = Logger.getLogger(VidyoConfig.class);
	
	private String vidyoPortalApiUrl="dev20.vidyo.com";
	private String vidyoPortalApiUsername="rank1";
	private String vidyoPortalApiPassword="rank1";
	private String vidyoPortalCDRPassword="v1dy0p0rtal";
	private String vidyoGatewayIP="192.168.0.1";
	private String vidyoGatewayUrl="192.168.0.1";
	
	private String vidyoReplyApiUrl="dev20-replay.vidyo.com";
	private String vidyoReplyApiUsername="super";
	private String vidyoReplyApiPassword="password";
	
	private String vidyoPortalCDRdbUrl="jdbc:mysql://dev20.vidyo.com/portal2";
	
	private String supportEmailAddress="admin@vidyo.com";
	private String systemEmailAddress="admin@vidyo.com";
	
	private String iOSAppType="vidyoapp";
	private String userPortalUrl="vidyoapp";
	private String adminPortalUrl="vidyoapp";
	
	public VidyoConfig(ConfigDAO configDao)
	{
	
		try{
			List<ConfigBean> configList= configDao.getSystenSettings();
			
			for(ConfigBean config:configList ){
				if(config.getConfigName().equalsIgnoreCase("vidyoPortalApiUrl")){
					this.vidyoPortalApiUrl= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoPortalApiUsername")){
					this.vidyoPortalApiUsername= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoPortalApiPassword")){
					this.vidyoPortalApiPassword= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoPortalCDRPassword")){
					this.vidyoPortalCDRPassword= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoGatewayIP")){
					this.vidyoGatewayIP= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoGatewayUrl")){
					this.vidyoGatewayUrl= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoReplayApiUrl")){
					this.vidyoReplyApiUrl= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoReplayApiUsername")){
					this.vidyoReplyApiUsername= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("vidyoReplayApiPassword")){
					this.vidyoReplyApiPassword= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("supportEmailAddress")){
					this.supportEmailAddress= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("systemEmailAddress")){
					this.systemEmailAddress= config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("iOSAppType")){
					this.iOSAppType = config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("userPortalUrl")){
					this.userPortalUrl = config.getConfigValue();
				}else if(config.getConfigName().equalsIgnoreCase("adminPortalUrl")){
					this.adminPortalUrl = config.getConfigValue();
				}
				
			}
		}
		catch(Exception ex){
			
			LOGGER.error("error in vidyoConfig AfterInitialization", ex);
		}
	}

	
	public String getVidyoPortalCDRdbUrl() {
		this.vidyoPortalCDRdbUrl = "jdbc:mysql://"+this.vidyoPortalApiUrl+"/portal2";
		return this.vidyoPortalCDRdbUrl;

	}
	
	public String getVidyoPortalApiUserWsdl(){
		String portalWsdl = "http://"+this.vidyoPortalApiUrl+"/services/v1_1/VidyoPortalUserService?wsdl";
		return portalWsdl;
	}

	public String getVidyoPortalApiAdminWsdl(){
		String portalWsdl = "http://"+this.vidyoPortalApiUrl+"/services/v1_1/VidyoPortalAdminService?wsdl";
		return portalWsdl;
	}

	
	public String getVidyoReplyApiWsdl(){
		String replyWsdl = "http://"+this.vidyoReplyApiUrl+"/replay/services/VidyoReplayContentManagementService?wsdl";
		return replyWsdl;
	}

	public String getVidyoPortalApiUrl() {
		return vidyoPortalApiUrl;
	}

	public String getVidyoPortalApiUsername() {
		return vidyoPortalApiUsername;
	}


	public String getVidyoPortalApiPassword() {
		return vidyoPortalApiPassword;
	}


	public String getVidyoPortalCDRPassword() {
		return vidyoPortalCDRPassword;
	}

	public String getVidyoGatewayIP() {
		return vidyoGatewayIP;
	}


	public String getVidyoReplyApiUrl() {
		return vidyoReplyApiUrl;
	}


	public String getVidyoReplyApiUsername() {
		return vidyoReplyApiUsername;
	}


	public String getVidyoReplyApiPassword() {
		return vidyoReplyApiPassword;
	}


	public String getSupportEmailAddress() {
		return supportEmailAddress;
	}




	public String getSystemEmailAddress() {
		return systemEmailAddress;
	}


	public String getiOSAppType() {
		return iOSAppType;
	}


	public String getUserPortalUrl() {
		return userPortalUrl;
	}


	public String getAdminPortalUrl() {
		return adminPortalUrl;
	}

	public String getVidyoGatewayUrl() {
		return vidyoGatewayUrl;
	}

}
