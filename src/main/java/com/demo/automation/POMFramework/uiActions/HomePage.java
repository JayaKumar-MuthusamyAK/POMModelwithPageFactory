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

	@FindBy(xpath = "//*[@id='signIn']")
	WebElement loginLbl;

	@FindBy(xpath = "//*[@id='user']")
	WebElement loginEmail;

	@FindBy(xpath = "//*[@id='pass']")
	WebElement loginPassword;

	@FindBy(xpath = "//*[@id='loginLink']")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@id='frmLogin']/div/div[1]")
	WebElement authonticationFailMessage;

	@FindBy(xpath = "//*[@id='myaccount_manage']")
	WebElement authonticationSuccessMEssage;

	@FindBy(xpath = "//*[@id='asdf']")
	WebElement SignUpLbl;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[1]/input[1]")
	WebElement SignUpFirstName;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[1]/input[2]")
	WebElement SignUpLastName;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[3]/input[3]")
	WebElement SignUpMobile;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[1]/input[3]")
	WebElement SignUpEmail;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[1]/input[4]")
	WebElement SignUpConfirmMail;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[3]/input[1]")
	WebElement SignUpPassword;

	@FindBy(xpath = "//*[@id='frm_registration']/div[1]/div[3]/input[2]")
	WebElement SignUpConfirmPassword;

	@FindBy(xpath = "//*[@id='frm_registration']/div[2]/input[3]")
	WebElement SignUpSubmitBtn;

	@FindBy(xpath = "//*[@id='signup']/div/div[2]/p[1]")
	WebElement NewUserSuccessMessage;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void loginToAppilcation(String email, String pwd) {

		loginLbl.click();
		log.info("Clicked on the login label and Object is :" + loginLbl.toString());
		loginEmail.clear();
		loginEmail.sendKeys(email);
		log.info("Enter email address and Object is :" + loginEmail.toString());
		loginPassword.clear();
		loginPassword.sendKeys(pwd);
		log.info("Enter Password  and Object is :" + loginPassword.toString());
		loginBtn.click();
		log.info("Clicked the Login button and Object is :" + loginBtn.toString());
	}

	public String getFailedMessage() {
		log.info("Verifing the Error message :" + authonticationFailMessage.toString());
		return authonticationFailMessage.getText();
	}
	
	public String getSuccessMessage(){
		log.info("Verifing the Error message :" + authonticationSuccessMEssage.toString());
		return authonticationSuccessMEssage.getText();
	}

	public void new_userLogin(String firstname, String lastname, String mobile, String email, String password,
			String confirmPassword) {

		SignUpLbl.click();
		log.info("Clicked on SignUp option and object is :" + SignUpLbl.toString());

		SignUpFirstName.clear();
		SignUpFirstName.sendKeys(firstname);
		log.info("Entered the First Name in sign up form and object is :" + SignUpFirstName.toString());

		SignUpLastName.clear();
		SignUpLastName.sendKeys(lastname);
		log.info("Entered the Last Name in sign up form and object is :" + SignUpLastName.toString());

		SignUpMobile.clear();
		SignUpMobile.sendKeys(mobile);
		log.info("Entered the mobile number in sign up form and object is  :" + SignUpMobile.toString());

		SignUpEmail.clear();
		SignUpEmail.sendKeys(email);
		log.info("Entered the Email address in sign up form and object is  :" + SignUpEmail.toString());

		SignUpConfirmMail.clear();
		SignUpConfirmMail.sendKeys(email);
		log.info("Entered the Confirmation Email address in sign up form and object is  :"
				+ SignUpConfirmMail.toString());

		SignUpPassword.clear();
		SignUpPassword.sendKeys(password);
		log.info("Entered the Password in sign up form and object is  :" + SignUpPassword.toString());

		SignUpConfirmPassword.clear();
		SignUpConfirmPassword.sendKeys(confirmPassword);
		log.info("Entered the Confirm Password in sign up form and object is  :" + SignUpConfirmPassword.toString());

		SignUpSubmitBtn.click();
		log.info("Clicked on SignUp button and object is :" + SignUpSubmitBtn.toString());

	}

	public boolean userSignupSuccessMessage(String firstname, String lastname) {

		return driver.findElement(By.xpath("//*[contains(text(),'" + firstname + " " + lastname + "')]")).isDisplayed();

	}

	public boolean verifySuccessMsgTextPresent() {

		return authonticationSuccessMEssage.isDisplayed();

	}

	public boolean verifyFailureMsgTextPresent() {

		return authonticationFailMessage.isDisplayed();

	}
}
