package br.com.treinamento.yaman.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.treinamento.yaman.constants.Constants;
import br.com.treinamento.yaman.constants.ViewConstants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author Gabriel Fraga
 * 
 * Essa classe tem como finalidade ser uma classe helper,
 * contendo vários métodos que podem ser usados por diversas classes de forma genérica
 * 
 * 
 */
public class Utils {


	/**
	 * Initialize firefox driver.
	 *
	 * @return the web driver
	 */
	public static WebDriver initializeFirefoxDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver initializeChromeDriver() throws Exception {

		File file = new File(Constants.CHROMEDRIVER_EXE);

		if (!file.exists()) {

			throw new Exception("Erro ao localizar o driver");
		}

		ChromeDriverService chromeService = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(Constants.CHROMEDRIVER_EXE))
				.usingAnyFreePort().build();

		chromeService.start();

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");

		RemoteWebDriver driver = new RemoteWebDriver(chromeService.getUrl(), capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		return driver;
	}

	@Deprecated
	public static WebDriver initializeIEDriver() {
		File file = new File("C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		//

		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * @author Gabriel Fraga
	 * 
	 * Método responsável por carregar os linls de configuração dentro do arquivo properties
	 * 
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static Properties carregarLinks() throws FileNotFoundException, IOException, URISyntaxException {
		Properties ids = new Properties();

		if(!Inet4Address.getLocalHost().getHostName().toUpperCase().contains("local")){ //máquina local
			File file = new File(Constants.configProperties);
			ids.load(new FileInputStream(file));
		} else {
			File file = new File(ViewConstants.Properties.CONFIG_LINKS);
			ids.load(new FileInputStream(file));
		}

		return ids;
	}

	/**
	 * @author Gabriel Aguido Fraga
	 * 
	 * Método responsável por carregar os links de configuração dentro do arquivo servico.properties
	 * 
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static Properties carregarUrlsServicos() throws FileNotFoundException, IOException, URISyntaxException {

		Properties ids = new Properties();

		if(!Inet4Address.getLocalHost().getHostName().toUpperCase().contains("VININM701LIV")){ //máquina local
			File file = new File(Constants.servicosProperties);
			ids.load(new FileInputStream(file));
		} else {
			File file = new File(ViewConstants.Properties.CONFIG_SERVICOS);
			ids.load(new FileInputStream(file));
		}

		return ids;
	}

	public static BufferedImage getScreenshot(WebDriver driver, WebElement element, Boolean crop, Boolean highlight) throws IOException {

		final BufferedImage img;

		int xNovaImagem;
		int yNovaImagem;
		int larguraNovaImagem;
		int alturaNovaImagem;

		if (element != null && highlight) {

			try{
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+(element.getLocation().y-250)+");");
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid #cc008f'", element);
			} catch (RasterFormatException e){
				e.printStackTrace();
				driver.navigate().refresh();
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid #cc008f'", element);
			}
		}

		img = ImageIO.read(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));

		if (element != null && crop) {
			int larguraImagem = img.getWidth();
			int alturaImagem = img.getHeight();
			int x = element.getLocation().getX();
			int y = element.getLocation().getY();
			int larguraElemento = element.getSize().getWidth();
			int alturaElemento = element.getSize().getHeight();

			if (y - 200 < 0) {
				yNovaImagem = 0;
			} else {
				yNovaImagem = y - 200;
			}

			if (x - 200 < 0) {
				xNovaImagem = 0;
			} else {
				xNovaImagem = x - 200;
			}

			if (x + larguraElemento + 200 > larguraImagem) {
				larguraNovaImagem = (x + larguraElemento) + (larguraImagem - (x + larguraElemento)); 
			} else {
				larguraNovaImagem = larguraElemento + 400;
			}

			if (y + alturaElemento + 200 > alturaImagem) {
				alturaNovaImagem = (y + alturaElemento) + (alturaImagem - (y + alturaElemento));
			} else {
				alturaNovaImagem = alturaElemento + 400;
			}

			try{
				return img.getSubimage(xNovaImagem, yNovaImagem, larguraNovaImagem, alturaNovaImagem);
			} catch (RasterFormatException rfe){

			}
		}

		alturaNovaImagem = img.getHeight();
		larguraNovaImagem = img.getWidth();

		if(img.getHeight() > Constants.screen.getHeight()) {
			alturaNovaImagem = (int) Constants.screen.getHeight();
		}

		if (img.getWidth() > Constants.screen.getWidth()) {
			larguraNovaImagem = (int) Constants.screen.getWidth();
		}

		return img.getSubimage(0, 0, larguraNovaImagem, alturaNovaImagem);
	}


	/**
	 * Yaman<BR>
	 *
	 * @author Gabriel Aguido Fraga<BR>
	 * @throws Exception 
	 */
	public static void generateImage(String sampleText, String fileName) throws Exception {

		try {

			String[] outputs = sampleText.split("\n");

			int w = 900; // largura
			int h = 400; // altura

			// calcula a altura de acordo com a quantidade de linhas
			for (int i = 0; i < outputs.length; i++) {

				h += 12;
			}

			// create a File Object
			File newFile = new File(fileName);

			// create the font you wish to use
			Font font = new Font("Arial", Font.PLAIN, 12);

			// create the FontRenderContext object which helps us to measure the text
			FontRenderContext frc = new FontRenderContext(null, true, true);

			// get the height and width of the text
			//			Rectangle2D bounds = font.getStringBounds(sampleText, frc);
			//			int w = (int) bounds.getWidth();
			//			int h = (int) bounds.getHeight();

			// create a BufferedImage object
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			// calling createGraphics() to get the Graphics2D
			Graphics2D g = image.createGraphics();

			// set color and other parameters
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, w, h);
			g.setColor(Color.BLACK);
			g.setFont(font);

			//g.drawString(sampleText, (float) bounds.getX(), (float) -bounds.getY());

			TextLayout layout = new TextLayout(sampleText, font, frc);

			for (int i = 0; i < outputs.length; i++) {

				g.drawString(outputs[i], 15, (int) (15 + i * layout.getBounds().getHeight() + 0.5));
			}

			// releasing resources
			g.dispose();

			// creating the file
			ImageIO.write(image, "jpeg", newFile);

		} 
		catch (Exception e) {

			throw new Exception("Erro ao converter o texto em imagem", e);
		}
	}

	/**
	 * Yaman<BR>
	 *
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static String toJsonString(Object o) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return gson.toJson(o);
	}

	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature inicializa o driver do dispositivo Android<BR>
	 *
	 * @since 7 de jul de 2016 06:05:51
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static AndroidDriver<AndroidElement> initializeAndroidDriver(String deviceName, String appPath) throws InterruptedException, IOException, URISyntaxException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);

		if (appPath == null) {
			//caps.setCapability(MobileCapabilityType.APP, "Browser");
			//caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			caps.setCapability(MobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
			caps.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
		} else {
			caps.setCapability(MobileCapabilityType.APP, new File(appPath).getAbsolutePath());
			//caps.setCapability(MobileCapabilityType.APP_PACKAGE, Constants.pacote);
			//caps.setCapability(MobileCapabilityType.APP_ACTIVITY, Constants.classe);
		}

		if (deviceName == null){ 
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Emulator"); 
		} else {
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName); 
		}

		if(verificarSeHaDispositivosAndroidAtivos()){
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.MINUTES);
			return driver;
		} else {
			Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.EMULATOR_PATH)+"emulator -netdelay none -netspeed full -avd Nexus_5X_API_25");
			System.out.println(aguardarDispositivoAndroidLigar());
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.MINUTES);
			return driver;
		}

	}


	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature Aguarda o dispositivo ligar para tomada de qualquer decisão<BR>
	 *
	 * @since 7 de jul de 2016 06:06:20
	 * @author Gabriel Aguido Fraga<BR>
	 */
	protected static String aguardarDispositivoAndroidLigar() throws IOException, InterruptedException, URISyntaxException{

		Process proc = Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb shell getprop init.svc.bootanim");

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		try{
			// read the output from the command
			String s = "";
			if (!(s = stdError.readLine()).contains("HUEHUE")) {
				//enquanto não encontrar (nunca irá rs) a palavra 'HUEHUE', 
				//re-executa o comando de verificar se esta ativo o dispositivo;
				aguardarDispositivoAndroidLigar();
			}

		} catch(NullPointerException npe){
			String s = "";
			if (!(s = stdInput.readLine()).contains("stopped")) {
				//Quando encontrar o resultado do comando 'stopped', é porque o
				//dispositivo ja esta aberto e tudo está ok;
				aguardarDispositivoAndroidLigar();
			}
		}

		return "Dispositivo Carregado!";

	}

	/**
	 * Yaman<BR>
	 *
	 * Feature Faz a verificação se já existe algum dispositivo Android ativo para tomada de qualquer decisão<BR>
	 *
	 * @since 7 de jul de 2016 06:06:47
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static boolean verificarSeHaDispositivosAndroidAtivos() throws IOException, URISyntaxException{

		Process proc = Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb shell getprop init.svc.bootanim");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		try{
			String s = "";
			if ((s = stdInput.readLine()).contains("stopped")) {
				//Quando encontrar o resultado do comando 'stopped', é porque o
				//dispositivo ja esta aberto e tudo está ok;
				System.out.println("Rodando testes em dispositivo físico");
				return true;
			}
		} catch (NullPointerException npe){
			return false;
		}

		return false;
	}

	/**
	 * Yaman<BR>
	 *
	 * Feature Faz a verificação se já existe algum dispositivo Android ativo para tomada de qualquer decisão<BR>
	 *
	 * @since 7 de jul de 2016 06:06:47
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static boolean desinstalarAplicativosAppium() throws IOException, URISyntaxException{

		Process proc = Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb uninstall io.appium.settings");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		try{
			String s = "";
			s += stdInput.readLine();

		} catch (NullPointerException npe){
			return false;
		}


		Process proc1 = Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb uninstall io.appium.unlock");
		BufferedReader stdInput1 = new BufferedReader(new InputStreamReader(proc1.getInputStream()));

		try{
			String s = "";
			s += stdInput1.readLine();
		} catch (NullPointerException npe){
			return false;
		}

		return false;

	}

	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature Inicializa o driver do iOS para a toma de qualquer decisão<BR>
	 *
	 * @since 7 de jul de 2016 06:07:34
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static WebDriver initializeiOSDriver(String deviceName, String appPath) throws InterruptedException, IOException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
		if (deviceName == null)	caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus"); else caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);//nome do dispositivo
		caps.setCapability(MobileCapabilityType.APP, appPath);
		//caps.setCapability(MobileCapabilityType.UDID, "8ab872d5a64f6e7fdcc7b5dab6c4914cb4b05ccf");

		Runtime.getRuntime().exec("open -a Simulator --args");

		return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	}

	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature Inicializa o servidor Appium/Selenium para que possa ser executado os comandos mobile nos dispositivos<BR>
	 *
	 * @since 7 de jul de 2016 06:07:50
	 * @author Gabriel Aguido Fraga<BR>
	 * @throws URISyntaxException 
	 */
	public static AppiumServer initializeServer() throws ExecuteException, IOException, InterruptedException, URISyntaxException{

		stopServer();
		ServerArguments serverArguments = new ServerArguments();
		serverArguments.setArgument("--address", "127.0.0.1");
		serverArguments.setArgument("--port", 4723);
		serverArguments.setArgument("--platform-name", "Android");
		//serverArguments.setArgument("--platform-version", 23);
		serverArguments.setArgument("--automation-name", "Appium");
		serverArguments.setArgument("--local-timezone", true);
		AppiumServer appiumServer = new AppiumServer(serverArguments);
		appiumServer.startServer(60000);

		//desinstalarAplicativosAppium();

		return appiumServer;
	}

	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature Para o servidor Appium/Selenium<BR>
	 *
	 * @since 7 de jul de 2016 06:08:18
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static void stopServer() throws IOException{

		System.out.println("Stoping server...");

		StringBuilder command = new StringBuilder();
		command.append("cmd /c echo off")
		.append(" & ")
		.append("FOR /F \"usebackq tokens=5\" %p in (`netstat -nao ^| findstr /R /C:\"4723\"`) ")
		.append("do (FOR /F \"usebackq\" %t in (`TASKLIST /FI \"PID eq %p\" ^| findstr /I node.exe`) ")
		.append("do taskkill /F /PID %p)");

		Runtime.getRuntime().exec(command.toString());

		System.out.println("Server stopped...");

	}

	public static void closeEmulator() throws FileNotFoundException, IOException, URISyntaxException{
		Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb emu kill");
	}

	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature Devido a problemas de performance, via linha de comando, são executados inserções de dados<BR>
	 *
	 * @since 7 de jul de 2016 06:10:45
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static void insertValueOnByADB(String value) throws IOException, InterruptedException, URISyntaxException {
		Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb -s "+getDeviceName()+" shell input text "+value);
	}

	/**
	 * 
	 * Yaman<BR>
	 *
	 * Feature Obtem o nome do dispositivo<BR>
	 *
	 * @since 7 de jul de 2016 06:11:26
	 * @author Gabriel Aguido Fraga<BR>
	 */
	public static String getDeviceName() throws IOException, InterruptedException, URISyntaxException{

		Process exec = Runtime.getRuntime().exec(carregarLinks().getProperty(ViewConstants.Commands.ADB_PATH)+"adb devices -l");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(exec.getInputStream()));

		String s = "";
		while ((s = stdInput.readLine()) != null) {

			if(s.contains("device ")){
				String[] split = s.split("device ");
				return split[0].trim();
			}
		}

		Thread.sleep(2000);
		return null;
	}

}