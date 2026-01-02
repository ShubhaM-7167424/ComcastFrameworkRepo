package com.comcast.crm.generic.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementation  implements ITestListener, ISuiteListener  {

	
	public  ExtentReports reports;
	public static ExtentTest test;  
//	this line is not advisible for cross browser testing, distributed testing as it has static 
	
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		
		String time  = new Date().toString().replace(" ", "_").replace(":", "_");
//		spark report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReport/"+time+"sample.html");
		spark.config().setDocumentTitle("CRM test suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

//		add Environment information and create test
		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("BROWSER", "chrome");
		reports.setSystemInfo("OS", "windows-11");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		reports.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("======" +result.getMethod().getMethodName() + "====START==");
		test = reports.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "===> STARTED <===");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("======" +result.getMethod().getMethodName() + "====END==");
		test.log(Status.PASS, result.getMethod().getMethodName() + "===> COMPLETED <===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot takesScreenshot = (TakesScreenshot)BaseClass.sdriver;
		String base64Screenshot  = takesScreenshot.getScreenshotAs(OutputType.BASE64);	
		String time  = new Date().toString().replace(" ", "_").replace(":", "_");
//		assume in 1 test case we have multiple failures, if we use only testName, it will overwrite the same 
//		variable again and again, so to avoid that we attach time with it, so we get all the screenshot of the failure
		test.addScreenCaptureFromBase64String(base64Screenshot , testName + "_" + time);	
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===> FAILED <===");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
