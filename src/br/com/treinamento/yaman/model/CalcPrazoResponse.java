
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class CalcPrazoResponse {

    private String xmlns;
    private CalcPrazoResult calcPrazoResult;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public CalcPrazoResult getCalcPrazoResult() {
        return calcPrazoResult;
    }

    public void setCalcPrazoResult(CalcPrazoResult calcPrazoResult) {
        this.calcPrazoResult = calcPrazoResult;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
