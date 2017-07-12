package testpractice;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class AddMultipleProjects {
	
	WebDriver driver;
	HashMap<Integer, String> projectNameandCount;
	
	int productNumber=9;
	
	@Test
	public void verfiy_count() throws InterruptedException{
		
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		
		driver = new ChromeDriver();
		//driver = new FirefoxDriver(cap);
		//driver.manage().window().maximize();
		driver.get("https://www.myntra.com/men-suits?src=tNav");
		
		
		List<WebElement> projectNameList = driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2"));
		
		int selectCount = 0;
		int oldCount = projectNameList.size();
		Actions act = new Actions(driver);
		while (selectCount < oldCount) {

			selectCount = oldCount;
			act.moveToElement(projectNameList.get(oldCount-1)).build().perform();
			if(driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/div[2]/div")).size()!=0){
				driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/div[2]/div")).click();
			}
			Thread.sleep(1000);
			projectNameList= driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2"));
			oldCount = projectNameList.size();
			System.out.println(oldCount);
			//selectCount++;
		}
		
		projectNameandCount = new HashMap<Integer, String>();
		
		
		for(int i=0; i<oldCount; i++){
			projectNameandCount.put(i, projectNameList.get(i).getText());
			System.out.println(projectNameList.get(i).getText());
		}
		
		
		if(productNumber<=9){
			
			clickOnAddtoBagWithInLmt(productNumber, act);
		}
		else{
			clickOnAddtoBagAboveLmt(productNumber,act);
		}
	
	    
	  
	}
	private void clickOnAddtoBagWithInLmt(int productNumber, Actions act) throws InterruptedException {
		    act.moveToElement(driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["+productNumber+"]/a/img"))).build().perform();
			//driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[57]/a/div[1]/img")).click();
			driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["+productNumber+"]/div[3]/span[2]/span")).click();
			
			 selectSizeOftheproduct(productNumber);
		
	}
	private void clickOnAddtoBagAboveLmt(int productNumber, Actions act) throws InterruptedException {
		act.moveToElement(driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["+productNumber+"]/a/div[1]/img"))).build().perform();
		//driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[57]/a/div[1]/img")).click();
		driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["+productNumber+"]/div[3]/span[2]/span")).click();
		
	    selectSizeOftheproduct(productNumber);
		
	}
	public void selectSizeOftheproduct(int projectNo) throws InterruptedException {
		List<WebElement> diff_Size = driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["+projectNo+"]/div[2]/div[2]/button"));
		System.out.println(diff_Size.size());
		
		for(int i=0;i<diff_Size.size();i++){
			
			System.out.println(diff_Size.get(i).getText());
			if(diff_Size.get(i).getText().equals("40")){
				Thread.sleep(1000);
				diff_Size.get(i).click();
				break;
			}
		}
	}

}
