import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import util.Config;

public class CRUDTesteExcluir {
	private WebDriver driver;

	@Before
	public void setUp() {
		// Obtém o caminho para o diretório do WebDriver dentro do seu projeto
		String driverPath = "src/main/resources/drivers/msedgedriver.exe";

		// Configura o caminho para o WebDriver
		System.setProperty("webdriver.edge.driver", driverPath);

	}

	@Test
	public void testExcluir() {

		WebDriver browser = new EdgeDriver();

		browser.manage().window().maximize();

		browser.navigate().to(Config.baseUrl + "/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);

		browser.findElement(By.className("button-dados-perfil")).click();
		browser.findElement(By.className("cartoes")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement botaoExcluir = browser.findElement(By.id("btnExcluir"));
		botaoExcluir.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		browser.switchTo().alert().accept();

		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

		WebElement botaoExcluirCliente = browser.findElement(By.id("BotaoExcluir"));
		botaoExcluirCliente.click();
		browser.switchTo().alert().accept();

	}

}
