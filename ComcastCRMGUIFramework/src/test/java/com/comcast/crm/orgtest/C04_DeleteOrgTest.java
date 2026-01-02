package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganizationInfoPage;
import com.comcast.crm.objectrepository.OrganizationsPage;

public class C04_DeleteOrgTest {

	public static void main(String[] args) throws Throwable {
		
//		1. create an organization		
		
//		create FileUtility object to read common data and ExcelUtiltiy object to read excel data			
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");		
		
//		read testscript data from Excel file		
		String orgName = eLib.getDataFromExcel("org", 10 , 2) + jLib.getRandomNumber();
		String shippingAddress = eLib.getDataFromExcel("org", 10 , 5) + jLib.getRandomNumber();		
				
//		polymorphism program
		  WebDriver driver = null;		  
		  if(BROWSER.equals("chrome"))
		  {
			  driver = new ChromeDriver();
		  }else if(BROWSER.equals("firefox"))
		  {
			  driver = new FirefoxDriver();
		  }else if (BROWSER.equals("edge")) {
			driver = new FirefoxDriver();
		  }else
		  {
			  driver = new ChromeDriver();
		  }		  		 
		  
//		  step 1 : Login to vtiger app
		  wLib.waitForPageToLoad(driver);
		  driver.get(URL);	
		  
		  LoginPage lp = new LoginPage(driver);
		  lp.loginToApp(URL,USERNAME, PASSWORD );
 
//		  step 2 : Navigate to Organization module
		  HomePage hp = new HomePage(driver);
		  hp.getOrgLink().click();
		  
//		  step 3 : Click on "create Organization" button
		  OrganizationsPage op = new OrganizationsPage(driver);
		  op.getCreateNewOrgBtn().click();
		  
//		  step 4 : enter all the details & create new Organization
		  CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		  cp.createOrg(orgName, shippingAddress);
		 
//		  step 5 : Verify Organization name is present in the header msg
		  OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		  String actualOrgName = oip.getHeaderMsg().getText();
		  if (actualOrgName.contains(orgName)) {
			  System.out.println(orgName + " name is verified=PASS");			
		  }else {
			  System.out.println(orgName + " name is not verified=FAIL");
		  }
		
		  Thread.sleep(1000);
//		  2. goback to organizations page
		  hp.getOrgLink().click();
		  
		  Thread.sleep(1000);
//		  3. search for organization
		  op.getSearchEdt().sendKeys(orgName);
		  wLib.select(op.getSearchDD(), "Organization Name");
		  op.getSearchBtn().click();
		  		  
//		  4. delete the created orgName
		  driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]//a[text()='del']")).click();
		   
//		  step 6 : logout	
//		  hp.logout();
//		  
//		  Thread.sleep(2000);
//		  driver.quit();
	}
}
