package application;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	@Test
	public void teste() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		
		//Posicao da tela do browser
		//driver.manage().window().setPosition(new Point(100, 100));
		
		//Tamanho da tela do browser
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("http://www.google.com");
		
		Assert.assertEquals("Google", driver.getTitle());
		
		//Fechando o browser
		driver.quit();
	}
}
