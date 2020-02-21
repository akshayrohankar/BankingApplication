package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Xls_Reader;
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
	

	public void ValidateSuccessfullLogin() {
		System.out.println(driver.getTitle());
		String actualPageTitle = driver.getTitle();
		System.out.println(actualPageTitle);
		if(prop.getProperty("LoginPageTitle") == actualPageTitle) {
			System.out.println("Page Title Matches !!!");
		}else {
			System.out.println("DONOT MATCH WITH EXPECTED TITLE");
		}
	
		
	}


}