package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepository.LoginPage;
/**
 * test class for contact module
 * 
 */
public class SearchContactTest extends BaseClass{
	/**
	 * scenario : login() => navigateContact => createContact() == verify
	 */
	@Test
	public void searchContactTest() {
		/* step1 : login to app */
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp("url", "username", "password");
	}
}
