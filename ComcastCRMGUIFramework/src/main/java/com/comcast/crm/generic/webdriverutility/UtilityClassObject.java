package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
//	ThreadLocal is used to store WebDriver and ExtentTest instances per thread so
//	that parallel tests do not overwrite each other and cross-browser execution works safely.
	
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
//	this class helps us to share my static variable for multiple threads in case of parallel execution
//	i have written class utility for sharing my static variable
	public static ExtentTest getTest() {
		return test.get();
	}
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}
	
}
