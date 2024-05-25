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
import org.openqa.selenium.Dimension;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;

public class FLUXODEVENDACancela {
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
		driver.get("http://localhost:8080/les-ecommerce-vinhos/");
		driver.manage().window().setSize(new Dimension(1936, 1056));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("btn-incrementar-5")).click();
		driver.findElement(By.id("btn-add-carrinho-5")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
		driver.findElement(By.linkText("MEU CARRINHO")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
		driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".card:nth-child(2) .col-md-2 label")).click();
		driver.findElement(By.id("SalvarEndereco")).click();
		driver.findElement(By.id("SelecionarCupons")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement element = driver.findElement(By.id("SelecionarCupons"));
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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("cupomTselect19")).click();
		driver.findElement(By.cssSelector("#modalCupomT .btn")).click();
		{
			WebElement element = driver.findElement(By.cssSelector("#modalCupomT .btn"));
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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".col-2 > .text-center"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).clickAndHold().perform();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".col-2 > .text-center"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".col-2 > .text-center"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).release().perform();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".mt-4")).click();
		driver.findElement(By.cssSelector(".col-md-5 > .btn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("#modalCartoes .card label")).click();
		driver.findElement(By.id("valorCartao")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("valorCartao")).sendKeys("441.58");
		driver.findElement(By.cssSelector(".col-6 > .btn-primary")).click();
		try {
			Thread.sleep(2000);
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html");

		driver.findElement(By.id("statusPedido4")).click();
		{
			WebElement dropdown = driver.findElement(By.id("statusPedido4"));
			dropdown.findElement(By.xpath("//option[. = 'PAGAMENTO REALIZADO']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("tr:nth-child(5) .Botao1")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.id("statusPedido4")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement dropdown = driver.findElement(By.id("statusPedido4"));
			dropdown.findElement(By.xpath("//option[. = 'EM TRANSPORTE']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("tr:nth-child(5) .Botao1")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.id("statusPedido4")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		{
			WebElement dropdown = driver.findElement(By.id("statusPedido4"));
			dropdown.findElement(By.xpath("//option[. = 'ENTREGUE']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("tr:nth-child(5) .Botao1")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.id("statusPedido4")).click();

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=20");

		driver.findElement(By.id("abaEntregue-tab")).click();
		driver.findElement(By.cssSelector(".row:nth-child(6) .btn:nth-child(2)")).click();
		driver.switchTo().alert().accept();
		driver.switchTo().alert().accept();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html");
		driver.findElement(By.id("statusPedido4")).click();
		{
			WebElement dropdown = driver.findElement(By.id("statusPedido4"));
			dropdown.findElement(By.xpath("//option[. = 'RECEBER PRODUTOS']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("tr:nth-child(5) .Botao1")).click();
		driver.switchTo().alert().accept();
		driver.switchTo().alert().accept();
		driver.findElement(By.id("statusPedido4")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=20");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".nav-item:nth-child(6) > #abaDevolucao-tab")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaCliente/MeusCupons.html?id=20");
	}
}
