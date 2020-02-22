package testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.Xls_Reader;
import resources.base;
import testUtils.Util;

public class Day05WithSceenshot extends base {

	@BeforeTest
	public void initMe() throws IOException {
		initializeDriver();
	}

	@Test
	public void testCase05() throws Exception {

		String actualBoxMsg;

		LoginPage objLoginPage = new LoginPage(driver);

		Xls_Reader reader = new Xls_Reader(prop.getProperty("testDataExcelSheetPath"));

		int countRow = reader.getRowCount("Banking");

		for (int i = 2; i < countRow; i++) {

			objLoginPage.getUsername().sendKeys(reader.getCellData("Banking", "Username", i));
			objLoginPage.getPassword().sendKeys(reader.getCellData("Banking", "Password", i));
			objLoginPage.getLoginbtn().click();

			try {

				Alert alert = driver.switchTo().alert();
				actualBoxMsg = alert.getText(); // get content of the Alter Message
				alert.accept();
				// Compare Error Text with Expected Error Value
				assertEquals(actualBoxMsg, Util.EXPECT_ERROR);
				driver.get(prop.getProperty("url"));

			} catch (NoAlertPresentException Ex) {
				// Get text displayes on login page

				Util.getScreenshot("ValidLogin");
				HomePage homepage = new HomePage(driver);
				homepage.verifyManagerId();
				homepage.getLogoutBtn().click();

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alt = driver.switchTo().alert();
				alt.accept();
				driver.get(prop.getProperty("url"));

			}
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}
}