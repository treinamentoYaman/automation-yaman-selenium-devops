
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CalcPrazoResponse {

    @SerializedName("xmlns")
    @Expose
    private String xmlns;
    @SerializedName("CalcPrazoResult")
    @Expose
    private CalcPrazoResult calcPrazoResult;

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

}
