package pageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.base;

public class HomePage extends base {

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	WebDriver driver;
	By logoutBtn = By.xpath("//a[contains(text(),'Log out')]");
	By loginId = By.xpath("//tr[@class='heading3']");

	public WebElement getLogoutBtn() {
		return driver.findElement(logoutBtn);
	}

	public WebElement getLoginId() {
		return driver.findElement(loginId);
	}

	public void verifyManagerId() {

		int ExpectedId = 247076;
		String input = getLoginId().getText();

		Pattern pattern = Pattern.compile("mngr");

		Matcher matcher = pattern.matcher(input);

		while (matcher.find())
			System.out.println("Pattern found from " + matcher.start() + " to " + (matcher.end() - 1));

		int id = Integer.parseInt(input.substring(16));

		System.out.println("Actual Id: " + id);

		if (id == ExpectedId) {
			System.out.println("Valid Manager Id !!");
		} else
			System.out.println("Invalid Id !");
	}
}
