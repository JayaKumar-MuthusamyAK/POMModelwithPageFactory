package com.demo.automation.POMFramework.homepage;

import org.apache.log4j.Logger;
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
	
	@DataProvider(name="loginData")
	public String[][] getCellData(){
		
		return getData("TestData.xlsx", "LoginTestData");
		
	}
	@BeforeTest
	public void setUp(){
		init();
	}
	
	@Test(dataProvider="loginData")
	public void testLogin(String email, String password, String runmode){
		if(runmode.equalsIgnoreCase("n")){
			throw new SkipException("this test case runmode is no");
		}
		log.info("--------------Starting login with Different Records------------------");
		homepage = new HomePage(driver);
		homepage.loginToAppilcation(email, password);
		
		log.info("--------------Ending login with Different Records------------------");
		
	}
	
	@AfterClass
	public void endTest(){
		driver.close();
		
	}

}
