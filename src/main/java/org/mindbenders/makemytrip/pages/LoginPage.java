package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
}
