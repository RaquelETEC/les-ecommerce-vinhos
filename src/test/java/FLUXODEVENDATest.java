import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import util.Config;

import org.openqa.selenium.Dimension;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;

public class FLUXODEVENDATest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		String driverPath = "src/main/resources/drivers/msedgedriver.exe";

		System.setProperty("webdriver.edge.driver", driverPath);
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		vars = new HashMap<>();
		js = (JavascriptExecutor) driver;
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void fLUXODEVENDA() {
		driver.get(Config.baseUrl + "/les-ecommerce-vinhos/");
		driver.manage().window().setSize(new Dimension(1936, 1056));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("btn-add-carrinho-1")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
		driver.findElement(By.id("btn-add-carrinho-3")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
		driver.findElement(By.linkText("MEU CARRINHO")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
		driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
		{
			WebElement element = driver.findElement(By.cssSelector(".col-md-3 > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector("#modalSelecaoEndereco .card:nth-child(1) label")).click();
		driver.findElement(By.cssSelector(".modal-md .modal-footer > .btn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
		{
			WebElement element = driver.findElement(By.cssSelector(".col-md-3 > .btn"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("#modalSelecaoEndereco .card:nth-child(2) label")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".modal-md .modal-footer > .btn")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("SelecionarCupons")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("cupomTselect1")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("#modalCupomT .btn")).click();
		driver.findElement(By.id("SalvarCupom")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("#modalCartoes .card label")).click();
		driver.findElement(By.cssSelector("#modalCartoes .modal-body")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("valorCartao")).click();
		driver.findElement(By.id("valorCartao")).sendKeys("139.23");
		driver.findElement(By.cssSelector(".col-6 > .btn-primary")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("FinalizarCompra")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=20");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("statusPedido3")).click();

		{
			WebElement dropdown = driver.findElement(By.id("statusPedido3"));
			dropdown.findElement(By.xpath("//option[. = 'PAGAMENTO REALIZADO']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector("tr:nth-child(4) .Botao1")).click();
		driver.switchTo().alert().accept();

		driver.findElement(By.id("statusPedido3")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement dropdown = driver.findElement(By.id("statusPedido3"));
			dropdown.findElement(By.xpath("//option[. = 'EM TRANSPORTE']")).click();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("tr:nth-child(4) .Botao1")).click();
		driver.switchTo().alert().accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("statusPedido3")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		{
			WebElement dropdown = driver.findElement(By.id("statusPedido3"));
			dropdown.findElement(By.xpath("//option[. = 'ENTREGUE']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector("tr:nth-child(4) .Botao1")).click();
		driver.switchTo().alert().accept();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=20");

		driver.findElement(By.id("abaEntregue-tab")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".row:nth-child(3) .item-troca")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".row:nth-child(8) .btn:nth-child(1)")).click();
		driver.switchTo().alert().accept();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/TrocaPedidos.html");
		driver.findElement(By.cssSelector(".btn-secondary:nth-child(1)")).click();
		driver.switchTo().alert().accept();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=20");
		driver.findElement(By.id("abaDevolucao-tab")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".btn-outline-secondary")).click();
		driver.switchTo().alert().accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/TrocaPedidos.html");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("confirmarRecebimentoBtn")).click();
		driver.findElement(By.cssSelector(".modal-footer > .btn-primary")).click();
		driver.switchTo().alert().accept();

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=20");
		driver.findElement(By.id("abaDevolucao-tab")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MeusCupons.html?id=20");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
