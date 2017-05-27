package br.com.treinamento.yaman.tests;

import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.treinamento.yaman.helper.LerArquivo;
import br.com.treinamento.yaman.helper.SoapHelper;
import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.constants.ConstantsServices;
import br.com.treinamento.yaman.constants.ViewConstants;

/**
 * Yaman<BR>
 * 
 * @author Gabriel Aguido Fraga<BR>
 */
public class Exampl2 implements Serializable {

	private static final long serialVersionUID = 1722547517156957716L;

	private Map<String, String> valoresMassa = new HashMap<String, String>();
	public Properties urlsServicos = null;
	public Properties links = null;
	String ENDPOINT = null;
	String request = null;
	String response = null;

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
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@After
	public void depois(){
		
	}
	
}
