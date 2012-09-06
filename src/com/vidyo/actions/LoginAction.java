package com.vidyo.actions;

import java.util.Enumeration;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vidyo.beans.User;
import com.vidyo.common.VidyoConstants;
import com.vidyo.services.UserManager;
import com.vidyo.webservices.common.VidyoUserService;



@ManagedBean(name="loginForm")
@ViewScoped
public class LoginAction extends BaseAction {

 
	private boolean rememberMe=false;
	private boolean forgotPassword=false;
	private boolean forgotUsername = false;
	private String  actionType = "doLogin";
	private String  username; 
	private String  password;
	private String  emailaddress;


	public LoginAction(){
		FacesContext fCtx = FacesContext.getCurrentInstance(); 
		Cookie cookie[]  = ((HttpServletRequest)fCtx.getExternalContext().getRequest()).getCookies();
		
		/*
		Cookie vidyoUser = new Cookie("vidyousername", username);
		Cookie vidyoPass = new Cookie("vidyopassword",password);
		Cookie vidyoId = new Cookie("vidyoremember","true");
		*/
		String cookieName = null;

		if(cookie != null && cookie.length > 0){
			  for(int i = 0; i<cookie.length; i++){
				  cookieName = cookie[i].getName();
				  if(cookieName.equals("vidyousername")){
					  username = cookie[i].getValue();
				  }
				  else if(cookieName.equals("vidyopassword")){
					  password = cookie[i].getValue();
				  }
				  else if(cookieName.equals("vidyoremember")){
					  if(cookie[i].getValue().equals("false")){
						  this.rememberMe = false;
					  }
					  else if(cookie[i].getValue().equals("true")){
						  this.rememberMe = true;
					  }
				  }
			  }
		}


		
	}
    public void clicked(String forgotme){
    	
    	if(forgotme.equalsIgnoreCase("username")){
    		this.forgotUsername =true;
    		this.forgotPassword=true;    		
    	}else if(forgotme.equalsIgnoreCase("password")){
    		this.forgotUsername =false;
    		this.forgotPassword=true;
    	}
    }

    public String emailAdction(){
		UserManager userManager = (UserManager)getBean("userManager");
    	if(userManager.sendUsername(this.emailaddress))
		{	this.actionType="sentLogin";
			addMessage(getMsgString("LoginDetailSent"), FacesMessage.SEVERITY_INFO,"loginform");	}
		else
		{	this.actionType="getLogin";
			addMessage(getMsgString("EmailNotFound"), FacesMessage.SEVERITY_ERROR,"emailForm:emailid");	}
		
		return "login";
    }
	
	public String loginAction(){
		
		
		if(this.forgotUsername && this.forgotPassword){
			UserManager userManager = (UserManager)getBean("userManager");
	    	if(userManager.sendUsername(this.emailaddress))
			{	this.actionType="sentLogin";
				addMessage(getMsgString("LoginDetailSent"), FacesMessage.SEVERITY_INFO,"loginform");	}
			else
			{	this.actionType="getLogin";
				addMessage(getMsgString("EmailNotFound"), FacesMessage.SEVERITY_ERROR,"loginform");	}
			
			return "login";
		
		}else if(!this.forgotUsername && this.forgotPassword){
			UserManager userManager = (UserManager)getBean("userManager");
			if(userManager.sendPassword(this.username))
			{	this.actionType="sentPassword";
				addMessage(getMsgString("PasswordSent"), FacesMessage.SEVERITY_INFO,"loginform");	}
			else
			{	this.actionType="doLogin";
				addMessage(getMsgString("UserNotFound"), FacesMessage.SEVERITY_ERROR,"loginform");	}

			return "login";

		}else if(!this.forgotPassword && !this.forgotUsername){
			this.actionType="doLogin";
			return doLogin();
		}
		
		return "login";
	}
	
