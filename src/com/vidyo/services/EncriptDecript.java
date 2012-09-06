package com.vidyo.services;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
    
public class EncriptDecript {
    private static Logger LOGGER = Logger.getLogger(EncriptDecript.class);
    
	private static String algorithm = "DESede";
    private static Cipher cipher = null;
    private static SecretKey key = null;

    private static void init() {
    	try {
	    	DESKeySpec keySpec = new DESKeySpec("ThisIsVidyoIncSecret".getBytes("UTF8")); 
	    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    	key = keyFactory.generateSecret(keySpec);
	    	cipher = Cipher.getInstance(algorithm);
    	} 
    	catch (Exception e) {
    		
    	}
    }


    public static String encrypt(String plainTextPassword) {
    	String encrypedPwd =plainTextPassword;
    	try{
    		init();
	    	sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
	    	byte[] cleartext = plainTextPassword.getBytes("UTF8");      
	    	Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
	    	cipher.init(Cipher.ENCRYPT_MODE, key);
	    	encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));
    	}
    	catch(Exception ex){
    		LOGGER.error("Encryption failed",ex);
    	}
    	return encrypedPwd;
    }
    
    public static  String decrypt(String encrypedPwd){
 
    	String dycryptedPwd = encrypedPwd;
    	try{
    	init();
        sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] encrypedPwdBytes = base64decoder.decodeBuffer(encrypedPwd);
        	
    	Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
    	cipher.init(Cipher.DECRYPT_MODE, key);
    	byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
    	dycryptedPwd = new String(plainTextPwdBytes, "US-ASCII");
    	}
    	catch(Exception ex){
    		LOGGER.error("Dycryption failed",ex);
    	}
    	return dycryptedPwd;
    }

}