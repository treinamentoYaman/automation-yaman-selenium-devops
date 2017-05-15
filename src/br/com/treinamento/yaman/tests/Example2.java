package br.com.treinamento.yaman.tests;

import java.io.Serializable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.pageObjects.ExampleWelcome;

/**
 * Yaman<BR>
 * 
 * @author Gabriel Aguido Fraga<BR>
 */
public class Example2 implements Serializable {

	private static final long serialVersionUID = -525004464331578037L;
	private WebDriver driver;
	
	@Before
	public void antes() throws Exception{
		
		driver = Utils.initializeChromeDriver();
		
	}
	
	@Test
	public void teste() throws Exception {

		try {

			ExampleWelcome paginaExample = new ExampleWelcome(driver);
			paginaExample.open("https://www.opiniaoburgerking.com.br/");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@After
	public void depois(){
		
		driver.quit();
	}
	
}
