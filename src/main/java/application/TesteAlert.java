package application;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	@Test
	public void deveTestarAlerta() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		Alert alerta = driver.switchTo().alert();
		String texto = alerta.getText();
		Assert.assertEquals("Alert Simples", texto);
		
		alerta.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		
		//Alerta ao aceitar
		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		
		//Alerta ao negar
		driver.findElement(By.id("confirm")).click();
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.dismiss();
		
		driver.quit();

	}
	
	@Test
	public void deveInteragirPrompt() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		
		driver.quit();
	}

}
