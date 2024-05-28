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
import org.openqa.selenium.Alert;
import java.util.concurrent.TimeUnit;

public class FLUXODEVENDACENARIO2 {

	@Test
	public void fLUXODEVENDA() {
		  // Configura o caminho do driver do Chrome
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        // Inicializa o WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navega até a página desejada
        driver.get("http://localhost:8080/les-ecommerce-vinhos/paginaInical.html");
    
		

		
		driver.findElement(By.id("btn-add-carrinho-3")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();

        alert.accept();
        driver.findElement(By.linkText("MEU CARRINHO")).click();
        driver.findElement(By.id("btn-finalizar-compra")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //SELECIONAR UM ENDEREÇO JA CADASTRADO 
        driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
        driver.findElement(By.cssSelector("#modalSelecaoEndereco .card:nth-child(1) label")).click();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clica no botão para salvar o endereço
        driver.findElement(By.id("btn-salvar-endereco")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
        //PAREI AQUI !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        
		driver.findElement(By.id("btn-selecionar-cupom")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.findElement(By.id("cupomTselect1")).click();
		
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
		driver.findElement(By.id("SelecionarPagamento")).click();
		{
			WebElement element = driver.findElement(By.id("SelecionarPagamento"));
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
		driver.findElement(By.id("valorCartao")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("valorCartao")).sendKeys("33.93");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("#modalCartoes .card:nth-child(1) label")).click();
		driver.findElement(By.cssSelector(".col-6 > .btn-primary")).click();
		
		driver.findElement(By.id("FinalizarCompra")).click();

	}
}
