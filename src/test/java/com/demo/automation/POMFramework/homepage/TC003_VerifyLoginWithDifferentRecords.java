package com.demo.automation.POMFramework.homepage;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.excelReader.ExcelReader;
import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;

public class TC003_VerifyLoginWithDifferentRecords extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_VerifyLoginWithDifferentRecords.class.getName());

	HomePage homepage;
	String excelFileName ="TestData.xlsx";
	String excelSheetName = "LoginTestData";
	Actions act = null;
	

	@BeforeTest
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
		
		homepage.waitForTextPresent(driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/div[2]/div/div/p")), "The username or password you entered is incorrect");
		if(homepage.getFailedMessage().equals("The username or password you entered is incorrect")){
			
			Assert.assertEquals(homepage.getFailedMessage(),"The username or password you entered is incorrect");
		}
		else if(homepage.errorMessage().equals("Please enter a valid email id")){
			Assert.assertEquals(homepage.errorMessage(), "Please enter a valid email id");
		}
		else if(homepage.errorMessage().equals("Password must be at least 6 characters")){
			Assert.assertEquals(homepage.errorMessage(), "Password must be at least 6 characters");
		}
		else if(homepage.checkLogOutbtn()){
			Assert.assertEquals(homepage.getSuccessMessage(),email);
			homepage.clickOnLogOutbtn();
		}
		
		//getScreenShots(email);
		log("--------------Ending login with Different Records------------------");
		
		
	}

	@DataProvider(name = "loginData")
	public String[][] getCellData() {

		return getData(excelFileName, excelSheetName);

	}
	

	
	public void log(String data){
		
		log.info(data);
		Reporter.log(data);
	}

}
