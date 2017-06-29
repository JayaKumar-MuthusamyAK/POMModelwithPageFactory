package com.demo.automation.POMFramework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;

public class TC002_VerifyNewUserRegistration extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC002_VerifyNewUserRegistration.class.getName());
	
	//WebDriver driver;
	
	HomePage homepage;
	@BeforeTest
	public void setup() throws IOException{
		
		init();
	}
	
	@Test
	public void verifyNewUserRegistration(){
		
		log.info("--------------Starting Sign up page test-------------------- ");
		homepage = new HomePage(driver);
		homepage.new_userLogin("Jakay", "K", "984898958", "jakay555@gmail.com", "12345678", "12345678");
		
		Assert.assertTrue(homepage.userSignupSuccessMessage("Jakay", "K"), "Thank you for registering with CeX");
		log.info("--------------Ending Sign up page test-------------------- ");
		
	}
	
	@AfterClass
	public void endSetUp(){
		driver.quit();
	}

}
