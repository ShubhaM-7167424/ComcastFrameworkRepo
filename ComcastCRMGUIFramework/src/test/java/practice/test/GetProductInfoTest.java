package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
	@Test (dataProvider = "getData")
	public void getProductInfoTest(String brandName, String phoneName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.amazon.in/");
		
//		search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		
//		capture product info
		String x = "//span[contains(text(), '"+phoneName+"')]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] objArr = new Object[3][2];
		
		objArr[0][0] = "iphone";
		objArr[0][1] = "iPhone 17 Pro 512 GB: 15.93 cm ";
		
		objArr[1][0] = "iphone";
		objArr[1][1] = "iPhone Air 256 GB: Thinnest iPhone Ever";
		
		objArr[2][0] = "iphone";
		objArr[2][1] = "iPhone 17 Pro Max 256 GB: 17.42 cm (6.9″)";
		
		return objArr;
	}
}
