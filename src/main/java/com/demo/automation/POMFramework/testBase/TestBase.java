package com.demo.automation.POMFramework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.demo.automation.POMFramework.customListners.WebEventListnersClass;
import com.demo.automation.POMFramework.customListners.WebEventListnersClass;
import com.demo.automation.POMFramework.excelReader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public FileInputStream fi;
	public Properties prop;
	public EventFiringWebDriver dr;
	public WebEventListnersClass eventWebDriver;
	public static ExtentReports report;
	public static ExtentTest test;
	

	//public String browserName = "firefox";
	//String url = "https://in.webuy.com/";

	ExcelReader excel;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		report = new ExtentReports(System.getProperty("user.dir")+ "//src//main//java//com//demo//automation//POMFramework//extentreport//test"+simpleformat.format(calendar.getTime())+".html",false);
		log.info("Report is initialled");
		try {
			report.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName()).addSystemInfo("Environment", System.getenv("JAVA_HOME")).addSystemInfo("User Name", "Jayakumar M");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init() throws IOException {
		loadData();
		
		selectBrowser(prop.getProperty("browser"));
		getUrl(prop.getProperty("url"));

		String log4jConfigPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
	}

	// Get the properties file data.
	public void loadData() throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir")
				+ "/src/main/java/com/demo/automation/POMFramework/config/OR.properties");
		prop = new Properties();
		prop.load(fi);
	}

	public void selectBrowser(String browserName) {
		System.out.println(browserName);
		if (browserName.equalsIgnoreCase("firefox")) {

			log.info("Openinng the browser.." + browserName);
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("marionette", false);
			
			driver = new FirefoxDriver(cap);
			//driver = new EventFiringWebDriver(dr);
			//eventWebDriver = new WebEventListnersClass();
			//driver.register(eventWebDriver);
			//eventWebDriver=new EventFiringWebDriver(driver);
			
		} else if (browserName.equalsIgnoreCase("chrome")) {
			log.info("Openinng the browser.." + browserName);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			// DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setCapability("marionette", false);
			
			driver = new ChromeDriver();
			
			
		}

	}

	public void getUrl(String url) {

		log.info("Navigating the URl");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	public String[][] getData(String excelName, String sheetName) {
		// TestData.xlsx
		String path = System.getProperty("user.dir") + "//src//main//java//com//demo//automation//POMFramework//data//"
				+ excelName;
		excel = new ExcelReader(path);

		String data[][] = excel.getDataFromSheet(sheetName, excelName);

		return data;
	}

	public void waitForElement(int timeOutInSeconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void getScreenShots(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "//src//main//java//com//demo//automation//POMFramework//screenShot//";
			File destFile = new File(
					(String) reportDirectory + name + "-" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height ='100' widght ='100'/> </a>");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void waitForSomeTime() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	public void getStatus(ITestResult result){
		
		if(result.getStatus()==ITestResult.SUCCESS){
			test.log(LogStatus.PASS, "test is pass"+result.getName());
		}
		else if(result.getStatus()==ITestResult.FAILURE){
			test.log(LogStatus.ERROR, result.getName()+"test is failed"+result.getThrowable());
		    test.log(LogStatus.FAIL, result.getName()+"test is failed"+test.addScreenCapture(catureScreen("")));
		}
		else if(result.getStatus()==ITestResult.SKIP){
			test.log(LogStatus.SKIP, result.getName()+"test is skip"+result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.STARTED){
			test.log(LogStatus.INFO, result.getName()+"Test is Started");
		}
		
	}

	@AfterMethod
	public void aftermethod(ITestResult result){
		getStatus(result);
	}
	
	@BeforeMethod
	public void beforemethod(Method result){
		test = report.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"Test is Started");
		log.info("Report is Started");
	}
	
	@AfterClass
	public void endTesting(){
		closeBrowser();
		
	}
	public void closeBrowser() {
		log.info("Test is Ended");
		//driver.quit();
		report.endTest(test);
		report.flush();
	}

	public String catureScreen(String filename) {
		if(filename==""){
			filename = "blank";
		}
		File destFile =null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try{
			String path = new File(System.getProperty("user.dir"))+"//src//main//java//com//demo//automation//POMFramework//";
			destFile = new File ((String)path+"_"+simpleformat.format(calendar.getTime())+".png");
			FileUtils.copyDirectory(scrFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return destFile.toString();
	}

}
