package application;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import page.TestesFrameEJanelasPage;

public class TestesFrameEJanelas {
	
	private TestesFrameEJanelasPage page;
	

	@Test
	public void deveInteragirComFrames() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		//traz para a pagina principal
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		
	}
	
	@Test
	public void deveInteragirComJanelas() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
			
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String )driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String )driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
		driver.quit();
		
	}
}
