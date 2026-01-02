package com.comcast.crm.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (name = "accountname")
	private WebElement orgNameEdt;

	@FindBy (name = "ship_street")
	private WebElement shipAddrEdt;
	
	@FindBy (xpath = "(//input[@title='Save [Alt+S]'])")
	private WebElement saveBtn;
	
	@FindBy (name = "industry")
	private WebElement industryDD;

	@FindBy (name = "accounttype")
	private WebElement typeDD;
	
	@FindBy (name = "phone")
	private WebElement phoneEdt;
	
	public WebElement getTypeDD() {
		return typeDD;
	}
	
	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getShipAddrEdt() {
		return shipAddrEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
//	Business logic
	public void createOrg(String orgName, String shipAddress) {
		orgNameEdt.sendKeys(orgName);
		shipAddrEdt.sendKeys(shipAddress);
		saveBtn.click();	
	}
	
	public void createOrg(String orgName, String shipAddress, String phoneNumber) {
		orgNameEdt.sendKeys(orgName);
		shipAddrEdt.sendKeys(shipAddress);
		phoneEdt.sendKeys(phoneNumber);
		saveBtn.click();	
	}
	
	public void createOrg(String orgName, String shipAddress, String industry, String type) {
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		orgNameEdt.sendKeys(orgName);
		shipAddrEdt.sendKeys(shipAddress);
		select(createNewOrganizationPage.getIndustryDD(), industry);
		select(createNewOrganizationPage.getTypeDD(), type);		
		saveBtn.click();	
	}
}
