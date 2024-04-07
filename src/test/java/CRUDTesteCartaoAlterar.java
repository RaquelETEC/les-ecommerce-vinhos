import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

class CRUDTesteCartaoAlterar {

	@Test
	public void AlterarExcluir() {
		
		WebDriver browser = new EdgeDriver();
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");
		
		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));
		

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);
		
		
		browser.findElement(By.className("button-dados-perfil")).click();
		browser.findElement(By.className("cartoes")).click();
		browser.findElement(By.id("BotaoEditar")).click();
		
		
		WebElement campoCartaoNome = browser.findElement(By.id("CartaoNome"));
		campoCartaoNome.clear();
		campoCartaoNome.sendKeys("TesteAlterar");
		
		WebElement campoCartaoBandeira = browser.findElement(By.id("tipoBandeira"));
		Select bandeira = new Select(campoCartaoBandeira);
		bandeira.selectByValue("2");
		
		WebElement campoPadrao = browser.findElement(By.id("CartaoPadrao"));
		Select padrao = new Select(campoPadrao);
		padrao.selectByValue("NAO");
		
		WebElement botaoEnviar = browser.findElement(By.id("BotaoAlterar"));
		botaoEnviar.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement botaoExcluir = browser.findElement(By.id("btnExcluir"));
		botaoExcluir.click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    browser.switchTo().alert().accept();
		
		
	}

}
