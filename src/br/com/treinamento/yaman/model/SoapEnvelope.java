
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoapEnvelope {

    @SerializedName("xmlns:xsd")
    @Expose
    private String xmlnsXsd;
    @SerializedName("xmlns:soap")
    @Expose
    private String xmlnsSoap;
    @SerializedName("xmlns:xsi")
    @Expose
    private String xmlnsXsi;
    @SerializedName("soap:Body")
    @Expose
    private SoapBody soapBody;

    public String getXmlnsXsd() {
        return xmlnsXsd;
    }

    public void setXmlnsXsd(String xmlnsXsd) {
        this.xmlnsXsd = xmlnsXsd;
    }

    public String getXmlnsSoap() {
        return xmlnsSoap;
    }

    public void setXmlnsSoap(String xmlnsSoap) {
        this.xmlnsSoap = xmlnsSoap;
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

}
