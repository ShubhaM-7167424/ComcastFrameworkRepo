package com.comcast.crm.contacttest;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.ContactInfoPage;
import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.CreateNewContactPage;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganizationInfoPage;
import com.comcast.crm.objectrepository.OrganizationsPage;

public class CreateContactTest extends BaseClass {
//	1st test script
	@Test (groups = "smokeTest")
	public void createContactTest() throws Throwable {
//		read test script data from Excel file		
		String lastName = excelUtility.getDataFromExcel("contact", 1, 2) + javaUtility.getRandomNumber();

//		  step 2 : Navigate to Contacts module
		HomePage homePage = new HomePage(driver);
		homePage.getContactLink().click();

//		  step 3 : Click on "create new Contact" button
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateNewContactBtn().click();

//		  step 4 : enter all the details & create new Contact
		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.createContact(lastName);

//		  Verify Contact name is present in header message
		ContactInfoPage contactInfoPage = new ContactInfoPage(driver);
		String headerMsg = contactInfoPage.getHeaderMsg().getText();
		boolean status = headerMsg.contains(lastName);
		Assert.assertEquals(status, true);
		
//		convert all the if-else statement using assert
//		  Verify Contact name is present in last name field
		String actualLastName = contactInfoPage.getGetLastName().getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualLastName, lastName);
		
	}
	
	
//	2nd test script
	@Test (groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {
//		read test script data from Excel file	
		String lastName = excelUtility.getDataFromExcel("contact", 4, 2) + javaUtility.getRandomNumber();

//		  step 2 : Navigate to Contacts module
		HomePage homePage = new HomePage(driver);
		homePage.getContactLink().click();

//		  step 3 : Click on "create Contact" button
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateNewContactBtn().click();

//		  step 4 : enter all the details & create new Organization
		String startDate = javaUtility.getSystemDateYYYYMMDD();
		String endDate = javaUtility.getRequiredDateYYYYMMDD(30);

		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.createContact(lastName, startDate, endDate);

//		  step 5 : Verify start date
		String actualStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actualStartDate.equals(startDate)) {
			System.out.println(startDate + " startDate is verified==PASS");
		} else {
			System.out.println(startDate + " startDate is not verified==FAIL");
		}

//		  Verify end date
		String actualEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actualEndDate.equals(endDate)) {
			System.out.println(endDate + " endDate is verified==PASS");
		} else {
			System.out.println(endDate + " endDate is not verified==FAIL");
		}
	}
	
	
//	3rd test script
	@Test (groups = "regressionTest")
	public void createContactWithOrgTest() throws InterruptedException, Throwable {
//		precondition - create an organization

//		read test script data from Excel file		
		String orgName = excelUtility.getDataFromExcel("org", 1, 2) + javaUtility.getRandomNumber();
		String shippingAddress = excelUtility.getDataFromExcel("org", 1, 3) + javaUtility.getRandomNumber();

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
		} else {
			System.out.println(orgName + " name is not verified=FAIL");
		}

//		  create a contact and select the organization name also from the lookup table

//			read test script data from Excel file			
		String lastName = excelUtility.getDataFromExcel("contact", 1, 2) + javaUtility.getRandomNumber();

//			  step 2 : Navigate to Contacts module
		HomePage homePage = new HomePage(driver);
		homePage.getContactLink().click();

//			  step 3 : Click on "create Contact" button
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateNewContactBtn().click();

//			  step 4 : enter all the details & create new Organization
		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.getLastNameEdt().sendKeys(lastName);
		createNewContactPage.getLookupBtn().click();

//			  switch to child window
		webDriverUtility.switchTabUsingUrl(driver, "module=Accounts");
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.getSearchEdt().sendKeys(orgName);
		organizationsPage.getSearchBtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

//			  switch to parent window
		webDriverUtility.switchTabUsingUrl(driver, "module=Contacts");

		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		createNewOrganizationPage.getSaveBtn().click();

//			  step 5 : Verify last name is present in the header info
		String headerInfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo1.contains(lastName)) {
			System.out.println(lastName + " lastName is present in header info==PASS");
		} else {
			System.out.println(lastName + " lastName is NOT present in header info==FAIL");
		}

//			  Verify Contact name is correct in the contact information
		String actualOrgName1 = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgName1);
		if (actualOrgName1.trim().equals(orgName)) {
			System.out.println(orgName + " orgName is created==PASS");
		} else {
			System.out.println(orgName + " orgName is not created==FAIL");
		}
	}	
}
