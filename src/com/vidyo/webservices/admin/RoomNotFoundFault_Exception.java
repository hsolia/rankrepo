
package com.vidyo.webservices.admin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "RoomNotFoundFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class RoomNotFoundFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private RoomNotFoundFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public RoomNotFoundFault_Exception(String message, RoomNotFoundFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public RoomNotFoundFault_Exception(String message, RoomNotFoundFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.webservices.admin.RoomNotFoundFault
     */
    public RoomNotFoundFault getFaultInfo() {
        return faultInfo;
    }

}
