package page;

import org.openqa.selenium.WebDriver;

import application.DSL;

public class TestesFrameEJanelasPage {

	private DSL dsl;
	private WebDriver driver;

	public TestesFrameEJanelasPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}
}
