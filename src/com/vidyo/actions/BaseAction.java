package com.vidyo.actions;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ManagedBean(name="meetingForm")
@ViewScoped
public class BaseAction implements Serializable {

	private static Logger LOGGER = Logger.getLogger(BaseAction.class);

	public Object getBean(String beanName){
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpServletRequest request =  (HttpServletRequest)fCtx.getExternalContext().getRequest();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			
			return ctx.getBean(beanName);
	}
	
	public ApplicationContext getCtx(){
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpServletRequest request =  (HttpServletRequest)fCtx.getExternalContext().getRequest();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		return ctx;
}
	
	public void addMessage(String strMessage, FacesMessage.Severity serverity,String UIName){
    	FacesMessage message = new FacesMessage();
    	message.setSummary(strMessage);
    	message.setSeverity(serverity);
    	
    	FacesContext fc = FacesContext.getCurrentInstance();
    	fc.addMessage(UIName,message);
	}
	
	
	public HttpSession getCurrentSession(){
		FacesContext fCtx = FacesContext.getCurrentInstance(); 
		HttpSession session = ((HttpServletRequest)fCtx.getExternalContext().getRequest()).getSession(false); 
		return session;
	}
	
	public String getMsgString(String msgKey){
		String message = "Unknow messages";
		try{
			FacesContext context = FacesContext.getCurrentInstance(); 
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs"); 
			message = bundle.getString(msgKey);
		}catch(Exception ex){
			LOGGER.error("error in getMsgString in BaseAction",ex);
		}
		return message; 
	}
	
}
