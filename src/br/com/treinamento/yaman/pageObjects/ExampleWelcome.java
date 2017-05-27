package br.com.treinamento.yaman.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExampleWelcome {

	protected WebDriver driver;

	@FindBy(name="q")
	private WebElement campoPesquisa;
	
	
	
	//------------------------- getter and setters -------------------------

	public void setCampoPesquisa(WebElement campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}


	public WebElement getCampoPesquisa() {
		return campoPesquisa;
	}

	
	//Gerar logica (METODO) para obter os valores dos indices da tabela


	//------------------------------------------------------------------------------------------------------------------------------

	public ExampleWelcome(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void open(String url) {
		driver.get(url);
	}

	public void close() {
		driver.quit();
	}

	public String getTitle() {
		return driver.getTitle();
	}

}