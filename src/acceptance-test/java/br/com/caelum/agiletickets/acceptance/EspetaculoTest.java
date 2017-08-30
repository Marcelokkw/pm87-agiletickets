package br.com.caelum.agiletickets.acceptance;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementValue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EspetaculoTest {
	
	private static FirefoxDriver driver;
	
	@BeforeClass
	public static void antesDaClasse() {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
	}
	
	@Before
	public void antesDeCadaTeste() {
		driver.get("http://localhost:8080/espetaculos");
	}
	
	@AfterClass
	public static void depoisDeCadaTeste() {
		driver.close();
	}
	
	@Test
	public void cadastroEspetaculo() throws Exception {

		WebElement form = driver.findElement(By.id("addForm"));
		form.findElement(By.name("espetaculo.tipo")).sendKeys("Teatro");
		form.findElement(By.name("espetaculo.estabelecimento.id")).sendKeys("Casa de shows");
		form.submit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.not(textToBePresentInElementValue(By.name("espetaculo.tipo"), "TEATRO")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("errors")));
		
		WebElement erro = driver.findElement(By.id("errors"));		
		List<WebElement> erros = erro.findElements(By.cssSelector("li"));
		Assert.assertEquals("Nome do espetáculo não pode estar em branco", erros.get(0).getText());
		Assert.assertEquals("Descrição do espetáculo não pode estar em branco", erros.get(1).getText());
		
//		WebElement linha = driver.findElement(By.cssSelector("table tbody tr:last-child"));
//		List<WebElement> colunas = linha.findElements(By.cssSelector("td"));
//		Assert.assertEquals("TEATRO", colunas.get(3).getText());
		

		
//		EntityManagerFactory efm = Persistence.createEntityManagerFactory("default");
//		EntityManager manager = efm.createEntityManager();
//		manager.getTransaction().begin();
//		manager.createNativeQuery("delete from Espetaculo where nome = 'Rei Leão'").executeUpdate();
//		manager.getTransaction().commit();		
	}

}
