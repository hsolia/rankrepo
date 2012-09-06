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
import com.vidyo.common.VidyoConfig;
import com.vidyo.services.EmailManager;
import com.vidyo.services.UserManager;
import com.vidyo.webservices.common.VidyoUserService;



@ManagedBean(name="contactForm")
@ViewScoped
public class ContactFormAction extends BaseAction {


	private String senderName;
	private String senderEmail;
	private String senderPhone;
	private String supportReuest;
	private boolean emailSent = false;
	
	public void sendRequestEmail(String subject){
		VidyoConfig vidyoConfig =(VidyoConfig)getBean("vidyoConfig");
		EmailManager emailManager =(EmailManager)getBean("emailManager");
		
		String emilBody ="<BR/>";
		emilBody +="Name : "+this.senderName+"<BR/>"; 
		emilBody +="Email : "+this.senderEmail+"<BR/>";
		emilBody +="Phone : "+this.senderPhone+"<BR/>";
		emilBody +="Request : "+this.supportReuest+"<BR/>";
		
		emailManager.sendUserEmail(emilBody,subject,vidyoConfig.getSupportEmailAddress(),this.senderEmail);
		
		this.emailSent = true;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getSupportReuest() {
		return supportReuest;
	}
	public void setSupportReuest(String supportReuest) {
		this.supportReuest = supportReuest;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	
}