	public String doLogout(){
		
		FacesContext fCtx = FacesContext.getCurrentInstance(); 
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
		
		String userRole = ((User)session.getAttribute("user")).getUserrole();
		
		if(session!=null){
			session.invalidate();
		}

		if(userRole.equalsIgnoreCase(VidyoConstants.USER_ROLE_ADMIN)){
			return "adminlogin";
		}else if(userRole.equalsIgnoreCase(VidyoConstants.USER_ROLE_USER)){
			return "userlogin";	
		}
			
		return "index";	
	
	}


	
	public String doLogin(){
		
		System.out.println("Start Login");

		UserManager userManager = (UserManager)getBean("userManager");
		User user = userManager.authenricateUser(this.username, this.password);
		if(user == null){
			this.actionType="doLogin";
			addMessage(getMsgString("AutheticationFailed") , FacesMessage.SEVERITY_ERROR,"loginform");
		}else if(user.getStatus().equals("0")){
			this.actionType="doLogin";
			addMessage(getMsgString("UnActivated"), FacesMessage.SEVERITY_INFO,"loginform");
		}else if(user.getEnabled().equals("0")){
			this.actionType="doLogin";
			addMessage(getMsgString("UserLocked"), FacesMessage.SEVERITY_INFO,"loginform");
		}		
		else{
			setUserInsession(user);
			
			if(this.rememberMe){
				Cookie vidyoUser = new Cookie("vidyousername", username);
				Cookie vidyoPass = new Cookie("vidyopassword",password);
				Cookie vidyoId = new Cookie("vidyoremember","true");
				vidyoUser.setMaxAge(3600);
				vidyoPass.setMaxAge(3600);
				((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(vidyoUser);
				((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(vidyoPass);
				((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(vidyoId);			

			}else{
				Cookie vidyoUser = new Cookie("vidyousername", "");
				Cookie vidyoPass = new Cookie("vidyopassword","");
				Cookie vidyoId = new Cookie("vidyoremember","false");	
				((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(vidyoUser);
				((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(vidyoPass);
				((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).addCookie(vidyoId);			
			}
			
    		try{
    			if(user.getUserrole().equals(VidyoConstants.USER_ROLE_ADMIN)){
    				FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/manageadmin.jsf");
    			}else{
    				
    				String nextPage=(String)getCurrentSession().getAttribute("nextpage");
    				if(nextPage==null){
    					nextPage="../user/createmeeting.jsf";
    				}
    				FacesContext.getCurrentInstance().getExternalContext().redirect(nextPage);
    			}
    		}
    		catch(Exception ex){
    			
    		}
		}
		return "login";
	}

    public void  activateUser(){
    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    	String sessionid = params.get("sessionid");
    	
    	UserManager userManager = (UserManager)getBean("userManager");
    	
    	User user = userManager.activateUser(sessionid);
    	if(user!=null){
    		setUserInsession(user);
    		try{
    			FacesContext.getCurrentInstance().getExternalContext().redirect("../user/createmeeting.jsf");
    		}
    		catch(Exception ex){
    			
    		}
    	}
    }

    public void setUserInsession(User user){
		FacesContext fCtx = FacesContext.getCurrentInstance(); 
		HttpSession session = ((HttpServletRequest)fCtx.getExternalContext().getRequest()).getSession(true); 
	
		Enumeration<String> attrs= session.getAttributeNames();
		session.setAttribute("user", user);
		
		VidyoUserService vidyoUserService = (VidyoUserService)getBean("vidyoUserService");
		vidyoUserService.setAuthentication(user.getUsername() , user.getApiPassword());

		
		if(rememberMe){
			//HttpServletRequest request;
			//request.getCookies();
		}
		
    }
	
	public boolean isForgotPassword() {
		return forgotPassword;
	}


	public void setForgotPassword(boolean forgotPassword) {
		this.forgotPassword = forgotPassword;
	}


	public boolean isForgotUsername() {
		return forgotUsername;
	}


	public void setForgotUsername(boolean forgotUsername) {
		this.forgotUsername = forgotUsername;
	}


	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmailaddress() {
		return emailaddress;
	}


	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
}
