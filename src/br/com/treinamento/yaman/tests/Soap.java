package br.com.treinamento.yaman.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.json.JSONObject;
import org.json.XML;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.treinamento.yaman.constants.ConstantsServices;
import br.com.treinamento.yaman.constants.ViewConstants;
import br.com.treinamento.yaman.helper.LerArquivo;
import br.com.treinamento.yaman.helper.SoapHelper;
import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.model.Calculation;

public class Soap {

	private Map<String, String> valoresMassa = new HashMap<String, String>();
	private String endpoint;
	private String request;
	private String response;
	private Calculation c;
	private int dias = 1;

	@Before
	public void antes() throws FileNotFoundException, IOException, URISyntaxException{
		endpoint = Utils.carregarUrlsServicos().getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CALCPRAZO);		
	}
	
	@Test
	public void teste() throws Exception{
		
		StringBuilder sb = new LerArquivo().lerArquivo(Utils.carregarLinks().getProperty(ViewConstants.Properties.LAYOUTS_PATH) + this.getClass().getSimpleName() + ViewConstants.Properties.XML_EXTENSION);
		
		String template = sb.toString();
		
		valoresMassa.put(ConstantsServices.CD_SERVICO_LABEL, ConstantsServices.CD_SERVICO);
		valoresMassa.put(ConstantsServices.CEP_ORIGEM_LABEL, ConstantsServices.CEP_ORIGEM);
		valoresMassa.put(ConstantsServices.CEP_DESTINO_LABEL, ConstantsServices.CEP_DESTINO);
		
		String content = SoapHelper.mergeTemplate(template, valoresMassa);
		request = SoapHelper.format(content);
		
		response = SoapHelper.executarTransacao(new StreamSource(new StringReader(request)), endpoint, true, ConstantsServices.SOAPACTION_CALCPRAZO, this.getClass().getSimpleName());
		
		JSONObject json = XML.toJSONObject(response);
		
		c = new Gson().fromJson(json.toString(), Calculation.class);
		
		Assert.assertTrue(c.getSoapEnvelope().getSoapBody().getCalcPrazoResponse().getCalcPrazoResult().getServicos().getCServico().getPrazoEntrega() == dias);
		
	}

	@After
	public void depois(){
		
		System.out.println("Resultado esperado: " + dias);
		System.out.println("Resultado obtido: " + c.getSoapEnvelope().getSoapBody().getCalcPrazoResponse().getCalcPrazoResult().getServicos().getCServico().getPrazoEntrega());
		
	}
}
