
package br.com.treinamento.yaman.model;

import java.util.HashMap;
import java.util.Map;

public class CServico {

    private String codigo;
    private String prazoEntrega;
    private String entregaDomiciliar;
    private String entregaSabado;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(String prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getEntregaDomiciliar() {
        return entregaDomiciliar;
    }

    public void setEntregaDomiciliar(String entregaDomiciliar) {
        this.entregaDomiciliar = entregaDomiciliar;
    }

    public String getEntregaSabado() {
        return entregaSabado;
    }

    public void setEntregaSabado(String entregaSabado) {
        this.entregaSabado = entregaSabado;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
