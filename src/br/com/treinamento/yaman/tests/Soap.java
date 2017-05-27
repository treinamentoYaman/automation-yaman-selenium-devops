package br.com.treinamento.yaman.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.treinamento.yaman.constants.Constants;
import br.com.treinamento.yaman.constants.ConstantsServices;
import br.com.treinamento.yaman.constants.ViewConstants;
import br.com.treinamento.yaman.helper.LerArquivo;
import br.com.treinamento.yaman.helper.SoapHelper;
import br.com.treinamento.yaman.helper.Utils;

public class Soap {

	private String url;
	private String cep1 = "06472001";
	private String cep2 = "06220090";
	private String codServico = "40010";
	private Map<String, String> valoresMassa = new HashMap<String, String>();
	
	@Before
	public void antes() throws FileNotFoundException, IOException, URISyntaxException {
		url = Utils.carregarUrlsServicos()
				.getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CALCPRAZO);
	}

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
		
		System.out.println(transacao);
		
	}

	@After
	public void depois() {

	}

}
