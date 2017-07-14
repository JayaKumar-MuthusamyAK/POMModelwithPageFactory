package com.demo.automation.POMFramework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
		homepage.new_userLogin("jakay@gmail.com","8565146556","12345678","Male");
		
		if(homepage.verifyEmailAlreadyExistMsgTextPresent()){
			Assert.assertEquals(homepage.getFailedMessage(), "The email you entered already exists");
		}
		else if(homepage.checkLogOutbtn()){
		Assert.assertEquals(homepage.userSignupSuccessMessage(), "jakay@gmail.com", "New User logged in SucessFully");
		}
		log.info("--------------Ending Sign up page test-------------------- ");
		
	}
	
	@AfterClass
	public void endSetUp(){
		//driver.quit();
	}

}
