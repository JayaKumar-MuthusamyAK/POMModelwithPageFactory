package com.demo.automation.POMFramework.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demo.automation.POMFramework.testBase.TestBase;

public class ProjectViewPage extends TestBase{
	
	public static final Logger log = Logger.getLogger(ProjectViewPage.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[1]/h1")
	WebElement projectNametext;
	
	@FindBy(xpath="//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[1]/p[2]/strong")
	WebElement projectPriceText;
	
	@FindBy(xpath="//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[4]/button[2]")
	WebElement addButton;
	
	@FindBy(xpath="//*[@id='sizeButtonsContainer']/span")
	WebElement select_size_Errormsg;
	
	@FindBy(xpath="//*[@id='desktop-header-cnt']/div/div[2]/a/span[2]")
	WebElement wishlistCount;
	
	public ProjectViewPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getProjectNameText(){
		
		log("Getting the project name in project view page. project name object is :"+projectNametext.toString());
		return projectNametext.getText();
	}
	
	public String getPriceOftheProject(){
		
		log("Getting the price of the project in project view page. Price object is :"+projectPriceText.toString());
		return projectPriceText.getText().split("Rs. ")[1];
		
	}
	
	public void clickOnAddButton(String shirt_Size){
		log("Click on the add button before select the size of the product :"+addButton.toString());
		driver.findElement(By.xpath("//*[@id='sizeButtonsContainer']//p[.='"+shirt_Size+"']")).click();
		addButton.click();
		
	}
	
	public String verifyWithoutSelectSize(){
		log("Click on the add button with out select the size of the product Add button Object is  :"+addButton.toString());
		addButton.click();
		return select_size_Errormsg.getText();
		
	}
	
	public String verifyBagCount(){
		log("Verify the wishlist project count. And Wishlist Object is  :"+wishlistCount.toString());
		return wishlistCount.getText();
		
	}
	
	
	public void log(String data){
		
		log.info(data);
		Reporter.log(data);
	}

}
