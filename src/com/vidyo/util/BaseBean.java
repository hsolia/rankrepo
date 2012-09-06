package com.vidyo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BaseBean implements ApplicationContextAware  {
	public static ApplicationContext appCtx = null;
	
	public static ApplicationContext getApplicationContext() {
        return appCtx;
    }
  	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.appCtx = applicationContext;
    }
}
