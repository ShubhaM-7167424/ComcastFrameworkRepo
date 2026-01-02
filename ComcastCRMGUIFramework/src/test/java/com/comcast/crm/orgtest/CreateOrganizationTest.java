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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.listenerutility.ListenerImplementation;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganizationInfoPage;
import com.comcast.crm.objectrepository.OrganizationsPage;

@Listeners(com.comcast.crm.generic.listenerutility.ListenerImplementation.class)
public class CreateOrganizationTest extends BaseClass {
//	test 1
	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws IOException, Throwable {

//		read test script data from Excel file		
 		UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
		String orgName = excelUtility.getDataFromExcel("org", 1, 2) + javaUtility.getRandomNumber() ;
		String shippingAddress = excelUtility.getDataFromExcel("org", 1, 3) + javaUtility.getRandomNumber();

//		  step 2 : Navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		HomePage homePage = new HomePage(driver);
		homePage.getOrgLink().click();

//		  step 3 : Click on "create Organization" button
		UtilityClassObject.getTest().log(Status.INFO, "click on create org button");
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.getCreateNewOrgBtn().click();

//		  step 4 : enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "enter all org details");
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		createNewOrganizationPage.createOrg(orgName, shippingAddress);

		UtilityClassObject.getTest().log(Status.INFO, orgName + "===> created a new org");
//		  step 5 : Verify Organization name is present in the header msg
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actualOrgName = organizationInfoPage.getHeaderMsg().getText();
//		Assert.assertEquals(true, actualOrgName.contains(orgName));
		Assert.assertTrue(actualOrgName.contains(orgName));
		
	}

//	test 2 
	@Test (groups = "regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws Throwable {
//		read test script data from Excel file		
		String orgName = excelUtility.getDataFromExcel("org", 7, 2) + javaUtility.getRandomNumber();
		String shippingAddress = excelUtility.getDataFromExcel("org", 7, 5) + javaUtility.getRandomNumber();
		String phoneNumber = excelUtility.getDataFromExcel("org", 7, 3);

//		  step 2 : Navigate to Organization module
		HomePage homePage = new HomePage(driver);
		homePage.getOrgLink().click();

//		  step 3 : Click on "create Organization" button
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.getCreateNewOrgBtn().click();

//		  step 4 : enter all the details & create new Organization
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		createNewOrganizationPage.createOrg(orgName, shippingAddress, phoneNumber);

//		  verify phone number
		String actualPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actualPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " phoneNumber is verified==PASS");
		} else {
			System.out.println(phoneNumber + " phoneNumber is not verified==FAIL");
		}
	}

//	test 3
	@Test (groups = "regressionTest")
	public void createOrganizationWithIndustryAndTypeTest() throws Throwable {
//		read test script data from Excel file		
		String orgName = excelUtility.getDataFromExcel("org", 4, 2) + javaUtility.getRandomNumber();
		String shippingAddress = excelUtility.getDataFromExcel("org", 4, 5) + javaUtility.getRandomNumber();
		String industry = excelUtility.getDataFromExcel("org", 4, 3);
		String type = excelUtility.getDataFromExcel("org", 4, 4);

//		  step 2 : Navigate to Organization module
		HomePage homePage = new HomePage(driver);
		homePage.getOrgLink().click();

//		  step 3 : Click on "create Organization" button
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.getCreateNewOrgBtn().click();

//		  step 4 : enter all the details & create new Organization
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		createNewOrganizationPage.createOrg(orgName, shippingAddress, industry, type);

//		  verify industry and type info
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustry.equals(industry)) {
			System.out.println(industry + " industry is verified==PASS");
		} else {
			System.out.println(industry + " industry is not verified==FAIL");
		}

		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actualType.equals(type)) {
			System.out.println(type + " type is verified==PASS");
		} else {
			System.out.println(type + " type is not verified==FAIL");
		}

	}
}
