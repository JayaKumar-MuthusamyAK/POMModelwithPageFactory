package com.demo.automation.POMFramework.homepage;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;

public class TC003_VerifyLoginWithDifferentRecords extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_VerifyLoginWithDifferentRecords.class.getName());

	HomePage homepage;
	String excelFileName ="TestData.xlsx";
	String excelSheetName = "LoginTestData";

	@DataProvider(name = "loginData")
	public String[][] getCellData() {

		return getData(excelFileName, excelSheetName);

	}

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test(dataProvider="loginData")
	public void VerifyLoginWithDifferentRecords(String email, String password, String runmode) throws InterruptedException{
		homepage = new HomePage(driver); 
		if(runmode.equalsIgnoreCase("n")){
			throw new SkipException("this test case runmode is no");
		}
		
		log("--------------Starting login with Different Records------------------");
		//wait for the search box element present
		waitForElement(30, homepage.waitForElementPresent());
		//After element that login page testing start.
		homepage.loginToAppilcation(email, password);
	
		if(homepage.verifyLoginErrorMessage())
			Assert.assertEquals(homepage.getFailedMessage(),"The username or password you entered is incorrect");
		else if(homepage.checkLogOutbtn()){
			Assert.assertEquals(homepage.getSuccessMessage(),email);
			homepage.clickOnLogOutbtn();
		}
		
		//getScreenShots(email);
		log("--------------Ending login with Different Records------------------");
		
		
	}

	@AfterClass
	public void endTest() {
		driver.close();

	}
	
	public void log(String data){
		
		log.info(data);
		Reporter.log(data);
	}

}
