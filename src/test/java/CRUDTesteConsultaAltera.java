import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CRUDTesteConsultaAltera {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Obtém o caminho para o diretório do WebDriver dentro do seu projeto
    	String driverPath = "src/main/resources/drivers/msedgedriver.exe";

        // Configura o caminho para o WebDriver
        System.setProperty("webdriver.edge.driver", driverPath);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
	@Test
	public void testEndInserir() {
		
		WebDriver browser = new EdgeDriver();
		browser.manage().window().maximize();

		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");
		
		WebElement campoNome = browser.findElement(By.id("nome"));
		campoNome.sendKeys("Rodrigo Rocha");
			
	    
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement botaoConsultar = browser.findElement(By.id("btnBuscar"));
		botaoConsultar.click();
		
	    
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));
		
		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);

		// Dados pessoais
		WebElement campoNome2 = browser.findElement(By.id("typeNome"));
		campoNome2.clear();
		campoNome2.sendKeys("Rodrigo Rocha Silva");
		
	    
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement campoEmail = browser.findElement(By.id("typeEmail"));
		campoEmail.clear();
		campoEmail.sendKeys("rodrigorochaSilva@gmail.com");
		
	    
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement botaoAtualizar = browser.findElement(By.className("button-salvar"));
		botaoAtualizar.click();
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

	}

}
