import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class CRUDTesteCartaoInserir {
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
		
		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));
		

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);
		
		
		browser.findElement(By.className("button-dados-perfil")).click();
		browser.findElement(By.className("cartoes")).click();
		browser.findElement(By.className("botaoADDNovoCartao")).click();
		
		// Cartao

		WebElement campoCartaoNome = browser.findElement(By.id("CartaoNome"));
		campoCartaoNome.sendKeys("Teste");
		
		WebElement campoCartaoBandeira = browser.findElement(By.id("tipoBandeira"));
		Select bandeira = new Select(campoCartaoBandeira);
		bandeira.selectByValue("1");
		
		WebElement campoCartaoNumero = browser.findElement(By.id("CartaoNumero"));
		campoCartaoNumero.sendKeys("213");
		
		WebElement campoCartaoCodigo = browser.findElement(By.id("CartaoCodigo"));
		campoCartaoCodigo.sendKeys("432");
		
		WebElement campoPadrao = browser.findElement(By.id("CartaoPadrao"));
		Select padrao = new Select(campoPadrao);
		padrao.selectByValue("SIM");
		
		 WebElement botaoEnviar = browser.findElement(By.id("BotaoCadastrar"));
		 botaoEnviar.click();

		
	}

}
