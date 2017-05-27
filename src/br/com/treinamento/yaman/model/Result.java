
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("soap:Envelope")
    @Expose
    private SoapEnvelope soapEnvelope;

    public SoapEnvelope getSoapEnvelope() {
        return soapEnvelope;
    }

    public void setSoapEnvelope(SoapEnvelope soapEnvelope) {
        this.soapEnvelope = soapEnvelope;
    }

}
