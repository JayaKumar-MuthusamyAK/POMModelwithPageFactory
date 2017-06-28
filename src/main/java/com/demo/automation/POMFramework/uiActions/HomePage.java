package com.demo.automation.POMFramework.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.automation.POMFramework.testBase.TestBase;

public class HomePage {
	
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath="html/body/div[2]/div/div/div[2]/ul/li[2]/a")
	WebElement myAccount;
	
	@FindBy(xpath="html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[1]/a")
	WebElement loginLbl;
	
	@FindBy(xpath="//*[@id='loginfrm']/div[4]/div/div[1]/input")
	WebElement loginEmail;
	
	@FindBy(xpath="//*[@id='loginfrm']/div[4]/div/div[2]/input")
	WebElement loginPassword;
	
	@FindBy(xpath="//*[@id='loginfrm']/div[4]/button")
	WebElement loginBtn;
	
	@FindBy(xpath="//*[@id='loginfrm']/div[1]/div")
	WebElement authonticationFailMessage;
	
	@FindBy(xpath="html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[2]/a")
	WebElement SignUpLbl;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[3]/input")
	WebElement SignUpFirstName;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[4]/input")
	WebElement SignUpLastName;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[5]/input")
	WebElement SignUpMobile;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[6]/input")
	WebElement SignUpEmail;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[7]/input")
	WebElement SignUpPassword;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[8]/input")
	WebElement SignUpConfirmPassword;
	
	@FindBy(xpath="//*[@id='headersignupform']/div[9]/button")
	WebElement SignUpSubmitBtn;
	
	@FindBy(xpath="html/body/div[3]/div[1]/div/div[1]/h3")
	WebElement NewUserSuccessMessage;
	
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void loginToAppilcation(String email, String pwd){
		
		myAccount.click();
		log.info("Clicked on myaccount label and object is :"+myAccount.toString());
		loginLbl.click();
		log.info("Clicked on the login label and Object is :"+loginLbl.toString());
		loginEmail.sendKeys(email);
		log.info("Enter email address and Object is :"+loginEmail.toString());
		loginPassword.sendKeys(pwd);
		log.info("Enter Password  and Object is :"+loginPassword.toString());
		loginBtn.click();
		log.info("Clicked the Login button and Object is :"+loginBtn.toString());
	}
	
	public String getFailedMessage(){
		log.info("Verifing the Error message :"+authonticationFailMessage.toString());
		return authonticationFailMessage.getText();
	}
	
	public void new_userLogin(String firstname, String lastname, String mobile,String email, String password, String confirmPassword){
		myAccount.click();
		log.info("Clicked on myaccount label and object is :"+myAccount.toString());
		
		SignUpLbl.click();
		log.info("Clicked on SignUp option and object is :"+SignUpLbl.toString());
		
		SignUpFirstName.clear();
		SignUpFirstName.sendKeys(firstname);
		log.info("Entered the First Name in sign up form and object is :"+SignUpFirstName.toString());
		
		SignUpLastName.clear();
		SignUpLastName.sendKeys(lastname);
		log.info("Entered the Last Name in sign up form and object is :"+SignUpLastName.toString());
		
		SignUpMobile.clear();
		SignUpMobile.sendKeys(mobile);
		log.info("Entered the mobile number in sign up form and object is  :"+SignUpMobile.toString());
		
		SignUpEmail.clear();
		SignUpEmail.sendKeys(email);
		log.info("Entered the Email address in sign up form and object is  :"+SignUpEmail.toString());
		
		SignUpPassword.clear();
		SignUpPassword.sendKeys(password);
		log.info("Entered the Password in sign up form and object is  :"+SignUpPassword.toString());
		
		SignUpConfirmPassword.clear();
		SignUpConfirmPassword.sendKeys(confirmPassword);
		log.info("Entered the Confirm Password in sign up form and object is  :"+SignUpConfirmPassword.toString());
		
		SignUpSubmitBtn.click();
		log.info("Clicked on SignUp button and object is :"+SignUpSubmitBtn.toString());
		
		
	}
	
	public boolean userSignupSuccessMessage(String firstname,String lastname){
		
		return driver.findElement(By.xpath("//*[contains(text(),'"+firstname+" "+lastname+"')]")).isDisplayed();
		
	}
}
