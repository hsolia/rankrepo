
package com.vidyo.webservices.user;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "WrongPinFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class WrongPinFault_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private WrongPinFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public WrongPinFault_Exception(String message, WrongPinFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public WrongPinFault_Exception(String message, WrongPinFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.webservices.user.WrongPinFault
     */
    public WrongPinFault getFaultInfo() {
        return faultInfo;
    }

}
