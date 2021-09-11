package com.googlebooksapi.googlebooksapi;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;





public class ListarLivros {
	
	@Test
	public void testeParaAbrirONavegadorEIrParaAPaginaBuscaEsperarTresSegundosEFechar() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		browser.navigate().to("http://localhost:8080/busca");
		browser.findElement(By.id("nomeParaBusca")).sendKeys("Harry Potter");
		Thread.sleep(1000);
		browser.findElement(By.id("buscaPorNome")).click();
		Thread.sleep(3000);
		
		Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/lista?nomeLivro=Harry+Potter"));		
		
		Thread.sleep(3000);
		
//		Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/livros/listaLivros?nomeLivro=Harry+Potter"));
		browser.quit();
	}

}
