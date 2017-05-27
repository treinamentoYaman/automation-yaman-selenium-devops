
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class CalcPrazoResult {

    private Servicos servicos;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
