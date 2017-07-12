package testpractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class RediffWebsite {
	
	WebDriver driver;
	@Test
	public void get_LinkInMenu(){
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("marionette", false);
		
		driver = new FirefoxDriver(cap);
		driver.manage().window().maximize();
		driver.get("http://shopping.rediff.com/?sc_cid=inhome_icon");
		
		// Get the particular menu hyper link.
		List<WebElement> aLinks= driver.findElements(By.xpath("//*[@id='popular_cat']/h3/a"));
		
		for(WebElement element : aLinks){
			System.out.println(element.getAttribute("href"));
		}
		
		
		// Or Other way to take the particular menu a link.
		WebElement linkName = driver.findElement(By.xpath("//*[@id='popular_cat']"));
		List<WebElement> links = linkName.findElements(By.tagName("a"));
		for(int i=0; i<links.size(); i++){
			System.out.println(links.get(i).getAttribute("href"));
		}
	}

}
