package br.com.treinamento.yaman.tests;

import java.io.Serializable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.WebDriver;

import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;


import br.com.treinamento.yaman.helper.Utils;
import br.com.treinamento.yaman.pageObjects.GooglePage;

/**
 * Yaman<BR>
 * 
 * @author Gabriel Aguido Fraga<BR>
 */
public class Web implements Serializable {

	private static final long serialVersionUID = -525004464331578037L;
	private WebDriver driver;
	
	@Before
	public void antes() throws Exception{
		
		driver = Utils.initializeChromeDriver();
		
	}
	
	@Test
	public void teste() throws Exception {

		try {

			GooglePage paginaExample = PageFactory.initElements(driver, GooglePage.class);			
			paginaExample.open("https://www.google.com.br/");
			paginaExample.getCampoPesquisa().clear();
		    paginaExample.getCampoPesquisa().sendKeys("orkut");
		    Thread.sleep(2000);
		    
		} catch (Exception e) {
			fail("Aconteceu algum erro");
		}

	}
	
	@After
	public void depois(){
		driver.quit();
	}
	
}
