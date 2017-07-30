package testpractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Practice2 {
	
	WebDriver driver;
	

	@Test
	public void sampleMethod(){
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		
		driver = new FirefoxDriver(cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://www.myntra.com/");
		
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/div[2]/div/div[1]/span[1]")).click();
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']/div/div[2]/div/div[2]/div[2]/div[2]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[1]/input")).sendKeys("jakay34@gmail.com");
		driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/div/form/fieldset[1]/div[2]/input")).sendKeys("234567898765");
		
		driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/div/form/fieldset[2]/button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/div[2]/div/div/p")), "The username or password you entered is incorrect"));
		System.out.println(driver.findElement(By.xpath("//*[@id='mountRoot']/div/div/div[2]/div/div/p")).getText());
	}
}
