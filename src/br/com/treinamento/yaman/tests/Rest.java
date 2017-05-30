package br.com.treinamento.yaman.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.treinamento.yaman.constants.ViewConstants;
import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.model.Endereco;

public class Rest {
	
	private String url;
	private String resposta = "";
	private String cep = "06325070";
	private Endereco e;
	@Before
	public void antes () throws FileNotFoundException, IOException, URISyntaxException{
		
		url = Utils.carregarUrlsServicos().getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CORREIOS);
		
	}
	
	@Test
	public void test () throws MalformedURLException, IOException {
		
		url += cep; 
		
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setRequestMethod("GET");
		//
		BufferedReader br = new BufferedReader (new InputStreamReader(con.getInputStream()));
		
		String linha;
		
		while ((linha = br.readLine()) !=null){
			resposta += linha + "\n";
		}

		e = new Gson().fromJson(resposta, Endereco.class);
		
		Assert.assertEquals(cep, e.getCep());
		
		
		
	}
	
	@After
	public void depois (){
		System.out.println("Resposta do servidor: " + resposta);
		System.out.println("\nValor esperado: " + cep + "\nValor de resposta: " + e.getCep());
	}
}
