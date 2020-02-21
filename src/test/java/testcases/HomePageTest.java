package testcases;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.base;

public class HomePageTest extends base {
	
	 

	@BeforeTest
	public void initMe() throws IOException {
		initializeDriver();
	}

	@Test(priority = 1)
	public void testLoginPage() throws InterruptedException {

		LoginPage objLoginPage = new LoginPage(driver);
		objLoginPage.LoginProcess();
		if(objLoginPage.isSuccessfullLogin()) {
			System.out.println("Successfull Login !!");
		}else
			System.out.println("Invalid Credentils !!");
	}
	
	@Test(priority = 2)
	public void verifyLoginId() throws InterruptedException {

		HomePage homepage = new HomePage(driver);
		homepage.verifyManagerId();
	   
	}

	@AfterTest
	public void closeMe() throws IOException {
		driver.quit();
	}

}
