
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicos {

    @SerializedName("cServico")
    @Expose
    private CServico cServico;

    public CServico getCServico() {
        return cServico;
    }

    public void setCServico(CServico cServico) {
        this.cServico = cServico;
    }

}
