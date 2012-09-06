
package com.vidyo.webservices.user;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "VidyoPortalUserService", targetNamespace = "http://portal.vidyo.com/user/v1_1", wsdlLocation = "http://dev20.vidyo.com/services/v1_1/VidyoPortalUserService?wsdl")
public class VidyoPortalUserService
    extends Service
{

    private final static URL VIDYOPORTALUSERSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.vidyo.webservices.user.VidyoPortalUserService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.vidyo.webservices.user.VidyoPortalUserService.class.getResource(".");
            url = new URL(baseUrl, "http://dev20.vidyo.com/services/v1_1/VidyoPortalUserService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://dev20.vidyo.com/services/v1_1/VidyoPortalUserService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        VIDYOPORTALUSERSERVICE_WSDL_LOCATION = url;
    }

    public VidyoPortalUserService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public VidyoPortalUserService() {
        super(VIDYOPORTALUSERSERVICE_WSDL_LOCATION, new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService"));
    }

    /**
     * 
     * @return
     *     returns VidyoPortalUserServicePortType
     */
    @WebEndpoint(name = "VidyoPortalUserServicePort")
    public VidyoPortalUserServicePortType getVidyoPortalUserServicePort() {
        return super.getPort(new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserServicePort"), VidyoPortalUserServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns VidyoPortalUserServicePortType
     */
    @WebEndpoint(name = "VidyoPortalUserServicePort")
    public VidyoPortalUserServicePortType getVidyoPortalUserServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserServicePort"), VidyoPortalUserServicePortType.class, features);
    }

}
