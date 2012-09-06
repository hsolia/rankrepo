
package com.vidyo.webservices.content;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "AccessViolationFault", targetNamespace = "http://replay.vidyo.com/apiservice")
public class AccessViolationFault_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private AccessViolationFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public AccessViolationFault_Exception(String message, AccessViolationFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public AccessViolationFault_Exception(String message, AccessViolationFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.webservices.content.AccessViolationFault
     */
    public AccessViolationFault getFaultInfo() {
        return faultInfo;
    }

}
