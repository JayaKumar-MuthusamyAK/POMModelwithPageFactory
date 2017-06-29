package com.demo.automation.POMFramework.customListners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.demo.automation.POMFramework.testBase.TestBase;

public class Listners extends TestBase implements ITestListener
{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext result) {
		Reporter.log("Automation test is Start, Test name is: "+result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		if(!result.isSuccess()){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		try{
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"//src//main//java//com//demo//automation//POMFramework//screenShot//";
			File destFile = new File((String) reportDirectory +methodName +"//FailureScreenShots//"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(scrFile, destFile);
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		}
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("Automation test is Start, Test name is: "+result.getMethod().getMethodName());
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {

		if(result.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String methodName = result.getName();
			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			try{
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"//src//main//java//com//demo//automation//POMFramework//successScreenshots";
				File destFile = new File((String) reportDirectory +methodName +"-"+formater.format(calendar.getTime())+".png");
				
				FileUtils.copyFile(scrFile, destFile);
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			}
		
	}
    
}
