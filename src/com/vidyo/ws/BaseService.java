package com.vidyo.ws;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BaseService implements Serializable {

	private static Logger LOGGER = Logger.getLogger(BaseService.class);

	@Resource
	protected WebServiceContext wsContext;
	
	public Object getBean(String beanName){
		MessageContext mc = wsContext.getMessageContext();
       	HttpServletRequest request =  ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)); 
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		Object object = ctx.getBean(beanName);
		return  object;
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
	
	public HttpSession getCurrentSession(){
		MessageContext mc = wsContext.getMessageContext();
		HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();

		return session;
	}
	

	
}
