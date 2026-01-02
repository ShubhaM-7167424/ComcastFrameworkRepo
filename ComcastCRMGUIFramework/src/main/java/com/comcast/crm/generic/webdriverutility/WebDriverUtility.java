package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public WebElement waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchTabUsingUrl(WebDriver driver, String partialUrl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			String windowId = iterator.next();
			driver.switchTo().window(windowId);
			
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl.contains(partialUrl)) {
				break;
			}
		}
	}
		
		public void switchTabUsingTitle(WebDriver driver, String partialTitle) {
			Set<String> set = driver.getWindowHandles();
			Iterator<String> iterator = set.iterator();
			
			while (iterator.hasNext()) {
				String windowId = iterator.next();
				driver.switchTo().window(windowId);
				
				String title = driver.getTitle();
				if (title.contains(partialTitle)) {
					break;
			}
		}		
	}
		
		public void switchtoFrame(WebDriver driver, int index) {
			driver.switchTo().frame(index);
		}
		
		public void switchtoFrame(WebDriver driver, String nameID) {
			driver.switchTo().frame(nameID);
		}
		
		public void switchtoFrame(WebDriver driver, WebElement element) {
			driver.switchTo().frame(element);
		}
		
		public void switchtoAlertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
		}		
		public void switchtoAlertAndCancel(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}
		
		public void select(WebElement element, int index) {
			Select select = new Select(element);
			select.selectByIndex(index);
		}
		
		public void select(WebElement element, String text) {
			Select select = new Select(element);
			select.selectByVisibleText(text);
		}
		public void mouseHoverOnElement(WebDriver driver, WebElement element) {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
		}
		public void doubleClick(WebDriver driver , WebElement element) {
			Actions actions = new Actions(driver);
			actions.doubleClick(element).perform();			
		}
}
