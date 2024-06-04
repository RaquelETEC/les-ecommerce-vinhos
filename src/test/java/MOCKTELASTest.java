import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class MOCKTELASTest {
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
    driver.manage().window().maximize();

    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector("form .card-body > .btn")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector("a > button")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    driver.findElement(By.cssSelector(".Trocar:nth-child(5)")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
    driver.findElement(By.id("radio")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

	
    driver.findElement(By.cssSelector(".Endereco-Container:nth-child(3) > #radio")).click();
    driver.findElement(By.cssSelector(".botaoAddEnd")).click();
    driver.findElement(By.cssSelector(".button-salvar")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector(".Cupom-Container:nth-child(5) > .Selecionar")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.id("radio")).click();
    driver.findElement(By.cssSelector(".Endereco-Container:nth-child(3) > #radio")).click();
    driver.findElement(By.cssSelector(".botaoConfirmar")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector(".Cupom-Container:nth-child(6) > .Selecionar")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector(".Endereco-Container:nth-child(3) > #radio")).click();
    driver.findElement(By.id("radio")).click();
    driver.findElement(By.cssSelector(".botaoConfirmar")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    
    driver.findElement(By.className("FazerPedido")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
    driver.findElement(By.className("BotaoMinhasCompras")).click();

	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector(".span-nav:nth-child(2)")).click();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    driver.findElement(By.cssSelector(".span-nav:nth-child(3)")).click();
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    driver.findElement(By.cssSelector(".vinhos-container:nth-child(3) > .botaoTroca")).click();
    
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

    driver.findElement(By.cssSelector(".span-nav:nth-child(5)")).click();
    driver.findElement(By.cssSelector(".botaoTroca")).click();
    
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    
    driver.findElement(By.cssSelector(".button-dados-perfil:nth-child(4) > span")).click();
    
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    driver.findElement(By.cssSelector(".button-dados-perfil:nth-child(3) > span")).click();
  }
}
