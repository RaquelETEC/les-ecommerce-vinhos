import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import java.util.concurrent.TimeUnit;

public class FLUXODEVENDACENARIO1 {

    @Test
    public void cenario1() {
        // Configura o caminho do driver do Chrome
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        // Inicializa o WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navega até a página desejada
        driver.get("http://localhost:8080/les-ecommerce-vinhos/paginaInical.html");
        driver.findElement(By.id("btn-add-carrinho-1")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Aceita o alerta
        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.findElement(By.linkText("MEU CARRINHO")).click();
        driver.findElement(By.id("btn-finalizar-compra")).click();
        
        driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
        driver.findElement(By.cssSelector("#modalSelecaoEndereco .card:nth-child(3) label")).click();

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

        // Realiza a ação de mover o mouse para o botão
        Actions builder = new Actions(driver);
        WebElement btnSelecionarCartao = driver.findElement(By.id("btn-selecionar-cartao"));
        builder.moveToElement(btnSelecionarCartao).perform();

        driver.findElement(By.id("btn-selecionar-cartao")).click();
        
        try {
            Thread.sleep(0100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Move o mouse de volta para o corpo da página
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        builder.moveToElement(bodyElement, 0, 0).perform();

        // Seleciona o cartão e insere o valor
        driver.findElement(By.cssSelector("#modalCartoes .card:nth-child(1) label")).click();
        driver.findElement(By.id("valorCartao")).click();
        driver.findElement(By.id("valorCartao")).sendKeys("93.5");
        driver.findElement(By.id("btn-salvar-cartao")).click();

        try {
            Thread.sleep(0100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

        // Move o mouse novamente para o botão e, em seguida, de volta ao corpo da página
        builder.moveToElement(btnSelecionarCartao).perform();

        // Realiza a ação de mover o mouse para o botão
        WebElement btnSelecionarFinalizarVenda = driver.findElement(By.id("btn-finalizar-venda"));
        builder.moveToElement(btnSelecionarFinalizarVenda).perform();

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
