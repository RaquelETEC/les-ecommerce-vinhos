import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

class CRUDTesteExcluir {

	@Test
	void testExcluir() {
		
		
		WebDriver browser = new EdgeDriver();
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");
		
		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));
		

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);
		
		
		browser.findElement(By.className("button-dados-perfil")).click();
		browser.findElement(By.className("cartoes")).click();
		
	
		WebElement botaoExcluir = browser.findElement(By.id("btnExcluir"));
		botaoExcluir.click();
	    browser.switchTo().alert().accept();

		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");
		
		WebElement botaoExcluirCliente = browser.findElement(By.id("BotaoExcluir"));
		botaoExcluirCliente.click();
		browser.switchTo().alert().accept();

	}

}
