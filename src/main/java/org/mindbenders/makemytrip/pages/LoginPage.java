package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.By;

public class LoginPage extends TestBase {

	private static final By userName= By.id("username");
	private static final By password= By.id("password");
	private static final By continueBtn= By.cssSelector("[data-cy='continueBtn']");
	private static final By accountBtn= By.cssSelector("[data-cy='account']");
	private static final By loginBtn= By.cssSelector("[data-cy='login']");
	private static final By closeModal= By.cssSelector("[data-cy='modalClose']");


	public void userLogin(String Name, String Password) {
        clickElement(accountBtn);
        sendText(userName, Name);
        clickElement(continueBtn);
        doubleClick(continueBtn);
        sendText(password, Password);
        clickElement(loginBtn);
        doubleClick(loginBtn);
        clickElement(closeModal);
    }
}

