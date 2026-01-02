package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;
	
	@FindBy (name = "search_text")
	private WebElement searchTextEdt;
	
	@FindBy (name = "search_field")
	private WebElement searchFieldDD;
	
	@FindBy (name = "submit")
	private WebElement searchBtn;
	
	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}

	public WebElement getSearchTextEdt() {
		return searchTextEdt;
	}

	public WebElement getSearchFieldDD() {
		return searchFieldDD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
}
