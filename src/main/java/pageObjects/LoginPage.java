package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class LoginPage extends base {

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	WebDriver driver;
	By username = By.xpath("//input[@name='uid']");
	By password = By.xpath("//input[@name='password']");
	By loginbtn = By.xpath("//input[@name='btnLogin']");

	// By.id: txtUsername

	public WebElement getUsername() {
		return driver.findElement(username);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getLoginbtn() {
		return driver.findElement(loginbtn);
	}

	public void LoginProcess() throws InterruptedException {

		getUsername().sendKeys(prop.getProperty("username"));
		getPassword().sendKeys(prop.getProperty("password"));
		getLoginbtn().click();
	}

	public boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 0 /* timeout in seconds */);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	public boolean isSuccessfullLogin() {
		String expectedTitle = "Guru99 Bank Manager HomePage";
		String actualPageTitle = driver.getTitle();
		System.out.println(actualPageTitle);
		if (expectedTitle.equalsIgnoreCase(actualPageTitle)) {
			System.out.println("Successfull Login !!");
			return true;
		} else {
			System.out.println("INVALID Login !!");
			return false;
		}
			
	}

}