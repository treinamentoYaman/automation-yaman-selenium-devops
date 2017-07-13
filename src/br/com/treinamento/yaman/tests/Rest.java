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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.treinamento.yaman.constants.ViewConstants;
import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.model.Endereco;

public class Rest {

	private String endpoint;
	private String cep = "06220090";
	private String response = "";
	private Endereco e;

	@Before
	public void antes() throws FileNotFoundException, IOException, URISyntaxException{
		
		endpoint = Utils.carregarUrlsServicos().getProperty(ViewConstants.Properties.ServicosProperties.URL_SERVICO_CORREIOS);
		endpoint += cep;
		
	}
	
	@Test
	public void teste() throws IOException{
		
		HttpURLConnection con = (HttpURLConnection) new URL(endpoint).openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String linha = "";
		while((linha = br.readLine()) != null){
			response += linha;
		}
       
		System.out.println(response);
		
		e = new Gson().fromJson(response, Endereco.class);
		
		Assert.assertTrue(e.getCep().equals(cep));
		
	}
	
	@After
	public void depois(){
		
		System.out.println("Resultado esperado: " + cep);
		System.out.println("Resultado obtido: " + e.getCep());
		
	}
	
}
