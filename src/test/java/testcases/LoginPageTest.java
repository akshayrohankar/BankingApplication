package testcases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import resources.base;

public class LoginPageTest extends base {
	
	 

	@BeforeTest
	public void initMe() throws IOException {
		initializeDriver();
	}

	@Test(priority = 1)
	public void testLoginPage() throws InterruptedException {

		LoginPage objLoginPage = new LoginPage(driver);
		objLoginPage.LoginProcess();
		objLoginPage.ValidateSuccessfullLogin();
	}
	

	@AfterTest
	public void closeMe() throws IOException {
		driver.quit();
	}

}
