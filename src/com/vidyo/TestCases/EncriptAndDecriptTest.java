package com.vidyo.TestCases;
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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
    
    public class EncriptAndDecriptTest {
    private static String algorithm = "DESede";
    private static Cipher cipher = null;
    private static EncriptAndDecriptTest obj = new EncriptAndDecriptTest();
    SecretKey key = null;

    private EncriptAndDecriptTest() {
    try {
    	DESKeySpec keySpec = new DESKeySpec("ThisIsVidyoIncSecret".getBytes("UTF8")); 
    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    	key = keyFactory.generateSecret(keySpec);
    cipher = Cipher.getInstance(algorithm);
    } catch (Exception e) {
    }
    }


    public String getEncryptStringValue(String plainTextPassword) throws InvalidKeyException,
    BadPaddingException,
    IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
    	sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
    	byte[] cleartext = plainTextPassword.getBytes("UTF8");      
    	Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
    	cipher.init(Cipher.ENCRYPT_MODE, key);
    	String encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));
    	return encrypedPwd;
    }
    public String decrypt(byte[] encrypedPwdBytes)
    throws InvalidKeyException,
    BadPaddingException,
    IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
 

    	Cipher cipher = Cipher.getInstance("DES");// cipher is not thread safe
    	cipher.init(Cipher.DECRYPT_MODE, key);
    	byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
    	String s = new String(plainTextPwdBytes, "US-ASCII");
    	return s;
    }
    
    public static void main(String args[]){
    	try{
    	EncriptAndDecriptTest encry = new EncriptAndDecriptTest();
    	String myname =encry.getEncryptStringValue("ra");
    	System.out.println(myname);
    	
    	sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();
    	byte[] encrypedPwdBytes = base64decoder.decodeBuffer(myname);

    	
    	myname = encry.decrypt(encrypedPwdBytes);
    	
    	System.out.println(myname);
    	
    		
/*    		DESKeySpec keySpec = new DESKeySpec("YourSecr".getBytes("UTF8")); 
    		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    		SecretKey key = keyFactory.generateSecret(keySpec);
    		sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
    		sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();


    		// ENCODE plainTextPassword String
    		String plainTextPassword = "Harikrushna";
    		byte[] cleartext = plainTextPassword.getBytes("UTF8");      

    		Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
    		cipher.init(Cipher.ENCRYPT_MODE, key);
    		String encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));
    		
    		System.out.println(encrypedPwd);
    		// now you can store it 

    		// DECODE encryptedPwd String
    		byte[] encrypedPwdBytes = base64decoder.decodeBuffer(encrypedPwd);

    		Cipher cipher1 = Cipher.getInstance("DES");// cipher is not thread safe
    		cipher1.init(Cipher.DECRYPT_MODE, key);
    		byte[] plainTextPwdBytes = (cipher1.doFinal(encrypedPwdBytes));
    		
    		String s = new String(plainTextPwdBytes, "US-ASCII");

    		System.out.println(s);*/
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
}