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

public class FLUXODEVENDACENARIO12 {
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
        driver.get("http://localhost:8080/les-ecommerce-vinhos/paginaInical.html");
        
        Actions builder = new Actions(driver);
        
        // Realiza a ação de mover o mouse para o botão
        WebElement btnSelecionarItem = driver.findElement(By.id("btn-add-carrinho-5"));
        builder.moveToElement(btnSelecionarItem).perform();

        // Adiciona item ao carrinho
        driver.findElement(By.id("btn-add-carrinho-5")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Aceita o alerta
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Acessa o carrinho
        driver.findElement(By.linkText("MEU CARRINHO")).click();
        driver.findElement(By.id("btn-finalizar-compra")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Seleciona endereço
        driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("NovoEndereco")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Preenche o formulário de endereço
        driver.findElement(By.id("nome")).sendKeys("Teste3");
        driver.findElement(By.id("typeTipoResidencia")).sendKeys("Casa");
        driver.findElement(By.id("typeTipoLogradouro")).sendKeys("Rua");
        driver.findElement(By.id("typeLogradouro")).sendKeys("Teste");
        driver.findElement(By.id("typeNumero")).sendKeys("32");
        driver.findElement(By.id("typeBairro")).sendKeys("Vila");
        driver.findElement(By.id("typeCidade")).sendKeys("Poa");
        driver.findElement(By.id("typeEstado")).sendKeys("SP");
        driver.findElement(By.id("typeCep")).sendKeys("08559410");
        driver.findElement(By.id("typePais")).sendKeys("Brasil");
        driver.findElement(By.id("observacoes")).sendKeys("nenhuma");        
		driver.findElement(By.id("BotaoCadastrar")).click();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Aceita o alerta
        driver.switchTo().alert().accept();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Realiza a ação de mover o mouse para o botãos
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
        driver.findElement(By.id("valorCartao")).sendKeys("200");
        driver.findElement(By.id("btn-salvar-cartao")).click();

        try {
            Thread.sleep(0100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        

        driver.findElement(By.id("btn-selecionar-cartao")).click();
        
        try {
            Thread.sleep(0100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Move o mouse novamente para o botão e, em seguida, de volta ao corpo da página
        builder.moveToElement(btnSelecionarCartao).perform();

        // Seleciona o cartão e insere o valor
        driver.findElement(By.cssSelector("#modalCartoes .card:nth-child(1) label")).click();
        driver.findElement(By.id("valorCartao")).click();
        driver.findElement(By.id("valorCartao")).sendKeys("70.32");
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
