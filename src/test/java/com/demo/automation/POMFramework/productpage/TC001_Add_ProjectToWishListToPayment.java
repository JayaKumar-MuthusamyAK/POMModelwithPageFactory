package com.demo.automation.POMFramework.productpage;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;
import com.demo.automation.POMFramework.uiActions.ListingPage;
import com.demo.automation.POMFramework.uiActions.ProjectViewPage;
import com.demo.automation.POMFramework.uiActions.ShoppingPage;

public class TC001_Add_ProjectToWishListToPayment extends TestBase {
	
	HomePage homepage;
	ListingPage listpage;
	ProjectViewPage projectviewpage;
	ShoppingPage shoppingpage;
	String selectProjectName="Men Thermal Long Johns";
	String expectedProjectName="Thermal Long Johns";
	String shirt_Size = "M";
	String error_Text = "Please select a size";
	Actions act = null;
	@BeforeClass
	public void setUp() throws IOException{
		
		init();
	}
	
	@Test
	public void Add_ProjectToWishListToPayment() throws InterruptedException{
		
		homepage = new HomePage(driver);
		listpage = new ListingPage(driver);
		projectviewpage = new ProjectViewPage(driver);
		shoppingpage = new ShoppingPage(driver);
		
		homepage.moveMouseMainMenu("Men");
		homepage.moveMouseSubMenu("Thermals");
		homepage.clickOnSubLink("Thermals");
		
		listpage.scrollDownOnprojectList();
		HashMap<String, Integer> map = listpage.mappingProjectPrice();
		System.out.println(map.get(selectProjectName));
		listpage.openGivenProject(selectProjectName);
		String projectName = projectviewpage.getProjectNameText();
		String projectPrice = projectviewpage.getPriceOftheProject();
		
		Assert.assertTrue(projectName.contains(expectedProjectName), "Expected project page only opened");
		System.out.println(map.get(selectProjectName).intValue());
		System.out.println(projectPrice);
		Assert.assertTrue(map.get(selectProjectName).intValue()==Integer.parseInt(projectPrice), "Price is Matching");
		
		String sizeSelectionerrorMsg= projectviewpage.verifyWithoutSelectSize();
		Assert.assertEquals(sizeSelectionerrorMsg, error_Text);
		projectviewpage.clickOnAddButton(shirt_Size);
		Thread.sleep(2000);
		int wishlistAddedprojectCount = Integer.parseInt(projectviewpage.verifyBagCount());
		
		Assert.assertTrue(wishlistAddedprojectCount==1, "Project is selected. Count is updated in wish List");
		
		shoppingpage.clickOnWishListIcon();
		
		
		
		Assert.assertTrue(shoppingpage.get_OrderPrice1()==shoppingpage.getTotal_Price(), "Project Price is Matching with expected result");
		
	}

}
