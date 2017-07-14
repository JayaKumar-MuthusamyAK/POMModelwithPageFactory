package com.demo.automation.POMFramework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;

public class TC001_VerifyLoginwithInvalidCredentials extends TestBase {

	HomePage homepage;
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginwithInvalidCredentials.class.getName());
	Actions act = null;
	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	@Test
	public void verifyLoginwithInvalidCredentials() throws InterruptedException {
		/*
		 * driver.findElement(By.xpath(
		 * "html/body/div[2]/div/div/div[2]/ul/li[2]/a")).click();
		 * driver.findElement(By.xpath(
		 * "html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[1]/a")).click();
		 * driver.findElement(By.xpath(
		 * "//*[@id='loginfrm']/div[4]/div/div[1]/input")).sendKeys(
		 * "test@gmail.com"); driver.findElement(By.xpath(
		 * "//*[@id='loginfrm']/div[4]/div/div[2]/input")).sendKeys("1234567898"
		 * ); driver.findElement(By.xpath("//*[@id='loginfrm']/div[4]/button")).
		 * click();
		 */

		log("----------------------Starting Verifing the Login page----------------------------");
		homepage = new HomePage(driver);

		homepage.loginToAppilcation("test@gmail.com", "1234567");
		Thread.sleep(3000);
		Assert.assertEquals(homepage.getFailedMessage(), prop.getProperty("invalidLoginMsg"));
		log("----------------------Ending the Login page----------------------------");
	}

	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}
}
