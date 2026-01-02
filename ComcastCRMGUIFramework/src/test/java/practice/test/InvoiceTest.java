package practice.test;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


public class InvoiceTest extends BaseClass {
	@Test (retryAnalyzer = com.comcast.crm.generic.listenerutility.RetryListenerImplementation.class)
	public void activateSim() {
		System.out.println("execute createInvoiceTest");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");		
	}
	
	
	
}
