package com.vidyo.exception;

import org.apache.log4j.Logger;

public class ServiceException extends BaseException {

	private static Logger LOGGER = Logger.getLogger(ServiceException.class);
	
	public ServiceException(){
		
	}
	
	public ServiceException(String messageKey, Throwable throwable){
		super(messageKey,throwable);
	}
}
