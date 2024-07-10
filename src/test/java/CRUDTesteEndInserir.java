import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import util.Config;

public class CRUDTesteEndInserir {
	private WebDriver driver;

	@Before
	public void setUp() {
		// Obtém o caminho para o diretório do WebDriver dentro do seu projeto
		String driverPath = "src/main/resources/drivers/msedgedriver.exe";

		// Configura o caminho para o WebDriver
		System.setProperty("webdriver.edge.driver", driverPath);

	}

	@Test
	public void testEndInserir() {

		WebDriver browser = new EdgeDriver();
		browser.manage().window().maximize();

		browser.navigate().to(Config.baseUrl + "/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

		WebElement botaoEditar = browser.findElement(By.id("BotaoEditar"));

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("arguments[0].click();", botaoEditar);

		browser.findElement(By.className("button-dados-perfil")).click();
		browser.findElement(By.className("enderecos")).click();
		browser.findElement(By.className("botaoADDNovoEndereco")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Endere�o Residencial
		WebElement campoTipoResidencia = browser.findElement(By.id("typeTipoResidencia"));
		Select residencia = new Select(campoTipoResidencia);
		residencia.selectByValue("casa");

		WebElement campoTipoLogradouro = browser.findElement(By.id("typeTipoLogradouro"));
		Select logradouro = new Select(campoTipoLogradouro);
		logradouro.selectByValue("Rua");

		WebElement campoLogradouro = browser.findElement(By.id("typeLogradouro"));
		campoLogradouro.sendKeys("Rua Carlos Barattino");

		WebElement campoNumero = browser.findElement(By.id("typeNumero"));
		campoNumero.sendKeys("908");

		WebElement campoBairro = browser.findElement(By.id("typeBairro"));
		campoBairro.sendKeys("Vila Nova Mogilar");

		WebElement campoCidade = browser.findElement(By.id("typeCidade"));
		campoCidade.sendKeys("Mogi das Cruzes");

		WebElement campoEstado = browser.findElement(By.id("typeEstado"));
		campoEstado.sendKeys("Sao Paulo");

		WebElement campoCEP = browser.findElement(By.id("typeCep"));
		campoCEP.sendKeys("08773-600");

		WebElement campoPais = browser.findElement(By.id("typePais"));
		campoPais.sendKeys("Brasil");

		WebElement campoDest = browser.findElement(By.id("nome"));
		campoDest.sendKeys("Fatec RESIDENCIAL");

		WebElement campoObs = browser.findElement(By.id("observacoes"));
		campoObs.sendKeys("Nenhuma");

		WebElement campoTipo = browser.findElement(By.id("RESIDENCIAL"));
		campoTipo.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		browser.findElement(By.id("BotaoCadastrar")).click();

		// Endere�o Cobran�a
		browser.findElement(By.className("botaoADDNovoEndereco")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement campoTipoResidenciaC = browser.findElement(By.id("typeTipoResidencia"));
		Select residenciaC = new Select(campoTipoResidenciaC);
		residenciaC.selectByValue("apartamento");

		WebElement campoTipoLogradouroC = browser.findElement(By.id("typeTipoLogradouro"));
		Select logradouroC = new Select(campoTipoLogradouroC);
		logradouroC.selectByValue("Estrada");

		WebElement campoLogradouroC = browser.findElement(By.id("typeLogradouro"));
		campoLogradouroC.sendKeys("Rua Carlos Barattino");

		WebElement campoNumeroC = browser.findElement(By.id("typeNumero"));
		campoNumeroC.sendKeys("908");

		WebElement campoBairroC = browser.findElement(By.id("typeBairro"));
		campoBairroC.sendKeys("Vila Nova Mogilar");

		WebElement campoCidadeC = browser.findElement(By.id("typeCidade"));
		campoCidadeC.sendKeys("Mogi das Cruzes");

		WebElement campoEstadoC = browser.findElement(By.id("typeEstado"));
		campoEstadoC.sendKeys("Sao Paulo");

		WebElement campoCEPC = browser.findElement(By.id("typeCep"));
		campoCEPC.sendKeys("08773-600");

		WebElement campoPaisC = browser.findElement(By.id("typePais"));
		campoPaisC.sendKeys("Brasil");

		WebElement campoDestC = browser.findElement(By.id("nome"));
		campoDestC.sendKeys("Fatec Cobran�a");

		WebElement campoObsC = browser.findElement(By.id("observacoes"));
		campoObsC.sendKeys("Nenhuma");

		WebElement campoTipoC = browser.findElement(By.id("COBRANCA"));
		campoTipoC.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		browser.findElement(By.id("BotaoCadastrar")).click();

		// Endere�o Entrega
		browser.findElement(By.className("botaoADDNovoEndereco")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement campoTipoResidenciaE = browser.findElement(By.id("typeTipoResidencia"));
		Select residenciaE = new Select(campoTipoResidenciaE);
		residenciaE.selectByValue("condominio");

		WebElement campoTipoLogradouroE = browser.findElement(By.id("typeTipoLogradouro"));
		Select logradouroE = new Select(campoTipoLogradouroE);
		logradouroE.selectByValue("Avenida");

		WebElement campoLogradouroE = browser.findElement(By.id("typeLogradouro"));
		campoLogradouroE.sendKeys("Rua Carlos Barattino");

		WebElement campoNumeroE = browser.findElement(By.id("typeNumero"));
		campoNumeroE.sendKeys("908");

		WebElement campoBairroE = browser.findElement(By.id("typeBairro"));
		campoBairroE.sendKeys("Vila Nova Mogilar");

		WebElement campoCidadeE = browser.findElement(By.id("typeCidade"));
		campoCidadeE.sendKeys("Mogi das Cruzes");

		WebElement campoEstadoE = browser.findElement(By.id("typeEstado"));
		campoEstadoE.sendKeys("Sao Paulo");

		WebElement campoCEPE = browser.findElement(By.id("typeCep"));
		campoCEPE.sendKeys("08773-600");

		WebElement campoPaisE = browser.findElement(By.id("typePais"));
		campoPaisE.sendKeys("Brasil");

		WebElement campoDestE = browser.findElement(By.id("nome"));
		campoDestE.sendKeys("Fatec ENTREGA");

		WebElement campoObsE = browser.findElement(By.id("observacoes"));
		campoObsE.sendKeys("Nenhuma");

		WebElement campoTipoE = browser.findElement(By.id("ENTREGA"));
		campoTipoE.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		browser.findElement(By.id("BotaoCadastrar")).click();
	}

}
