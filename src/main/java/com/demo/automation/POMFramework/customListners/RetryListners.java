package com.demo.automation.POMFramework.customListners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListners implements IAnnotationTransformer{

	public void transform(ITestAnnotation result, Class arg1, Constructor arg2, Method arg3) {
		
		IRetryAnalyzer retry = result.getRetryAnalyzer();
		
		if(retry==null){
			result.setRetryAnalyzer(Retry.class);
		}
		
	}
	
	

}
