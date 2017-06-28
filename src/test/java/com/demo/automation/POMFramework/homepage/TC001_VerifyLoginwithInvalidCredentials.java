package com.demo.automation.POMFramework.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;

public class TC001_VerifyLoginwithInvalidCredentials extends TestBase {
	
	
	HomePage homepage;
	
	@BeforeTest
	public void setup(){
		   init();
	}
	

	@Test
	public void verifyLoginwithInvalidCredentials() throws InterruptedException{
		/*
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='loginfrm']/div[4]/div/div[1]/input")).sendKeys("test@gmail.com");
		driver.findElement(By.xpath("//*[@id='loginfrm']/div[4]/div/div[2]/input")).sendKeys("1234567898");
		driver.findElement(By.xpath("//*[@id='loginfrm']/div[4]/button")).click();
		*/
		
		log.info("----------------------Starting Verifing the Login page----------------------------");
		homepage = new HomePage(driver);
		
		homepage.loginToAppilcation("test@gmail.com", "1234567");
		Thread.sleep(3000);
		Assert.assertEquals(homepage.getFailedMessage(), "Invalid Email or Password");
		log.info("----------------------Ending the Login page----------------------------");
	}
	
	@AfterClass
	public void endTest(){
		
		driver.quit();
		
	}
}
