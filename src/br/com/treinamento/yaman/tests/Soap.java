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
import org.junit.*;

import com.google.gson.Gson;

import br.com.treinamento.yaman.constants.ConstantsServices;
import br.com.treinamento.yaman.constants.ViewConstants;
import br.com.treinamento.yaman.helper.LerArquivo;
import br.com.treinamento.yaman.helper.SoapHelper;
import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.model.Result;

public class Soap {

	private String url;
	private String cepOrigem = "06472001";
	private String cepDestino = "06325070";
	private String codServico = "40010";
	private Map<String, String> valoresMassa = new HashMap<String, String>();
	private String prazoEntrega = "1";

	@Before
	public void antes() throws FileNotFoundException, IOException, URISyntaxException {

		url = Utils.carregarUrlsServicos()
				.getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CALCPRAZO);

	}

	@Test
	public void test() throws Exception {

		// caminho do arquivo
		String nomeArquivo = Utils.carregarLinks().getProperty(ViewConstants.Properties.LAYOUTS_PATH)
				+ getClass().getSimpleName() + ViewConstants.Properties.XML_EXTENSION;

		// leitura do arquivo (filePath - caminho do arquivo)
		StringBuilder sb = new LerArquivo().lerArquivo(nomeArquivo);
		String template = sb.toString();
		// put - colocar
		valoresMassa.put(ConstantsServices.CD_SERVICO_LABEL, codServico);
		valoresMassa.put(ConstantsServices.CEP_ORIGEM_LABEL, cepOrigem);
		valoresMassa.put(ConstantsServices.CEP_DESTINO_LABEL, cepDestino);

		String content = SoapHelper.mergeTemplate(template, valoresMassa);
		String format = SoapHelper.format(content);
		// execut
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
