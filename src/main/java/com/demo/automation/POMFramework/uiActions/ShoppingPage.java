package com.demo.automation.POMFramework.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demo.automation.POMFramework.testBase.TestBase;

public class ShoppingPage extends TestBase {

	public static final Logger log = Logger.getLogger(ShoppingPage.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//*[@id='desktop-header-cnt']/div/div[2]/a/span[2]")
	WebElement wishlistButton;

	@FindBy(xpath = "//*[@id='prod-item-1499594611']/div[2]/div[1]/a")
	WebElement projectNameText;

	@FindBy(xpath = "//*[@id='prod-item-1499594611']/div[2]/div[4]/div/div")
	WebElement projectOriginalPriceText;

	@FindBy(xpath = "//*[@id='prod-item-1499594611']/div[2]/div[3]/div[1]/span[1]/span[2]")
	WebElement shoppingSelectedProductSize;

	@FindBy(xpath = "//*[@id='prod-item-1499594611']/div[2]/div[3]/div[1]/span[3]/span[2]")
	WebElement shoppingSelectedProductQuty;

	@FindBy(xpath="//div[@class='total-price']")
	WebElement totalPrice;
	
	@FindBy(xpath="//span[@class='value red']")
	WebElement bagTotal;
	

	@FindBy(xpath="//span[@class='value greenrupees c-green']")
	WebElement bagDiscrount;

	@FindBy(xpath="//span[@class='vat-charge red']")
	WebElement EstimatedTax;

	@FindBy(xpath="//span[@class='total-rupees fw-600 c-dblue']")
	WebElement orderTotalprice;
	
	
	
	public ShoppingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnWishListIcon() {
		wishlistButton.click();
	}

	public String getProjectNameInShoppingpage() {

		return projectNameText.getText();

	}

	public String getProjectPriceInShoppingpage() {

		return projectOriginalPriceText.getText();

	}
	
	public String getProjectQutyInShoppingpage() {

		return shoppingSelectedProductSize.getText();

	}

	public String getProjectSizeInShoppingpage() {

		return shoppingSelectedProductQuty.getText();

	}
	
	public int verify_TotalPrice(){
		
		String Text1 = bagTotal.getText().replaceAll(",", "").split("Rs. ")[1];
		String Text2 = bagDiscrount.getText().replaceAll(",", "").split("- Rs. ")[1];
		String Text3 = EstimatedTax.getText().replaceAll(",", "").split("Rs. ")[1];
		
		System.out.println(Text1);
		System.out.println(Text2);
		System.out.println(Text3);
		
		int cast_Of_ActualPrice = Integer.parseInt(Text1);
		int cast_Of_Discount = Integer.parseInt(Text2);
		int cast_Of_estimateTex = Integer.parseInt(Text3);
		
		
		int price = (cast_Of_ActualPrice -cast_Of_Discount) + cast_Of_estimateTex;
		return price;
		
	}
	
	public int get_OrderPrice(){
		String Text4 = orderTotalprice.getText().replaceAll(",", "").split("Rs. ")[1].trim();
		int espectedresultprice = Integer.parseInt(Text4);
		return espectedresultprice;
	}
	
	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}

}
