
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class SoapEnvelope {

    private String xmlnsSoap;
    private String xmlnsXsd;
    private String xmlnsXsi;
    private SoapBody soapBody;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getXmlnsSoap() {
        return xmlnsSoap;
    }

    public void setXmlnsSoap(String xmlnsSoap) {
        this.xmlnsSoap = xmlnsSoap;
    }

    public String getXmlnsXsd() {
        return xmlnsXsd;
    }

    public void setXmlnsXsd(String xmlnsXsd) {
        this.xmlnsXsd = xmlnsXsd;
    }

    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }

    public SoapBody getSoapBody() {
        return soapBody;
    }

    public void setSoapBody(SoapBody soapBody) {
        this.soapBody = soapBody;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
