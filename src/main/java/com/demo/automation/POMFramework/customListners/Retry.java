package com.demo.automation.POMFramework.customListners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int max_OfRetry=2;
	int retryCount =0;
	
	public boolean retry(ITestResult result) {
		
		if(retryCount<max_OfRetry){
			System.out.println("======RETRYING TESTCASES  "+result.getName()+" AND THE STATUS IS : "+getSuccessStatus(result.getStatus())+" FOR THE RETRY COUNT IS "+(retryCount+1)+" times====");
			retryCount++;
			return true;
			
		}
		return false;
	}

	public String getSuccessStatus(int status) {
		
		String resultName = null;
		
		if(status ==1)
			resultName ="Success";
		if(status==2)
			resultName="Fail";
		if(status ==3)
			resultName ="Skip";
		
		
		return resultName;
	}

}
