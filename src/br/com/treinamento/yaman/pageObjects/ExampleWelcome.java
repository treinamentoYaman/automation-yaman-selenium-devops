package br.com.treinamento.yaman.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExampleWelcome {

	protected WebDriver driver;

	@FindBy(id="SurveyCode")
	private WebElement cpnjField;
	
	@FindBy(id="InputDay")
	private WebElement day;
	
	@FindBy(id="InputMonth")
	private WebElement month;
	
	@FindBy(id="InputYear")
	private WebElement year;
	
	@FindBy(id="InputHour")
	private WebElement hour;
	
	@FindBy(id="InputMinute")
	private WebElement minute;
	
	@FindBy(id="InputMeridian")
	private WebElement PMorAM;
	
	@FindBy(id="NextButton")
	private WebElement nextButton;
	
	//------------------------- getter and setters -------------------------

	public WebElement getCpnjField() {
		return cpnjField;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public void setNextButton(WebElement nextButton) {
		this.nextButton = nextButton;
	}

	public WebElement getDay() {
		return day;
	}

	public void setDay(WebElement day) {
		this.day = day;
	}

	public WebElement getMonth() {
		return month;
	}

	public void setMonth(WebElement month) {
		this.month = month;
	}

	public WebElement getYear() {
		return year;
	}

	public void setYear(WebElement year) {
		this.year = year;
	}

	public WebElement getHour() {
		return hour;
	}

	public void setHour(WebElement hour) {
		this.hour = hour;
	}

	public WebElement getMinute() {
		return minute;
	}

	public void setMinute(WebElement minute) {
		this.minute = minute;
	}

	public WebElement getPMorAM() {
		return PMorAM;
	}

	public void setPMorAM(WebElement pMorAM) {
		PMorAM = pMorAM;
	}

	public void setCpnjField(WebElement cpnjField) {
		this.cpnjField = cpnjField;
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