package br.com.treinamento.yaman.constants;

import java.io.Serializable;

public class ConstantsServices implements Serializable{


	private static final long serialVersionUID = -4900383083466150517L;

	
	//==============================VALUES DEFAULT==============================
	
	public static final String CD_SERVICO = "04014";
	public static final String CEP_ORIGEM = "06472-001";
	public static final String CEP_DESTINO = "06220-060";
	
	public static final String CD_SERVICO_LABEL = "\\[CD_SERVICO]";
	public static final String CEP_ORIGEM_LABEL = "\\[CEP_ORIGEM]";
	public static final String CEP_DESTINO_LABEL = "\\[CEP_DESTINO]";
	
	//==============================SOAPAction==============================
	
	public static final String SOAPACTION_CALCPRAZO = "http://tempuri.org/CalcPrazo";
	
}
