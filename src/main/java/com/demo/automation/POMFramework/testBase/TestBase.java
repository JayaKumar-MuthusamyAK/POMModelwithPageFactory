package com.demo.automation.POMFramework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.demo.automation.POMFramework.excelReader.ExcelReader;

public class TestBase {

	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public FileInputStream fi;
	public Properties prop;
	
	public String browserName = "firefox";
	String url = "https://in.webuy.com/";

	ExcelReader excel;
	
	
	public void init() throws IOException {
		loadData();
		selectBrowser(browserName);
		getUrl(url);
		
		String log4jConfigPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
	}

	// Get the properties file data.
	public void loadData() throws IOException{
		fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/demo/automation/POMFramework/config/OR.properties");
		prop = new Properties();
		prop.load(fi);
	}
	
	public void selectBrowser(String browserName) {
		System.out.println(prop.getProperty("browser"));
		if (browserName.equalsIgnoreCase("firefox")) {

			log.info("Openinng the browser.."+browserName);
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("marionette", false);
			driver = new FirefoxDriver(cap);
		}
		else if(browserName.equalsIgnoreCase("chrome")){
			log.info("Openinng the browser.."+browserName);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			//DesiredCapabilities cap = new DesiredCapabilities();
			//cap.setCapability("marionette", false);
			driver = new ChromeDriver();
			
		}

	}

	public void getUrl(String url) {

		log.info("Navigating the URl");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	public String[][] getData(String excelName, String sheetName){
		//TestData.xlsx
		String path= System.getProperty("user.dir")+"//src//main//java//com//demo//automation//POMFramework//data//"+excelName;
		excel = new ExcelReader(path);
		
		String data[][]=excel.getDataFromSheet(sheetName, excelName);
		
		return data;
	}
	
	public void waitForElement(int timeOutInSeconds, WebElement element){
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void getScreenShots(String name){
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"//src//main//java//com//demo//automation//POMFramework//screenShot//";
			File destFile = new File((String) reportDirectory +name +"-"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(scrFile, destFile);
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height ='100' widght ='100'/> </a>" );
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
}
