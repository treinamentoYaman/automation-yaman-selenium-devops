package br.com.treinamento.yaman.constants;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Inmetrics<BR>
 *
 * @author Gabriel Aguido Fraga<BR>
 *         Inmetrics<BR>
 * 
 *         automation
 */
public class Constants {

	public static String configProperties = "./utilitarios/properties/config.properties";
	public static String servicosProperties = "./utilitarios/properties/servicos.properties";
	
	public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public static String CHROMEDRIVER_EXE = "./utilitarios/drivers/chromedriver_win32/chromedriver.exe";
	
	public static int NUMERO_TENTATIVAS = 5;
}
