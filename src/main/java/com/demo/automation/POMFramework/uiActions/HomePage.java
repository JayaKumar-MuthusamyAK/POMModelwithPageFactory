package com.demo.automation.POMFramework.uiActions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demo.automation.POMFramework.testBase.TestBase;

public class HomePage extends TestBase {

	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	
	WebDriver driver;

	@FindBy(xpath = "//*[@id='desktop-header-cnt']/div/div[2]/div/div[1]/span[1]")
	WebElement yourAccount;

	@FindBy(xpath = "//*[@id='desktop-header-cnt']/div/div[2]/div/div[2]/div[2]/div[2]/a[2]")
	WebElement loginLbl;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[1]/input")
	WebElement loginEmail;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[2]/input")
	WebElement loginPassword;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[2]/button")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div[2]/div/div/p")
	WebElement loginFailMessage;

	@FindBy(xpath = "//*[@id='desktop-header-cnt']/div/div[2]/div/div[2]/div[2]/div[1]/a/div[2]")
	WebElement loginSuccessMEssage;

	@FindBy(xpath = "//div[@class='desktop-accInfoSection'][contains(text(),'Logout')]")
	List<WebElement> verifylogOutBtn;

	@FindBy(xpath = "//div[@class='desktop-accInfoSection'][contains(text(),'Logout')]")
	WebElement logOutBtn;
	
	@FindBy(xpath = "//*[@id='desktop-header-cnt']/div/div[2]/div/div[2]/div[2]/div[2]/a[1]")
	WebElement SignUpLbl;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[1]/input")
	WebElement SignUpEmail;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[3]/input")
	WebElement SignUpMobile;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[2]/input")
	WebElement SignUpPassword;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[1]/fieldset/label[1]")
	WebElement Gender;

	@FindBy(xpath = "//*[@id='mountRoot']/div/div/div/form/fieldset[2]/button")
	WebElement SignUpSubmitBtn;

	@FindBy(xpath = "//div[@class='desktop-infoEmail']")
	WebElement newUserSuccessMessage;

	@FindBy(xpath = "//*[@id='desktop-header-cnt']/div/div[3]/input")
	WebElement SearchBox;
	
	@FindBy(xpath="//div[@class='desktop-userIconsContainer']")
	WebElement user_account_Link;
	
	@FindBy(xpath="//*[@id='mountRoot']/div/div/div[2]/div/div/p")
	List<WebElement> useralreadyExist;


	public HomePage(WebDriver driver ) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}

	public void loginToAppilcation(String email, String pwd) {

		moveCursor();
		loginLbl.click();
		log("Clicked on the login label and Object is :" + loginLbl.toString());
		loginEmail.clear();
		loginEmail.sendKeys(email);
		log("Enter email address and Object is :" + loginEmail.toString());
		loginPassword.clear();
		loginPassword.sendKeys(pwd);
		log("Enter Password  and Object is :" + loginPassword.toString());
		loginBtn.click();
		log("Clicked the Login button and Object is :" + loginBtn.toString());
	}

	public String getFailedMessage() {
		log("Verifing the Error message :" + loginFailMessage.toString());
		// System.out.println(authonticationFailMessage.getText());
		return loginFailMessage.getText();
	}

	public String getSuccessMessage() {
		
		log("Verifing the Error message :" + loginSuccessMEssage.toString());
		// System.out.println(authonticationSuccessMEssage.getText());
		return loginSuccessMEssage.getText();
	}

	public void new_userLogin(String email,String mobile, String password, String gender) {
		moveCursor();
		SignUpLbl.click();
		log("Clicked on SignUp option and object is :" + SignUpLbl.toString());

		SignUpMobile.clear();
		SignUpMobile.sendKeys(mobile);
		log("Entered the mobile number in sign up form and object is  :" + SignUpMobile.toString());

		SignUpEmail.clear();
		SignUpEmail.sendKeys(email);
		log("Entered the Email address in sign up form and object is  :" + SignUpEmail.toString());

		SignUpPassword.clear();
		SignUpPassword.sendKeys(password);
		log("Entered the Password in sign up form and object is  :" + SignUpPassword.toString());

		driver.findElement(By.xpath("//label[@class='register-gender-label'][contains(text(),'"+gender+"')]")).click();
		
		SignUpSubmitBtn.click();
		log("Clicked on SignUp button and object is :" + SignUpSubmitBtn.toString());

	}

	public String userSignupSuccessMessage() {

		Actions act = new Actions(driver);
		act.moveToElement(user_account_Link).build().perform();
		
		return newUserSuccessMessage.getText();

	}


	public boolean verifyEmailAlreadyExistMsgTextPresent() {
		
		return useralreadyExist.size()!=0;

	}

	public WebElement waitForElementPresent() {

		return SearchBox;

	}

	public boolean checkLogOutbtn() {
		user_account_Link.click();
		return verifylogOutBtn.size()!=0;
	}
	
	public void clickOnLogOutbtn() {
		user_account_Link.click();
		logOutBtn.click();
	}

	public boolean verifyLoginErrorMessage()
	{
		return loginFailMessage.isDisplayed();
	}
	public void moveCursor() {

		Actions act = new Actions(driver);
		act.moveToElement(yourAccount).build().perform();
	}
	public void clickOnMainMenuLink(String menuname){
		log("Clicking the Main menu option. The option name is :"+menuname);
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/nav/div/div/div/a[contains(text(),'"+menuname+"')]")).click();
	}
	public void clickOnSubLink(String menuname){
		log("Clicking the Sub menu option. The option name is :"+menuname);
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/nav/div/div/div/div/div/ul/li/a[contains(text(),'"+menuname+"')]")).click();
	}
	public void moveMouseMainMenu(String menuname){
		
		//System.out.println(driver);
		log("Moving cursor on main menu fields :" +menuname);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/nav/div/div/div/a[contains(text(),'"+menuname+"')]"))).build().perform();
	}

   public void moveMouseSubMenu(String menuname){
		
	   log("Moving cursor on sub menu fields :"+menuname);
	   Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/nav/div/div/div/div/div/ul/li/a[contains(text(),'"+menuname+"')]"))).build().perform();
	}
	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}
	
}
