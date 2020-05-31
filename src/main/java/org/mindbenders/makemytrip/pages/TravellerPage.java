package org.mindbenders.makemytrip.pages;
import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.By;

public class TravellerPage extends TestBase {
    private static final By firstName = By.id("fName");
    private static final By lastName = By.id("lName");
    private static final By mobileInfo = By.id("mNo");
    private static final By smokingRoom = By.id("101");
    private static final By lateCheckIn = By.id("102");
    private static final By mmtFoundationCheckbox = By.id("donation");
    private static final By payNow = By.xpath("//div[contains(text(),'Pay Now')]");

    public void travellerInfo(String fname, String lname, String Phone) {
        sendText(firstName, fname);
        sendText(lastName, lname);
        sendText(mobileInfo, Phone);
        clickElement(smokingRoom);
        clickElement(lateCheckIn);
        clickElement(mmtFoundationCheckbox);
        clickElement(payNow);
    }
}
