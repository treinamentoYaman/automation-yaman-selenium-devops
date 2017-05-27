
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CServico {

    @SerializedName("MsgErro")
    @Expose
    private String msgErro;
    @SerializedName("Codigo")
    @Expose
    private Integer codigo;
    @SerializedName("EntregaSabado")
    @Expose
    private String entregaSabado;
    @SerializedName("Erro")
    @Expose
    private String erro;
    @SerializedName("EntregaDomiciliar")
    @Expose
    private String entregaDomiciliar;
    @SerializedName("PrazoEntrega")
    @Expose
    private Integer prazoEntrega;
    @SerializedName("obsFim")
    @Expose
    private String obsFim;

    public String getMsgErro() {
        return msgErro;
    }

    public void setMsgErro(String msgErro) {
        this.msgErro = msgErro;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEntregaSabado() {
        return entregaSabado;
    }

    public void setEntregaSabado(String entregaSabado) {
        this.entregaSabado = entregaSabado;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getEntregaDomiciliar() {
        return entregaDomiciliar;
    }

    public void setEntregaDomiciliar(String entregaDomiciliar) {
        this.entregaDomiciliar = entregaDomiciliar;
    }

    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getObsFim() {
        return obsFim;
    }

    public void setObsFim(String obsFim) {
        this.obsFim = obsFim;
    }

}
