package com.vidyo.ws;

import java.util.Enumeration;

import javax.jws.WebService;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.vidyo.beans.User;
import com.vidyo.services.UserManager;
import com.vidyo.webservices.common.VidyoUserService;


public class CommonUtil extends BaseService
{
	


    public void setUserInsession(User user){
		HttpSession session = getCurrentSession(); 
		Enumeration<String> attrs= session.getAttributeNames();
		session.setAttribute("user", user);
    }	
	
  public boolean isLoggedIn(){
	  HttpSession session = getCurrentSession();
	  if(session.getAttribute("user")!=null){
		  return true;
	  }
	  else{ return false;	}
  }
  
}