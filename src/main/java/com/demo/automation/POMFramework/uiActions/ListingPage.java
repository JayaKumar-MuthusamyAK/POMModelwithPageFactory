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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.demo.automation.POMFramework.constants.ConstantsVariable;
import com.demo.automation.POMFramework.testBase.TestBase;

public class ListingPage extends TestBase {

	public static final Logger log = Logger.getLogger(ListingPage.class.getName());
	WebDriver driver;
	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[1]/section/div[1]/div[1]/span")
	WebElement projectTotalCount;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2")
	List<WebElement> projectNameList;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/div[2]/span[1]")
	List<WebElement> projectPrice;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/div[2]/div")
	List<WebElement> verifyShowMoreBtn;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/div[2]/div")
	WebElement showMoreProjectBtn;

	@FindBy(xpath = "//img[@class='product-thumb']")
	List<WebElement> projectImg;

	@FindBy(xpath = "//span[@class='product-actionsButton product-addToBag']/span")
	WebElement addCartbtn;

	@FindBy(xpath = "//*[@id='desktopSearchResults']/div[2]/section/ul/li/div[2]/div[2]/button")
	List<WebElement> chooseSize;

	@FindBy(xpath = "//*[@id='desktop-headerMount']/div/div[2]/div")
	WebElement addCartSuccessMessage;

	// Add button //span[@class='product-actionsButton product-addToBag']/span

	public ListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void scrollDownOnprojectList() throws InterruptedException {

		int selectCount = 0;
		int oldCount = projectNameList.size();
		Actions act = new Actions(driver);

		while (selectCount < oldCount) {

			selectCount = oldCount;
			act.moveToElement(projectNameList.get(oldCount - 1)).build().perform();
			if (verifyShowMoreBtn.size() != 0) {
				showMoreProjectBtn.click();
			}
			Thread.sleep(1000);
			projectNameList = driver
					.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2"));
			oldCount = projectNameList.size();
			// System.out.println(oldCount);
			log("Scrolling the page Now project count is :" + oldCount);

			// selectCount++;
		}
	}

	public void addProjectToBag(String projectName, String size) throws InterruptedException {

		Actions act = new Actions(driver);
		scrollDownOnprojectList();

		// System.out.println(projectNameList.get(selectCount).getText());
		// log.info(projectNameList.get(selectCount).getText());
		for (int i = 0; i < projectNameList.size(); i++) {
			// act.moveToElement(projectNameList.get(i)).build().perform();
			log("Project Name is :" + projectNameList.get(i).getText());
			if (projectNameList.get(i).getText().equals(projectName)) {

				if (i <= 9) {
					clickOnAddtoBagWithInLmt(i, act);
				} else {
					clickOnAddtoBagAboveLmt(i, act);
				}
				log("Clicked on add cart button in project tail.Object name is :" + addCartbtn.toString());
				clickOnbutton_ListofElemnets(driver.findElements(By.xpath(
						"//*[@id='desktopSearchResults']/div[2]/section/ul/li[" + (i + 1) + "]/div[2]/div[2]/button")),
						"M");
				break;

			}
		}
	}

	public void moveCursor(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		log("Cursor is moved into given element object name is :" + element.toString());
	}

	public String totalProjectCount() {

		log("TOTAL PROJECT COUNT IS :" + projectTotalCount.getText());
		return projectTotalCount.getText();

	}

	public void clickOnbutton_ListofElemnets(List<WebElement> elements, String name) {
		log("Different shirt size available is :" + elements.size());

		int count = 0;
		while (count < elements.size()) {
			// System.out.println(elements.get(count).getText());
			
			if (elements.get(count).getText().equals(name)) {
				log("Selected the Size of the shirts:" + elements.get(count).getText());
				elements.get(count).click();
				break;
			}
			count++;
		}
	}

	public HashMap<String, Integer> mappingProjectPrice() throws InterruptedException {

		scrollDownOnprojectList();
		int initialCount = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while (initialCount < projectNameList.size()) {

			map.put(projectNameList.get(initialCount).getText(),
					Integer.parseInt(projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]));
			// System.out.println(projectNameList.get(initialCount).getText()+"-"+projectPrice.get(initialCount).getText().trim().split("Rs.
			// ")[1]);

			Thread.sleep(500);
			log(projectNameList.get(initialCount).getText() + "-"
					+ projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]);
			initialCount++;
		}

		return map;
	}

	public void openGivenProject(String selectProjectName) {

		log("Given project name page is opening");
		driver.findElement(By.xpath("//img[@class='product-thumb'][contains(@alt,'" + selectProjectName + "')]"))
				.click();
	}

	public void pagescrollDown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		js.executeScript("scroll(250, 0);");
	}

	public void pagescrollUptoElement(WebElement element) {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,250)", element);
	}

	public void clickOnAddtoBagWithInLmt(int productNumber, Actions act) throws InterruptedException {
		log("Cursor is moving on the given project name. Calling the method is Click on the within the limit");
		Thread.sleep(3000);
		act.moveToElement(driver.findElement(
				By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[" + productNumber + "]/a/img"))).build()
				.perform();
		// driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[57]/a/div[1]/img")).click();
		log("Click on the given project Add to Bag button");
		driver.findElement(By.xpath(
				"//*[@id='desktopSearchResults']/div[2]/section/ul/li[" + productNumber + "]/div[3]/span[2]/span"))
				.click();
		selectSizeOftheproduct(productNumber);

	}

	public void clickOnAddtoBagAboveLmt(int productNumber, Actions act) throws InterruptedException {
		log("Cursor is moving on the given project name. Calling method is click on the Above limit");
		Thread.sleep(3000);
		act.moveToElement(driver.findElement(
				By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[" + productNumber + "]/a/div[1]/img")))
				.build().perform();
		// driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[57]/a/div[1]/img")).click();
		waitUntilElementVisible(
				driver.findElement(By.xpath("//span[@class='product-actionsButton product-addToBag']/span")));
		log("Click on the given project Add to Bag button");
		driver.findElement(By.xpath("//span[@class='product-actionsButton product-addToBag']/span")).click();

		selectSizeOftheproduct(productNumber);

	}

	public void waitUntilElementVisible(WebElement element) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, ConstantsVariable.waitingTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		// Thread.sleep(500);

	}

	public void waitUntilElementIsInvisible(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, ConstantsVariable.waitingTime);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

	public void selectSizeOftheproduct(int projectNo) throws InterruptedException {
		List<WebElement> diff_Size = driver.findElements(By
				.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[" + projectNo + "]/div[2]/div[2]/button"));
		System.out.println(diff_Size.size());
		log("Given Project availabel size is :" + diff_Size.size());
		for (int i = 0; i < diff_Size.size(); i++) {

			System.out.println(diff_Size.get(i).getText());
			if (diff_Size.get(i).getText().equals("40")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.visibilityOf(diff_Size.get(i)));
				Thread.sleep(3000);
				log("Select the given project size.");
				diff_Size.get(i).click();
				// verifyAddCartSuccessMessage();
				break;
			}
		}
	}

	public String verifyAddCartSuccessMessage() {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(addCartSuccessMessage));
		log("Added cart Success Message is : " + addCartSuccessMessage.getText());
		return addCartSuccessMessage.getText();

	}

	public void log(String data) {

		log.info(data);
		Reporter.log(data);
	}

}
