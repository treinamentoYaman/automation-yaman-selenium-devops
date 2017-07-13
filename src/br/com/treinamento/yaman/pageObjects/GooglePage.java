package br.com.treinamento.yaman.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage {

	@FindBy(name="q")
	private WebElement campo;

	public WebElement getCampo() {
		return campo;
	}

	public void setCampo(WebElement campo) {
		this.campo = campo;
	}
	
}
