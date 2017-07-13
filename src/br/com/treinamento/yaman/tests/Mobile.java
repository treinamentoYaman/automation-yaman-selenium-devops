package br.com.treinamento.yaman.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.treinamento.yaman.helper.AndroidTestCase;

public class Mobile extends AndroidTestCase {

	@Before
	public void antes(){

		
		
	}

	@Test
	public void teste(){

		driver.findElement(By.id("digit_8")).click();
		driver.findElement(By.id("op_add")).click();
		driver.findElement(By.id("digit_8")).click();
		
		
	}

	@After
	public void depois(){
		
		driver.closeApp();
		
	}	
	
}
