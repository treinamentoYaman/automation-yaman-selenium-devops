package br.com.treinamento.yaman.constants;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Yaman<BR>
 *
 * @author Gabriel Aguido Fraga<BR>
 *         Yaman<BR>
 * 
 *         automation
 */
public class Constants {

	public static String pacote = "br.com.yaman.treinamento";
	public static String classe = "br.com.yaman.treinamento.ui.splash.SplashActivity";

	public static String configProperties = "./utilitarios/properties/config.properties";
	public static String servicosProperties = "./utilitarios/properties/servicos.properties";
	
	public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public static String CHROMEDRIVER_EXE = "./utilitarios/drivers/chromedriver_win32/chromedriver.exe";
	public static String APP_PATH = "./calculadora.apk";
	
	public static int NUMERO_TENTATIVAS = 5;
	
	public static class Commands {

		public static final String ADB_PATH = "adb_path";
		public static final String EMULATOR_PATH = "emulator_path";
	}
	
}
