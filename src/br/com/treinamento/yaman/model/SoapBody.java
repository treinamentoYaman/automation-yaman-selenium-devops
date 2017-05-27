
package br.com.treinamento.yaman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoapBody {

    @SerializedName("CalcPrazoResponse")
    @Expose
    private CalcPrazoResponse calcPrazoResponse;

    public CalcPrazoResponse getCalcPrazoResponse() {
        return calcPrazoResponse;
    }

    public void setCalcPrazoResponse(CalcPrazoResponse calcPrazoResponse) {
        this.calcPrazoResponse = calcPrazoResponse;
    }

}
