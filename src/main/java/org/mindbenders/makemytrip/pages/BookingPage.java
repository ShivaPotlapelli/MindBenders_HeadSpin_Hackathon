package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.support.PageFactory;

public class BookingPage extends TestBase {

    public BookingPage() {
        PageFactory.initElements(driver, this);
    }
}
