package br.com.treinamento.yaman.helper;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.genium_framework.appium.support.server.AppiumServer;

import br.com.treinamento.yaman.constants.ViewConstants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidTestCase{

	public static Logger logger = Logger.getLogger(AndroidTestCase.class);

	public AndroidDriver<AndroidElement> driver;
	public WebDriver driver2;
	public TreeMap<Integer, String> listaScreenshots = null;
	public Boolean erroEncontrado = Boolean.FALSE;
	public Random random = new Random();
	public Properties links = null;
	private Properties identificadoresAndroid = null;
	private AppiumServer appiumServer;
	private int cont = 0;

	private int tentativas;

	/**
	 * Inicializa o driver do navegador e seta a url para navegacao
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {

		Utils.initializeServer();
		driver = Utils.initializeAndroidDriver(null, links.getProperty(ViewConstants.Properties.APP_PATH));
		
	}
	
	/**
	 * 	
	 * Inmetrics<BR>
	 *
	 * Feature PrintScreen de dispositivos Android<BR>
	 *
	 * @since 23 de ago de 2016 14:01:04
	 * @author Gabriel Aguido Fraga<BR>
	 * @throws IOException 
	 */
	protected int saveMobileScreenShot(AndroidDriver<AndroidElement> driver) throws IOException {
	    
		StringBuilder outputfileName = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime(); 
		String dataFormatada = sdf.format(hora);
		dataFormatada = dataFormatada.replaceAll(":", "");
		outputfileName.append(dataFormatada);
		outputfileName.append(ViewConstants.XpathProperties.PONTO);
		outputfileName.append(ViewConstants.XpathProperties.PNG_EXTENSION);

		File outputfile = new File(outputfileName.toString());
		logger.info(outputfileName.toString());
		
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		BufferedImage imagem = ImageIO.read(driver.getScreenshotAs(OutputType.FILE));		

		int alturaImagem = (int) imagem.getHeight();
		
		ImageIO.write(imagem, ViewConstants.XpathProperties.PNG_EXTENSION, outputfile);
		
		File targetFile = new File(outputfileName.toString());
	    FileUtils.copyFile(srcFile, targetFile);
	    getListaScreenshots().put(++cont, outputfileName.toString());
		
	    return cont;
	}

	/**
	 * 
	 * @author Gabriel Fraga
	 * 
	 * Salva screenshot com uma pequena area de tela em redor do elemento, que fica destacado em vermelho
	 *
	 * @param element Elemento selecionado
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int saveCroppedHighlightedScreenshot(WebElement element, WebDriver wd) throws IOException {
		try{
			return saveScreenshot(element, Boolean.TRUE, Boolean.TRUE);
		} catch (RasterFormatException rfe){
			return saveScreenshot(element, Boolean.FALSE, Boolean.FALSE);
		}
	}

	/**
	 * @author Gabriel Fraga
	 * 
	 * Método responsável por salvar screenshot da tela toda
	 * 
	 * @throws IOException
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public int saveScreenshot(WebElement element, Boolean crop, Boolean highlight) throws IOException {

		StringBuilder outputfileName = new StringBuilder();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		Date hora = Calendar.getInstance().getTime(); 
		String dataFormatada = sdf.format(hora);
		dataFormatada = dataFormatada.replaceAll(":", "");
		outputfileName.append(dataFormatada);
		outputfileName.append(ViewConstants.XpathProperties.PONTO);
		outputfileName.append(ViewConstants.XpathProperties.PNG_EXTENSION);

		File outputfile = new File(outputfileName.toString());
		logger.info(outputfileName.toString());

		BufferedImage imagem = Utils.getScreenshot(driver, element, crop, highlight);

		int alturaImagem = (int) imagem.getHeight();

		ImageIO.write(imagem, ViewConstants.XpathProperties.PNG_EXTENSION, outputfile);

		getListaScreenshots().put(++cont, outputfileName.toString());

		return cont;

	}

	@After
	public void quit() throws IOException {
		
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		appiumServer.stopServer();
		//se houver emuladores, é finalizado
		Runtime.getRuntime().exec(links.getProperty(ViewConstants.Commands.ADB_PATH)+"adb emu kill");
	}

	/**
	 * 
	 * @author Gabriel Fraga
	 * 
	 * Método responsável por inserir valor dentro de um elemento da pagina
	 * 
	 * @param elemento
	 * @param value
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException 
	 */
	public void insertValueOn(WebElement elemento, String value) throws IOException, InterruptedException, URISyntaxException {

		try{
			if (elemento != null) {
				elemento.click();
				Utils.insertValueOnByADB(value);
			}
		} catch (NoSuchElementException e){
			//fail(new LivException(driver, e, workbook));
		}

	}

	public WebElement findAndroidElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<AndroidElement> findAndroidElements(By by) {
		return driver.findElements(by);
	}

	public WebElement chooseRandomAndroidElement(String className) {
		return chooseSpecificAndroidElement(className, null);
	}

	public WebElement chooseSpecificAndroidElement(String className, String num) {

		WebElement elemento = null;

		List<AndroidElement> elementos = new ArrayList<AndroidElement>();

		elementos = findAndroidElementsByClassName(className);

		if (elementos.size() > 0) {

			if (num != null) {

				num = identificadoresAndroid.getProperty(num);

				try {
					elemento = elementos.get(Integer.valueOf(num));
				} catch (Exception e) {
					elemento = elementos.get(random.nextInt(elementos.size()));
				}

			} else {

				elemento = elementos.get(random.nextInt(elementos.size()));
			}

		}

		return elemento;

	}

	/**
	 * @author Gabriel Fraga
	 * 
	 * Método responsável para retornar a lista de screeshots
	 * 
	 * @return
	 */
	public TreeMap<Integer, String> getListaScreenshots() {
		if (listaScreenshots == null) {
			listaScreenshots = new TreeMap<Integer, String>();
		}
		return listaScreenshots;
	}
	
	public void aguardarElemento(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void aguardarElementoAteXsegundos(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public List<AndroidElement> findAndroidElementsByClassName(String className) {
		return findAndroidElements(By.className(identificadoresAndroid.getProperty(className)));
	}
	
}