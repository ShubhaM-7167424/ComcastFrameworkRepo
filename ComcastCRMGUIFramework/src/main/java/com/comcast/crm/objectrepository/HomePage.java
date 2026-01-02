package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy (linkText = "Contacts") 
	private WebElement contactLink;
	
	@FindBy (linkText = "Campaigns")
	private WebElement campaginLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy (xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy (linkText = "Sign Out")
	private WebElement signOutLink;
	
	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaginLink() {
		return campaginLink;
	}
	
	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	public void goToCampaignsPage() {
		Actions actions = new Actions(driver);
		actions.moveToElement(moreLink).perform();
		campaginLink.click();
	}

	public void logout() {
		Actions actions = new Actions(driver);
		WebElement img = waitForElementPresent(driver, adminImg);
		actions.moveToElement(img).perform();
		waitForElementPresent(driver, signOutLink).click();		
	}	
}
