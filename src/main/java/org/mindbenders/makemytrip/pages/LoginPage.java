package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

	@FindBy(css = "[data-cy='account']")
	public WebElement accountBtn;

	@FindBy(id = "username")
	public WebElement userName;

	@FindBy(xpath = "//span[contains(text(),'Continue')]")
	public WebElement continueBtn;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(xpath = "//button[@class='capText font16']//span[contains(text(),'Login')]")
	public WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void userLogin(String Name, String pass) throws InterruptedException {
		accountBtn.click();
		userName.sendKeys(Name);
		try {
			if (continueBtn.isEnabled()) {
				Actions actions = new Actions(driver);
				actions.doubleClick(continueBtn).perform();
			}
		} catch (Exception e) {
			System.out.println("Continue is not Enabled" + e);
		}
		password.sendKeys(pass);
		try {
			if (loginBtn.isEnabled()) {
				loginBtn.click();
			}
		} catch (Exception e) {
			System.out.println("Login button is not Enabled" + e);
		}
	}
}
