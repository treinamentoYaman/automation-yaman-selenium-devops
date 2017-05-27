
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class Servicos {

    private CServico cServico;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public CServico getCServico() {
        return cServico;
    }

    public void setCServico(CServico cServico) {
        this.cServico = cServico;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
