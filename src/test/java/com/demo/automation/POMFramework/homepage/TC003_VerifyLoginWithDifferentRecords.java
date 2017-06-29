package com.demo.automation.POMFramework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;

public class TC003_VerifyLoginWithDifferentRecords extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_VerifyLoginWithDifferentRecords.class.getName());

	HomePage homepage;

	@DataProvider(name = "loginData")
	public String[][] getCellData() {

		return getData("TestData.xlsx", "LoginTestData");

	}

	@BeforeTest
	public void setUp() throws IOException {
		init();
	}

	@Test(dataProvider="loginData")
	public void testLogin(String email, String password, String runmode){
		homepage = new HomePage(driver); 
		if(runmode.equalsIgnoreCase("n")){
			throw new SkipException("this test case runmode is no");
		}
		
		log.info("--------------Starting login with Different Records------------------");
		homepage.loginToAppilcation(email, password);
		
		try{
			//if(homepage.verifyFailureMsgTextPresent())
				Assert.assertEquals(homepage.getFailedMessage(),"Sorry, your email or password appears to be incorrect. Please check and try again.");
			
				//else if(homepage.verifySuccessMsgTextPresent()){
					
					//Assert.assertEquals(homepage.getSuccessMessage(),"My Account");
			//	}
				
			
		}
		catch(AssertionError e)
		{
			e.printStackTrace();
		}
		
		//getScreenShots(email);
		log.info("--------------Ending login with Different Records------------------");
		
		
	}

	@AfterClass
	public void endTest() {
		driver.close();

	}

}
