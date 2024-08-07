
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FLUXODEVENDACENARIO21 {
	private WebDriver driver;

	@Test
	public void fLUXODEVENDA() {
		// Configura o caminho do driver do Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

		// Inicializa o WebDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Navega até a página desejada
		driver.get("/les-ecommerce-vinhos/paginaInical.html");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("btn-add-carrinho-2")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.switchTo().alert().accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.linkText("MEU CARRINHO")).click();
		driver.findElement(By.id("btn-finalizar-compra")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("NovoEndereco")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("nome")).click();
		driver.findElement(By.id("nome")).sendKeys("Teste4");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector(".row:nth-child(2) > .col-md-6:nth-child(2)")).click();
		driver.findElement(By.id("typeTipoResidencia")).click();
		{
			WebElement dropdown = driver.findElement(By.id("typeTipoResidencia"));
			dropdown.findElement(By.xpath("//option[. = 'Apartamento']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("typeTipoLogradouro")).click();
		{
			WebElement dropdown = driver.findElement(By.id("typeTipoLogradouro"));
			dropdown.findElement(By.xpath("//option[. = 'Estrada']")).click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("typeLogradouro")).click();
		driver.findElement(By.id("typeLogradouro")).sendKeys("Teste");
		driver.findElement(By.id("typeNumero")).click();
		driver.findElement(By.id("typeNumero")).sendKeys("32");
		driver.findElement(By.id("typeBairro")).click();
		driver.findElement(By.id("typeBairro")).sendKeys("Vila");
		driver.findElement(By.id("typeCidade")).click();
		driver.findElement(By.id("typeCidade")).sendKeys("Mogi das Cruzes");
		driver.findElement(By.id("typeEstado")).click();
		driver.findElement(By.id("typeEstado")).sendKeys("SP");
		driver.findElement(By.id("typeCep")).click();
		driver.findElement(By.id("typeCep")).sendKeys("08559410");
		driver.findElement(By.id("typePais")).click();
		driver.findElement(By.id("typePais")).sendKeys("Brasil");
		driver.findElement(By.id("observacoes")).click();
		driver.findElement(By.id("observacoes")).sendKeys("nenhuma");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("BotaoCadastrar")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.switchTo().alert().accept();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("btn-selecionar-cupom")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("cupomTselect1")).click();
		driver.findElement(By.id("SalvarCupom")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("btn-selecionar-cupom")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector(".card:nth-child(8) #cupomTselect4")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector(".card:nth-child(7) .custom-checkbox")).click();
		driver.findElement(By.id("cupomTselect4")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("SalvarCupom")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("btn-selecionar-cartao")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector("#modalCartoes .card:nth-child(1) label")).click();
		driver.findElement(By.id("valorCartao")).click();
		driver.findElement(By.id("valorCartao")).sendKeys("75.04");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("btn-salvar-cartao")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Finaliza a compra
		driver.findElement(By.id("btn-finalizar-venda")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Fecha o navegador
		driver.quit();

	}
}
