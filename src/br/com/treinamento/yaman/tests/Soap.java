package br.com.treinamento.yaman.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONObject;
import org.json.XML;
import org.junit.*;

import com.google.gson.Gson;

import br.com.treinamento.yaman.constants.Constants;
import br.com.treinamento.yaman.constants.ConstantsServices;
import br.com.treinamento.yaman.constants.ViewConstants;
import br.com.treinamento.yaman.helper.LerArquivo;
import br.com.treinamento.yaman.helper.SoapHelper;
import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.model.Result;

public class Soap {

	private String url;
	private String cep1 = "06472001";
	private String cep2 = "06220090";
	private String codServico = "40010";
	private Map<String, String> valoresMassa = new HashMap<String, String>();
	private String prazoEntrega = "1";

	@Before
	public void antes() throws FileNotFoundException, IOException, URISyntaxException {
		url = Utils.carregarUrlsServicos()
				.getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CALCPRAZO);
	}

	@SuppressWarnings("resource")
	@Test
	public void teste() throws Exception {

		String filePath = Utils.carregarLinks().getProperty(ViewConstants.Properties.LAYOUTS_PATH)
				+ getClass().getSimpleName() + ViewConstants.Properties.XML_EXTENSION;

		StringBuilder sb = new LerArquivo().lerArquivo(filePath);

		String template = sb.toString();

		valoresMassa.put(ConstantsServices.CD_SERVICO_LABEL, codServico);
		valoresMassa.put(ConstantsServices.CEP_ORIGEM_LABEL, cep1);
		valoresMassa.put(ConstantsServices.CEP_DESTINO_LABEL, cep2);

		String content = SoapHelper.mergeTemplate(template, valoresMassa);
		String format = SoapHelper.format(content);

		String transacao = SoapHelper.executarTransacao(new StreamSource(new StringReader(SoapHelper.format(format))),
				url, true, ConstantsServices.SOAPACTION_CALCPRAZO, getClass().getSimpleName());

		JSONObject json = XML.toJSONObject(transacao);

		System.out.println(json.toString());

		Result r = new Gson().fromJson(json.toString(), Result.class);

		Assert.assertEquals(prazoEntrega, r.getSoapEnvelope().getSoapBody().getCalcPrazoResponse().getCalcPrazoResult()
				.getServicos().getCServico().getPrazoEntrega().toString());

	}

	@After
	public void depois() {

	}

}
