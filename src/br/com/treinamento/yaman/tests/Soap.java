package br.com.treinamento.yaman.tests;

import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
import br.com.treinamento.yaman.model.Result;

/**
 * Yaman<BR>
 * 
 * @author Gabriel Aguido Fraga<BR>
 */
public class Soap implements Serializable {

	private static final long serialVersionUID = 1722547517156957716L;

	private Map<String, String> valoresMassa = new HashMap<String, String>();
	public Properties urlsServicos = null;
	public Properties links = null;
	String ENDPOINT = null;
	String request = null;
	String response = null;
	String prazoEntrega = "1";

	@Before
	public void antes(){
		
	}
	
	@Test
	public void teste() throws Exception {

		try {

			StringBuilder sb = new LerArquivo()
					.lerArquivo(Utils.carregarLinks().getProperty(ViewConstants.Properties.LAYOUTS_PATH)
							+ this.getClass().getSimpleName() + ViewConstants.Properties.XML_EXTENSION);

			String template = sb.toString();

			valoresMassa.put(ConstantsServices.CD_SERVICO_LABEL, ConstantsServices.CD_SERVICO);
			valoresMassa.put(ConstantsServices.CEP_ORIGEM_LABEL, ConstantsServices.CEP_ORIGEM);
			valoresMassa.put(ConstantsServices.CEP_DESTINO_LABEL, ConstantsServices.CEP_DESTINO);

			String content = SoapHelper.mergeTemplate(template, valoresMassa);
			request = SoapHelper.format(content);

			urlsServicos = Utils.carregarUrlsServicos();

			ENDPOINT = urlsServicos.getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CALCPRAZO);

			response = SoapHelper.executarTransacao(new StreamSource(new StringReader(SoapHelper.format(request))),
					ENDPOINT, true, ConstantsServices.SOAPACTION_CALCPRAZO, this.getClass().getSimpleName());

			System.out.println(response);
			
			JSONObject json = XML.toJSONObject(response);

			System.out.println(json.toString());

			Result r = new Gson().fromJson(json.toString(), Result.class);

			Assert.assertEquals(prazoEntrega, r.getSoapEnvelope().getSoapBody().getCalcPrazoResponse().getCalcPrazoResult()
					.getServicos().getCServico().getPrazoEntrega().toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@After
	public void depois(){
		
	}
	
}
