package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWithPom {
	
	@FindBy (name="user_name")
	WebElement ele1;
	
	@FindBy (name="user_password")
	WebElement ele2;
	
  	
	/*
	 * @FindAll({
	 * 
	 * @FindBy(xpath = "(//input[@value='Login'])[2]"),
	 * 
	 * @FindBy(id = "submitButton") })
	 */	
	@FindAll({
		
		@FindBy(xpath = "(//input[@value='Login'])[2]"),
		@FindBy(id = "submitButton")
		
	})	
  	WebElement ele3;
	
	@Test
	public void Sample() throws Throwable {
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		driver.manage().window().maximize();
		SampleTestWithPom s = PageFactory.initElements(driver, SampleTestWithPom.class);
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");	
		s.ele3.click();
		
		Thread.sleep(1000);
		driver.quit();
	}
}
