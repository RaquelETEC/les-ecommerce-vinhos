import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

class CRUDTesteInserir {

	@Test
	public void testInsert() {
		WebDriver browser = new EdgeDriver();
		
		browser.manage().window().maximize();
		
		browser.navigate().to("http://localhost:8080/les-ecommerce-vinhos/areaAdministrador/Clientes.html");

		WebElement botaoCadastar = browser.findElement(By.id("btnCadastrar"));
		botaoCadastar.click();

		// Dados pessoais
		WebElement campoNome = browser.findElement(By.id("typeNome"));
		campoNome.sendKeys("Rodrigo Rocha");

		WebElement campoEmail = browser.findElement(By.id("typeEmail"));
		campoEmail.sendKeys("rodrigorocha@gmail.com");

		WebElement campoSenha = browser.findElement(By.id("typeSenha"));
		campoSenha.sendKeys("suaSenha123@");

		WebElement campoSenhaRepetida = browser.findElement(By.id("typeRepitaSenha"));
		campoSenhaRepetida.sendKeys("suaSenha123@");

		WebElement campoCpf = browser.findElement(By.id("typeCPF"));
		campoCpf.sendKeys("12345678900");

		WebElement campoTipoTelefone = browser.findElement(By.id("TypeTipoTelefone"));
		Select telefone = new Select(campoTipoTelefone);
		telefone.selectByValue("celular");

		WebElement campoTelefone = browser.findElement(By.id("typeNumeroTelefone"));
		campoTelefone.sendKeys("9345678900");

		WebElement campoDtNasc = browser.findElement(By.id("typeNascimento"));
		campoDtNasc.sendKeys("01011990");

		WebElement campoGenero = browser.findElement(By.id("typeGenero"));
		Select genero = new Select(campoGenero);
		genero.selectByValue("Masculino");
		
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

		WebElement campoDest = browser.findElement(By.id("TypeNomeEnd"));
		campoDest.sendKeys("Fatec");

		WebElement campoObs = browser.findElement(By.id("observacoes"));
		campoObs.sendKeys("Nenhuma");

		// Endere�o Entrega
		WebElement campoTipoResidenciaE = browser.findElement(By.id("typeTipoResidenciaE"));
		Select residenciaE = new Select(campoTipoResidenciaE);
		residenciaE.selectByValue("apartamento");

		WebElement campoTipoLogradouroE = browser.findElement(By.id("typeTipoLogradouroE"));
		Select logradouroE = new Select(campoTipoLogradouroE);
		logradouroE.selectByValue("Estrada");

		WebElement campoLogradouroE = browser.findElement(By.id("typeLogradouroE"));
		campoLogradouroE.sendKeys("Rua Carlos Barattino");

		WebElement campoNumeroE = browser.findElement(By.id("typeNumeroE"));
		campoNumeroE.sendKeys("908");

		WebElement campoBairroE = browser.findElement(By.id("typeBairroE"));
		campoBairroE.sendKeys("Vila Nova Mogilar");

		WebElement campoCidadeE = browser.findElement(By.id("typeCidadeE"));
		campoCidadeE.sendKeys("Mogi das Cruzes");

		WebElement campoEstadoE = browser.findElement(By.id("typeEstadoE"));
		campoEstadoE.sendKeys("Sao Paulo");

		WebElement campoCEPE = browser.findElement(By.id("typeCepE"));
		campoCEPE.sendKeys("08773-600");

		WebElement campoPaisE = browser.findElement(By.id("typePaisE"));
		campoPaisE.sendKeys("Brasil");

		WebElement campoDestE = browser.findElement(By.id("TypeNomeEndE"));
		campoDestE.sendKeys("Fatec");

		WebElement campoObsE = browser.findElement(By.id("observacoesE"));
		campoObsE.sendKeys("Nenhuma");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Endere�o Cobran�a
		WebElement campoTipoResidenciaC = browser.findElement(By.id("typeTipoResidenciaC"));
		Select residenciaC = new Select(campoTipoResidenciaC);
		residenciaC.selectByValue("condominio");

		WebElement campoTipoLogradouroC = browser.findElement(By.id("typeTipoLogradouroC"));
		Select logradouroC = new Select(campoTipoLogradouroC);
		logradouroC.selectByValue("Avenida");

		WebElement campoLogradouroC = browser.findElement(By.id("typeLogradouroC"));
		campoLogradouroC.sendKeys("Rua Carlos Barattino");

		WebElement campoNumeroC = browser.findElement(By.id("typeNumeroC"));
		campoNumeroC.sendKeys("908");

		WebElement campoBairroC = browser.findElement(By.id("typeBairroC"));
		campoBairroC.sendKeys("Vila Nova Mogilar");

		WebElement campoCidadeC = browser.findElement(By.id("typeCidadeC"));
		campoCidadeC.sendKeys("Mogi das Cruzes");

		WebElement campoEstadoC = browser.findElement(By.id("typeEstadoC"));
		campoEstadoC.sendKeys("Sao Paulo");

		WebElement campoCEPC = browser.findElement(By.id("typeCepC"));
		campoCEPC.sendKeys("08773-600");

		WebElement campoPaisC = browser.findElement(By.id("typePaisC"));
		campoPaisC.sendKeys("Brasil");

		WebElement campoDestC = browser.findElement(By.id("TypeNomeEndC"));
		campoDestC.sendKeys("Fatec");

		WebElement campoObsC = browser.findElement(By.id("observacoesC"));
		campoObsC.sendKeys("Nenhuma");
		// Cartao

		WebElement campoCartaoNome = browser.findElement(By.id("CartaoNome"));
		campoCartaoNome.sendKeys("Rodrigo Rocha");

		WebElement campoCartaoBandeira = browser.findElement(By.id("tipoBandeira"));
		Select bandeira = new Select(campoCartaoBandeira);
		bandeira.selectByValue("2");

		WebElement campoCartaoNumero = browser.findElement(By.id("CartaoNumero"));
		campoCartaoNumero.sendKeys("123");

		WebElement campoCartaoCodigo = browser.findElement(By.id("CartaoCodigo"));
		campoCartaoCodigo.sendKeys("321");

		WebElement botaoEnviar = browser.findElement(By.id("BotaoCadastrar"));
		botaoEnviar.click();
		browser.quit();

	}

}
