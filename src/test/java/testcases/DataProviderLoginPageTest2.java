package testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import resources.Xls_Reader;
import resources.base;

public class DataProviderLoginPageTest2 extends base {

	@BeforeTest
	public void initMe() throws IOException {
		initializeDriver();
	}

	@Test(priority = 1, dataProvider = "LoginData")
	public void testLoginPage(String username, String password) throws InterruptedException {

		LoginPage objLoginPage = new LoginPage(driver);

		objLoginPage.getUsername().sendKeys(username);
		objLoginPage.getPassword().sendKeys(password);
		objLoginPage.getLoginbtn().click();

		if (objLoginPage.isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} else {
			driver.navigate().back();
		}

	}


	@Test(priority = 2)
	public void verifyManagerId() {

	
		WebElement mngrId = driver.findElement(By.xpath("//td[contains(text(),'Manger Id : mngr247076')]"));
		String msg = mngrId.getText();

		System.out.println(msg);
	}
	// td[contains(text(),'Manger Id : mngr247076')]

	@DataProvider(name = "LoginData")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "mngr247076", "seqUqaq" }, { "mngr247077", "seqUqaq" }, { "mngr247076", "seqUqar" },
				{ "mngr247079", "seqUqar" } };

	}

	@AfterTest
	public void closeMe() throws IOException {
		driver.quit();
	}

}