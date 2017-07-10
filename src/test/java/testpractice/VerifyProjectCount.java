package testpractice;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class VerifyProjectCount {

		WebDriver driver;
		
		ArrayList<String> selecteProjectNames= new ArrayList<String>();
		
		@BeforeClass
		public void setUp(){
			//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			//DesiredCapabilities cap = new DesiredCapabilities();
			//cap.setCapability("marionette", false);
			//driver = new FirefoxDriver(cap);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://www.myntra.com/");
			
		}
		
		@Test
		public void VerifyProductCount() throws InterruptedException{
			List<WebElement> maintxt = driver.findElements(By.xpath("//*[@id='desktop-header-cnt']/div/nav/div/div/div/a"));
			Actions act = new Actions(driver);
			int total = maintxt.size();
			int i=0;
			
			while(i<total){
				System.out.println(maintxt.get(i).getText());
				if(maintxt.get(i).getText().equalsIgnoreCase("Men")){
					act.moveToElement(maintxt.get(i)).build().perform();
					break;
				}
			}
		  Thread.sleep(5000);
					List<WebElement> subtext = driver.findElements(By.xpath("//*[@id='desktop-header-cnt']/div/nav/div/div[1]/div/div/div/ul/li/a"));
					for(int j=0; j<subtext.size();j++){
						System.out.println(subtext.get(j).getText());
						if(subtext.get(j).getText().equalsIgnoreCase("Active T-Shirts")){
							subtext.get(j).click();
							break;
						}
						
					}
					
					String projectCountText= driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[1]/section/div[1]/div[1]/span")).getText();
					System.out.println(projectCountText);
					String Text = projectCountText.split(" ")[1];
					System.out.println(Text);
					int projectCount = Integer.parseInt(Text);
					
					List<WebElement> projectNameList=driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2"));
					List<WebElement> projectImg = driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li"));
					System.out.println(projectNameList.size());
					int displayingListCount= projectNameList.size();
					Assert.assertTrue(projectCount==displayingListCount, "Project Count is Matching.");
					Thread.sleep(7000);
					String projectName ="Printed Lichfield T-shirt";
					int selectCount =0;
					while(selectCount<displayingListCount){
						System.out.println(projectNameList.get(selectCount).getText());
						if(projectNameList.get(selectCount).getText().equals(projectName)){
							act.moveToElement(projectImg.get(selectCount)).build().perform();
							//act.moveToElement(driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li["+selectCount+"]/div[3]/span[2]/span"))).build().perform();
							//act.moveToElement(driver.findElement(By.xpath("//span[@class='product-actionsButton product-addToBag']/span"))).build().perform();
							Thread.sleep(3000);
							driver.findElement(By.xpath("//span[@class='product-actionsButton product-addToBag']/span")).click();
							driver.findElement(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/div[2]/div[2]/button[1]")).click();
							break;
						}
						selectCount++;
					}
		}
		
		@AfterClass
		public void endTest(){
			
		}
	}


