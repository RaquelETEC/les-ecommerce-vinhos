import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.Dimension;

public class Teste {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Obtém o caminho para o diretório do WebDriver dentro do seu projeto
    	String driverPath = "src/main/resources/drivers/msedgedriver.exe";

        // Configura o caminho para o WebDriver
        System.setProperty("webdriver.edge.driver", driverPath);

        // Inicializa o WebDriver
        driver = new EdgeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void mOCKTELAS() {
        driver.get("http://localhost:8080/les-ecommerce-vinhos/");
        driver.manage().window().setSize(new Dimension(1552, 840));
        driver.findElement(By.cssSelector("form .card-body > .btn")).click();
        driver.findElement(By.cssSelector("a > button")).click();
        driver.findElement(By.cssSelector(".Trocar:nth-child(5)")).click();
        driver.findElement(By.id("radio")).click();
        driver.findElement(By.cssSelector(".Endereco-Container:nth-child(3) > #radio")).click();
        driver.findElement(By.cssSelector(".botaoAddEnd")).click();
        driver.findElement(By.cssSelector(".button-salvar")).click();
        driver.findElement(By.cssSelector(".Cupom-Container:nth-child(5) > .Selecionar")).click();
        driver.findElement(By.id("radio")).click();
        driver.findElement(By.cssSelector(".Endereco-Container:nth-child(3) > #radio")).click();
        driver.findElement(By.cssSelector(".botaoConfirmar")).click();
        driver.findElement(By.cssSelector(".Cupom-Container:nth-child(6) > .Selecionar")).click();
        driver.findElement(By.cssSelector(".Endereco-Container:nth-child(3) > #radio")).click();
        driver.findElement(By.id("radio")).click();
        driver.findElement(By.cssSelector(".botaoConfirmar")).click();
        driver.findElement(By.cssSelector(".Trocar:nth-child(9)")).click();
        driver.findElement(By.cssSelector(".FazerPedido")).click();
        driver.findElement(By.cssSelector(".BotaoMinhasCompras")).click();
        driver.findElement(By.cssSelector(".span-nav:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".span-nav:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".vinhos-container:nth-child(3) > .botaoTroca")).click();
        driver.findElement(By.cssSelector(".span-nav:nth-child(5)")).click();
        driver.findElement(By.cssSelector(".botaoTroca")).click();
        driver.findElement(By.cssSelector(".button-dados-perfil:nth-child(4) > span")).click();
        driver.findElement(By.cssSelector(".button-dados-perfil:nth-child(3) > span")).click();
    }
}
