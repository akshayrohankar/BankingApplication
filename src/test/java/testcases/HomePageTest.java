package testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.Xls_Reader;
import resources.base;

public class HomePageTest extends base {

	@BeforeTest
	public void initMe() throws IOException {
		initializeDriver();
	}

	@Test
	public void testLoginPage() throws InterruptedException {

		LoginPage objLoginPage = new LoginPage(driver);

		Xls_Reader reader = new Xls_Reader(prop.getProperty("testDataExcelSheetPath"));

		int countRow = reader.getRowCount("Banking");

		for (int i = 2; i < countRow; i++) {

			objLoginPage.getUsername().sendKeys(reader.getCellData("Banking", "Username", i));
			objLoginPage.getPassword().sendKeys(reader.getCellData("Banking", "Password", i));
			objLoginPage.getLoginbtn().click();

			HomePage homepage = new HomePage(driver);

			if (objLoginPage.isAlertPresent()) {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} else {
				Thread.sleep(3000);
				homepage.getLogoutBtn().click();
				Thread.sleep(3000);
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Thread.sleep(3000);
			}

			Assert.assertTrue(objLoginPage.isSuccessfullLogin());

			homepage.verifyManagerId();

		}

		/*
		 * objLoginPage.getUsername().sendKeys(username);
		 * objLoginPage.getPassword().sendKeys(password);
		 * objLoginPage.getLoginbtn().click();
		 * 
		 * 
		 * if (objLoginPage.isSuccessfullLogin()) {
		 * System.out.println("Successfull Login !!"); } else {
		 * System.out.println("Invalid Credentils !!");
		 * 
		 * }
		 */

	}

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
