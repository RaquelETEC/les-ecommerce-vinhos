import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

class CRUDTesteConsultaAltera {

	@Test
	public void testeConsultaAlterar() {

		WebDriver browser = new EdgeDriver();
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

		WebElement campoNome = browser.findElement(By.id("nome"));
		campoNome.sendKeys("Rodrigo Rocha");

		WebElement botaoConsultar = browser.findElement(By.id("btnBuscar"));
		botaoConsultar.click();

		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);

		// Dados pessoais
		WebElement campoNome2 = browser.findElement(By.id("typeNome"));
		campoNome2.clear();
		campoNome2.sendKeys("Rodrigo Rocha Silva");

		WebElement campoEmail = browser.findElement(By.id("typeEmail"));
		campoEmail.clear();
		campoEmail.sendKeys("rodrigorochaSilva@gmail.com");

		WebElement botaoAtualizar = browser.findElement(By.className("button-salvar"));
		botaoAtualizar.click();
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

	}

}
