package testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import resources.Xls_Reader;
import resources.base;

public class ParametererizedDataLoginPageTest extends base {
	
	 

	@BeforeTest
	public void initMe() throws IOException {
		initializeDriver();
	}

	@Test(priority = 1)
	public void testLoginPage() throws InterruptedException {

		LoginPage objLoginPage = new LoginPage(driver);
		
		Xls_Reader reader = new Xls_Reader("E:\\Eclipse Workspace\\Guru99_BankingApplication\\Guru99.BankingApplication\\TestData\\testData.xlsx");

		int countRow = reader.getRowCount("Banking");

		for (int i = 2; i < countRow; i++) {

			objLoginPage.getUsername().sendKeys(reader.getCellData("Banking", "Username", i));
			objLoginPage.getPassword().sendKeys(reader.getCellData("Banking", "Password", i));
			objLoginPage.getLoginbtn().click();
			
			
			WebDriverWait wait = new WebDriverWait(driver, 0);
			if(wait.until(ExpectedConditions.alertIsPresent()) != null) {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}else {
				driver.navigate().back();
			}
		
		}
	}
	

	@AfterTest
	public void closeMe() throws IOException {
		driver.quit();
	}

}
