package com.demo.automation.POMFramework.listingpage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;
import com.demo.automation.POMFramework.uiActions.ListingPage;

public class TC001_Add_ProjectsToCart extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_Add_ProjectsToCart.class);

	ListingPage listingpage;
	HomePage homepage;

	String categoriesName = "Men";
	String subCategoriesName = "Suits";
	String brandName = "Single-Breasted Formal Suit";
	String size = "XL";
	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void Add_ProjectsToCart() throws InterruptedException {
		log("=============STARTING TEST->>>>>TEST NAME IS :"+TC001_Add_ProjectsToCart.class.getName()+"================");
		listingpage = new ListingPage(driver);
		homepage = new HomePage(driver);
		// homepage.clickOnMainMenuLink("Men");
		// homepage.clickOnSubLink("Printed Varsity T-Shirt");
		
		homepage.moveMouseMainMenu(categoriesName);
		homepage.moveMouseSubMenu(subCategoriesName);
		homepage.clickOnSubLink(subCategoriesName);
		listingpage.addProjectToBag(brandName, size);
		log("=============ENDING TEST->>>>>TEST NAME IS :"+TC001_Add_ProjectsToCart.class.getName()+"================");
	}

	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}

}
