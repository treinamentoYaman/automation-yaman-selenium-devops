package br.com.treinamento.yaman.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.pageObjects.GooglePage;

public class Web {

	private String url = "http://www.google.com.br";
	private WebDriver driver;
	private GooglePage p;
	
	@Before
	public void antes() throws Exception{
		
		driver = Utils.initializeChromeDriver();
		p = PageFactory.initElements(driver, GooglePage.class);
		driver.get(url);
	}
	
	@Test
	public void teste(){
		
		p.getCampo().sendKeys("Orkut");
		
	}
	
	
	@After
	public void depois() throws InterruptedException{
		
		Thread.sleep(2000);
		driver.close();
		
	}
	
}
