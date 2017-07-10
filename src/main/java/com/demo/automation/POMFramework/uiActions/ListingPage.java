package com.demo.automation.POMFramework.uiActions;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.demo.automation.POMFramework.testBase.TestBase;

public class ListingPage extends TestBase {

	public static final Logger log = Logger.getLogger(ListingPage.class.getName());
	WebDriver driver;
	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[1]/section/div[1]/div[1]/span")
	WebElement projectTotalCount;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2") 
	List<WebElement> projectNameList;
	
	@FindBy(xpath="//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/div[2]/span[1]")
	List<WebElement> projectPrice;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/ul/li")
	List<WebElement> projectImg;

	@FindBy(xpath = "//span[@class='product-actionsButton product-addToBag']/span")
	WebElement addCartbtn;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/ul/li/div[2]/div[2]/button")
	List<WebElement> chooseSize;

	public ListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addProjectToBag(String projectName, String size) throws InterruptedException {

		int selectCount = 0;
		while (selectCount < projectNameList.size()) {

			//System.out.println(projectNameList.get(selectCount).getText());
			log("Project Name is :"+projectNameList.get(selectCount).getText());
			//log.info(projectNameList.get(selectCount).getText());
			if (projectNameList.get(selectCount).getText().equals(projectName)) {
				moveCursor(projectNameList.get(selectCount));
				Thread.sleep(3000);
				addCartbtn.click();
				log("Clicked on add cart button in project tail.Object name is :"+addCartbtn.toString());
				clickOnbutton_ListofElemnets(
						driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["
								+ (selectCount+1) + "]/div[2]/div[2]/button")),
						"M");
				break;
			}

			selectCount++;
		}
	}

	public void moveCursor(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		log("Cursor is moved into given element object name is :"+element.toString());
	}

	public String totalProjectCount() {

		log("TOTAL PROJECT COUNT IS :"+projectTotalCount.getText());
		return projectTotalCount.getText();

	}

	public void clickOnbutton_ListofElemnets(List<WebElement> elements, String name) {
		log("Different shirt size available is :"+elements.size());
		
		int count = 0;
		while (count < elements.size()) {
			//System.out.println(elements.get(count).getText());
			log("Select the Size of the shirts:"+elements.get(count).getText());
			if (elements.get(count).getText().equals(name)) {
				elements.get(count).click();
				break;
			}
			count++;
		}
	}
	
	public HashMap<String, Integer> mappingProjectPrice() throws InterruptedException{
		
		int initialCount =0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while(initialCount<projectNameList.size()){
			
			map.put(projectNameList.get(initialCount).getText(), Integer.parseInt(projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]));
			//System.out.println(projectNameList.get(initialCount).getText()+"-"+projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]);
			
			Thread.sleep(500);
			log(projectNameList.get(initialCount).getText()+"-"+projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]);
			initialCount++;
		}
		
		return map;
	}
	
	public void openGivenProject(String selectProjectName){
		
		driver.findElement(By.xpath("//img[@class='product-thumb'][contains(@alt,'"+selectProjectName+"')]")).click();
	}
	
	public void pagescrollDown() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		js.executeScript("scroll(250, 0);");
	}

	public void log(String data) {

		
		log.info(data);
		Reporter.log(data);
	}

}
