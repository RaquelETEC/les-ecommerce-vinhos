import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

class CRUDTesteENDAlterar {

	WebDriver browser = new EdgeDriver();
	
	@Test
	public void AlterarExcluir() {
		browser.manage().window().maximize();
	
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");
		
		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));
		

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);
		
		
		browser.findElement(By.className("button-dados-perfil")).click();
		browser.findElement(By.className("enderecos")).click();
		
		//Entrega vira cobrança
		browser.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement campoDest = browser.findElement(By.id("nome"));
		campoDest.clear();
		campoDest.sendKeys("Fatec Cobrança Alterar Teste");
		
		WebElement campoTipoLogradouro = browser.findElement(By.id("typeTipoLogradouro"));
		Select logradouro = new Select(campoTipoLogradouro);
		logradouro.selectByValue("Estrada");
	    

		WebElement campoObs = browser.findElement(By.id("observacoes"));
		campoObs.clear();
		campoObs.sendKeys("Cobrança");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement campoTipo = browser.findElement(By.id("COBRANCA"));
		campoTipo.click();

		browser.findElement(By.id("BotaoCadastrar")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement botaoExcluir = browser.findElement(By.id("BotaoExcluir"));
		botaoExcluir.click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    browser.switchTo().alert().accept();
	    
		
		//Cobrança vira Residencial
		browser.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]")).click();
		
		WebElement campoDest2 = browser.findElement(By.id("nome"));
		campoDest2.clear();
		campoDest2.sendKeys("Fatec Residencial Alterar Teste");
		
		WebElement campoTipoLogradouro2 = browser.findElement(By.id("typeTipoLogradouro"));
		Select logradouro2 = new Select(campoTipoLogradouro2);
		logradouro2.selectByValue("Alameda");
	    
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement campoObs2 = browser.findElement(By.id("observacoes"));
		campoObs2.clear();
		campoObs2.sendKeys("RESIDENCIAL");
		
		WebElement campoTipo2 = browser.findElement(By.id("RESIDENCIAL"));
		campoTipo2.click();

	
		WebElement botaoCadastrar = browser.findElement(By.id("BotaoCadastrar"));		
		botaoCadastrar.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement botaoExcluir2 = browser.findElement(By.id("BotaoExcluir"));
		botaoExcluir2.click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    browser.switchTo().alert().accept();
		
		
		//Residencial vira Entrega
		
		browser.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
		
		WebElement campoDest3 = browser.findElement(By.id("nome"));
		campoDest3.clear();
		campoDest3.sendKeys("Fatec entrega Alterar Teste");
		
		WebElement campoTipoLogradouro3 = browser.findElement(By.id("typeTipoLogradouro"));
		Select logradouro3 = new Select(campoTipoLogradouro3);
		logradouro3.selectByValue("Distrito");
	    

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement campoObs3 = browser.findElement(By.id("observacoes"));
		campoObs3.clear();
		campoObs3.sendKeys("ENTREGA");
		
		WebElement campoTipo3 = browser.findElement(By.id("ENTREGA"));
		campoTipo3.click();
		browser.findElement(By.id("BotaoCadastrar")).click();

		browser.findElement(By.xpath("(//a[@class='btn btn-danger'])[1]")).click();
	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		browser.switchTo().alert().accept();


	}

}
