package com.demo.automation.POMFramework.listingpage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.automation.POMFramework.constants.ConstantsVariable;
import com.demo.automation.POMFramework.testBase.TestBase;
import com.demo.automation.POMFramework.uiActions.HomePage;
import com.demo.automation.POMFramework.uiActions.ListingPage;
import com.demo.automation.POMFramework.uiActions.ShoppingPage;

public class TC002_Add_MultipleProjectIntoCart extends TestBase {

	
	ListingPage listingpage;
	HomePage homepage;
	ShoppingPage shoppingpage;
	String categoriesName = "Men";
	String subCategoriesName = "Suits";
	int productNumber = 9;
	//int[] selectproductNumber = {9,12,20,22,25};

	
	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void Add_MultipleProjectIntoCart() throws InterruptedException {
		log("=============STARTING TEST->>>>>TEST NAME IS :" + TC002_Add_MultipleProjectIntoCart.class.getName()+ "================");
		
		homepage = new HomePage(driver);
		listingpage = new ListingPage(driver);
		shoppingpage = new ShoppingPage(driver);
		// homepage.clickOnMainMenuLink("Men");
		// homepage.clickOnSubLink("Printed Varsity T-Shirt");

		homepage.moveMouseMainMenu(ConstantsVariable.categoriesName);
		homepage.moveMouseSubMenu(ConstantsVariable.subCategoriesName);
		homepage.clickOnSubLink(ConstantsVariable.subCategoriesName);
		
		listingpage.scrollDownOnprojectList();
		Actions act = new Actions(driver);
		int totalselect = ConstantsVariable.selectproductNumber.length;
		
		List<String> expectedProjectName= new LinkedList<String>();
		
		int select=0;
		while(select<totalselect){
			System.out.println(ConstantsVariable.selectproductNumber[select]);
			if (ConstantsVariable.selectproductNumber[select] <= ConstantsVariable.productNumber) {
				expectedProjectName.add(listingpage.stroreProjectName(ConstantsVariable.selectproductNumber[select]));
				listingpage.clickOnAddtoBagWithInLmt(ConstantsVariable.selectproductNumber[select], act);
				verifySuccessMessageForAddCart(select);
			} else {
				expectedProjectName.add(listingpage.stroreProjectName(ConstantsVariable.selectproductNumber[select]));
				listingpage.clickOnAddtoBagAboveLmt(ConstantsVariable.selectproductNumber[select], act);
				verifySuccessMessageForAddCart(select);
			}
			select++;
		}
		
		shoppingpage.clickOnWishListIcon();
		
		Assert.assertTrue(shoppingpage.verifyMultiProjectAddedCount()==totalselect, "WishList Count is Matching.");
		
		for(int shopcount=0; shopcount<shoppingpage.getProjectNameListInShoppingListpage().size();shopcount++){
			log(shoppingpage.getProjectNameListInShoppingListpage().get(shopcount).getText()+"--"+expectedProjectName.get(shopcount));
			if(expectedProjectName.contains(shoppingpage.getProjectNameListInShoppingListpage().get(shopcount).getText())){
				Assert.assertTrue(expectedProjectName.contains(shoppingpage.getProjectNameListInShoppingListpage().get(shopcount).getText()), "Project Name is Matching");
			}
			else{
				System.out.println("Project name is Not Matching");
			}
		}
		
		Assert.assertTrue(shoppingpage.getTotal_Price()==shoppingpage.get_OrderPrice(), "Price Calculation is Correct");
		
	}

	public void verifySuccessMessageForAddCart(int count) throws InterruptedException {
		System.out.println("verify Count is :"+count);
		if((count+1)<=1){
			System.out.println(count);
		    Assert.assertEquals(listingpage.verifyAddCartSuccessMessage(), "Added product to cart, you got "+(count+1)+" Product in cart");
		    Thread.sleep(1000);
		}
		else{
			Assert.assertEquals(listingpage.verifyAddCartSuccessMessage(), "Added product to cart, you got "+(count+1)+" Products in cart");
		    Thread.sleep(1000);
		}
	}

	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}

}
