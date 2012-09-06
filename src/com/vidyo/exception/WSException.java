package com.vidyo.exception;

import org.apache.log4j.Logger;

public class WSException extends ServiceException {

	private static Logger LOGGER = Logger.getLogger(WSException.class);
	
	public WSException(){
		
	}
	
	public WSException(String messageKey, Throwable throwable){
		super(messageKey,throwable);
	}
}
