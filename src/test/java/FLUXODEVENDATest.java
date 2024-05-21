import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Dimension;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;

public class FLUXODEVENDATest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    String driverPath = "src/main/resources/drivers/msedgedriver.exe";

    System.setProperty("webdriver.edge.driver", driverPath);
    driver = new EdgeDriver();
    driver.manage().window().maximize();
    vars = new HashMap<>();
    js = (JavascriptExecutor) driver;
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void fLUXODEVENDA() {
	    driver.get("http://localhost:8080/les-ecommerce-vinhos/");
	    driver.manage().window().setSize(new Dimension(1936, 1056));
	    driver.findElement(By.id("btn-add-carrinho-1")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    driver.switchTo().alert().accept();
	    driver.findElement(By.id("btn-add-carrinho-3")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    driver.switchTo().alert().accept();
	    driver.findElement(By.linkText("MEU CARRINHO")).click();
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".col-md-3 > .btn"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector("#modalSelecaoEndereco .card:nth-child(1) label")).click();
	    driver.findElement(By.cssSelector(".modal-md .modal-footer > .btn")).click();
	    driver.findElement(By.cssSelector(".col-md-3 > .btn")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".col-md-3 > .btn"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
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
	    driver.findElement(By.cssSelector("#modalSelecaoEndereco .card:nth-child(2) label")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    driver.findElement(By.cssSelector(".modal-md .modal-footer > .btn")).click();
  }
}
