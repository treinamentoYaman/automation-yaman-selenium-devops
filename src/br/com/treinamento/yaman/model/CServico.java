
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CServico {

    @SerializedName("MsgErro")
    @Expose
    private String msgErro;
    @SerializedName("Codigo")
    @Expose
    private int codigo;
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
    private int prazoEntrega;
    @SerializedName("obsFim")
    @Expose
    private String obsFim;

    public String getMsgErro() {
        return msgErro;
    }

    public void setMsgErro(String msgErro) {
        this.msgErro = msgErro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public int getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(int prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getObsFim() {
        return obsFim;
    }

    public void setObsFim(String obsFim) {
        this.obsFim = obsFim;
    }

}
