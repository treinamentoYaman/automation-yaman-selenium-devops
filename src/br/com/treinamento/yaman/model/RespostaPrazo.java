
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class RespostaPrazo {

    private SoapEnvelope soapEnvelope;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SoapEnvelope getSoapEnvelope() {
        return soapEnvelope;
    }

    public void setSoapEnvelope(SoapEnvelope soapEnvelope) {
        this.soapEnvelope = soapEnvelope;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
