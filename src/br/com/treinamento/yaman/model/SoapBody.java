
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class SoapBody {

    private CalcPrazoResponse calcPrazoResponse;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public CalcPrazoResponse getCalcPrazoResponse() {
        return calcPrazoResponse;
    }

    public void setCalcPrazoResponse(CalcPrazoResponse calcPrazoResponse) {
        this.calcPrazoResponse = calcPrazoResponse;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
