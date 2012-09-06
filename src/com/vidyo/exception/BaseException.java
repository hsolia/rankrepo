package com.vidyo.exception;

import org.apache.log4j.Logger;

public class BaseException extends RuntimeException {

	private static Logger LOGGER = Logger.getLogger(BaseException.class);
	
	public BaseException(){
		super();
	}
	
	public BaseException(String messageKey, Throwable throwable){
		super();
		LOGGER.info(messageKey,throwable);
	}
}
