package testpractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Practice1 {
	
	WebDriver driver;
	
	String selectProjectName="Printed Lichfield T-shirt";
	String shirt_Size = "XXL";
	
	@Test
	public void add_Cart() throws InterruptedException{
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		
		driver = new FirefoxDriver(cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://www.myntra.com/men-sports-wear-tshirts?src=tNav");
		
		// //*[@id='desktopSearchResults']/div[2]/section/ul/li[3]/a/div/div[2]/span[1]
		//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2
		
		List<WebElement> projectNamelist = driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/h2"));
		List<WebElement> projectPrice = driver.findElements(By.xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li/a/div/div[2]/span[1]"));
		
		int initialCount =0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		while(initialCount<projectNamelist.size()){
			
			map.put(projectNamelist.get(initialCount).getText(), Integer.parseInt(projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]));
			System.out.println(projectNamelist.get(initialCount).getText()+"-"+projectPrice.get(initialCount).getText().trim().split("Rs. ")[1]);
			initialCount++;
		}
		
		System.out.println(map.get(selectProjectName));
		driver.findElement(By.xpath("//img[@class='product-thumb'][contains(@alt,'"+selectProjectName+"')]")).click();
		Thread.sleep(5000);
		String project_Name = driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[1]/h1")).getText();
		System.out.println(project_Name);
		String priceText = driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[1]/p[2]/strong")).getText().trim().split("Rs. ")[1];
		int price = Integer.parseInt(priceText);
		Assert.assertTrue(project_Name.contains(selectProjectName), "Project page is opened. Selected project only opened.");
		Assert.assertEquals(map.get("Printed Lichfield T-shirt").intValue(), price,  "Project price is matching correctly");
		
		//img[@class='product-thumb'][starts-with(@alt,'Quiksilver Men White & Grey Printed Lichfield T-shirt')]
		
		driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[4]/button[2]")).click();
		String size_error_message = driver.findElement(By.xpath("//*[@id='sizeButtonsContainer']/span")).getText();
		String error_Text = "Please select a size";
		Assert.assertEquals(size_error_message, error_Text);
		//*[@id='sizeButtonsContainer']//p[contains(text(),'XL')]
		driver.findElement(By.xpath("//*[@id='sizeButtonsContainer']//p[.='"+shirt_Size+"']")).click();
		driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/main/div[2]/div[2]/div[4]/button[2]")).click();
		
		String wislistCount = driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/div[2]/a/span[2]")).getText();
		Assert.assertTrue(Integer.parseInt(wislistCount)==1, "Project is selected. Count is updated in wish List");
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/div[2]/a/span[2]")).click();
		
		String shoppingProject_Name = driver.findElement(By.xpath("//*[@id='prod-item-1499594611']/div[2]/div[1]/a")).getText();
		String shoppingProject_price = driver.findElement(By.xpath("//*[@id='prod-item-1499594611']/div[2]/div[4]/div/div")).getText();
		
		String shoppingSize = driver.findElement(By.xpath("//*[@id='prod-item-1499594611']/div[2]/div[3]/div[1]/span[1]/span[2]")).getText();
		String shoppingQuty = driver.findElement(By.xpath("//*[@id='prod-item-1499594611']/div[2]/div[3]/div[1]/span[3]/span[2]")).getText();
		
		Assert.assertEquals(shoppingProject_Name, map.get(selectProjectName));
		Assert.assertTrue(Integer.parseInt(shoppingProject_price)==1, "Project is selected.Count is matched.");
		
		Assert.assertTrue(shoppingSize.equals(shirt_Size), "Size is Matching.");
		Assert.assertTrue(Integer.parseInt(shoppingQuty)==1, "Quntity is Matching.");
		
		
	}

}
