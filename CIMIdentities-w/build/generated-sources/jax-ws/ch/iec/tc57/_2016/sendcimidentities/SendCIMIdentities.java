
package ch.iec.tc57._2016.sendcimidentities;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SendCIMIdentities", targetNamespace = "http://iec.ch/TC57/2016/SendCIMIdentities", wsdlLocation = "file:/Users/Daniel/Documents/CIM-Identities-fc0a1797f55c45c5655e24dae9adddd7cc27cc94/CIMIdentities-w/src/conf/xml-resources/web-services/CIMIdentities/wsdl/Send_Receive_Reply_CIMIdentities.wsdl")
public class SendCIMIdentities
    extends Service
{

    private final static URL SENDCIMIDENTITIES_WSDL_LOCATION;
    private final static WebServiceException SENDCIMIDENTITIES_EXCEPTION;
    private final static QName SENDCIMIDENTITIES_QNAME = new QName("http://iec.ch/TC57/2016/SendCIMIdentities", "SendCIMIdentities");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/Daniel/Documents/CIM-Identities-fc0a1797f55c45c5655e24dae9adddd7cc27cc94/CIMIdentities-w/src/conf/xml-resources/web-services/CIMIdentities/wsdl/Send_Receive_Reply_CIMIdentities.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SENDCIMIDENTITIES_WSDL_LOCATION = url;
        SENDCIMIDENTITIES_EXCEPTION = e;
    }

    public SendCIMIdentities() {
        super(__getWsdlLocation(), SENDCIMIDENTITIES_QNAME);
    }

    public SendCIMIdentities(WebServiceFeature... features) {
        super(__getWsdlLocation(), SENDCIMIDENTITIES_QNAME, features);
    }

    public SendCIMIdentities(URL wsdlLocation) {
        super(wsdlLocation, SENDCIMIDENTITIES_QNAME);
    }

    public SendCIMIdentities(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SENDCIMIDENTITIES_QNAME, features);
    }

    public SendCIMIdentities(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SendCIMIdentities(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CIMIdentitiesPort
     */
    @WebEndpoint(name = "CIMIdentities_Port")
    public CIMIdentitiesPort getCIMIdentitiesPort() {
        return super.getPort(new QName("http://iec.ch/TC57/2016/SendCIMIdentities", "CIMIdentities_Port"), CIMIdentitiesPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CIMIdentitiesPort
     */
    @WebEndpoint(name = "CIMIdentities_Port")
    public CIMIdentitiesPort getCIMIdentitiesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://iec.ch/TC57/2016/SendCIMIdentities", "CIMIdentities_Port"), CIMIdentitiesPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SENDCIMIDENTITIES_EXCEPTION!= null) {
            throw SENDCIMIDENTITIES_EXCEPTION;
        }
        return SENDCIMIDENTITIES_WSDL_LOCATION;
    }

}
