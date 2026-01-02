package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * Contains login page elements and business library like loginToApp()
 */
public class LoginPage extends WebDriverUtility {  
	
	WebDriver driver;
//	rule-3 object initialization
	public LoginPage(WebDriver driver) {
		this.driver = driver;
//		pagefactory should know on which browser we need to initialize the element
		PageFactory.initElements(driver, this);
	}
//  rule-1  create a separate java class for each webpage		
//	rule-2 object creation (identify all the elements using @FindBy annotation)
//	why are we making these variables private ?
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
//	rule-4 object encapsulation
	
//	rule-5 object utilization	
//	Provide action
	
/**
 * login to application based on url username and password arguments	
 * @param url
 * @param username
 * @param password
 */
	public void loginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
}
