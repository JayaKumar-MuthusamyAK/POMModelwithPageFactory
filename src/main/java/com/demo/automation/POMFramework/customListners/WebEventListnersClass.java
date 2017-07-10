package com.demo.automation.POMFramework.customListners;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebEventListnersClass implements WebDriverEventListener  {

	
	public static final Logger log = Logger.getLogger(WebEventListnersClass.class.getName());
	
	public void afterAlertAccept(WebDriver arg0) {
		
		
	}

	public void afterAlertDismiss(WebDriver arg0) {
		
		
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		
		
	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		
		log.info("Clicked on the element of "+arg0.toString());
		
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
	
		
	}

	public void afterNavigateBack(WebDriver arg0) {
	
		
	}

	public void afterNavigateForward(WebDriver arg0) {
		
		
	}

	public void afterNavigateRefresh(WebDriver arg0) {
	
		
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		
		
	}

	public void afterScript(String arg0, WebDriver arg1) {
		
		
	}

	public void beforeAlertAccept(WebDriver arg0) {

		
	}

	public void beforeAlertDismiss(WebDriver arg0) {
	
		
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		
		
	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		log.info("Trying to click on the element of "+arg0.toString());
		
	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		
		
	}

	public void beforeNavigateBack(WebDriver arg0) {
		
	}

	public void beforeNavigateForward(WebDriver arg0) {
		
	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		
	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		
	}

	public void onException(Throwable arg0, WebDriver arg1) {
	
		
	}

}
