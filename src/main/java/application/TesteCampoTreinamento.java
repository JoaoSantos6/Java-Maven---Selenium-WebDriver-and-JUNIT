package application;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\joao.santos\\Downloads\\geckodriver-v0.32.2-win64\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().window().setSize(new Dimension(1200, 765));		
		driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void Encerra() {
		driver.quit();
	}

	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita\nQuebra de Linha");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
	}
	
	@Test
	public void deveInteragirComRadioButton() {		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());		
		
	}
	
	@Test
	public void deveInteragirComCheckBox() {	
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());		
	}
	
	@Test
	public void deveInteragirComComboBox() {
				
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(3);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("Mestrado");
		Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());

	}
	
	@Test
	public void testaValoresCombo() {
			
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		
		Assert.assertTrue(encontrou);
		
	}
	
	@Test
	public void deveVerificarValorComboMultiplo() {
				
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		//combo.deselectAll();
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		
		Assert.assertEquals(3, allSelectedOptions.size());

	}
	
	@Test
	public void deveInteragirComBotoes() {
		//driver.findElement(By.id("buttonSimple")).click();
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		
	}
	
	@Test
	//@Ignore (notation para ignorar o teste)
	public void deveInteragirComLinks() {
		driver.findElement(By.linkText("Voltar")).click();
		
		//evitar que gere um falso positivo antes do fim do codigo
		//Assert.fail();
		
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText()); 
		
	}
	
	@Test
	//@Ignore (notation para ignorar o teste)
	public void deveBuscarTextosNaPagina() {
		//System.out.println(driver.findElement(By.tagName("body")).getText());
		
		//Assert.assertTrue(driver.findElement(By.tagName("body"))
		//		.getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", 
				driver.findElement(By.tagName("h3")).getText());
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				driver.findElement(By.tagName("span")).getText());
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				driver.findElement(By.className("facilAchar")).getText());
			
	}
	
}
