package org.mindbenders.makemytrip.pages;
import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class TravellerPage extends TestBase {
    private static final By firstName = By.id("fName");
    private static final By lastName = By.id("lName");
    private static final By mobileInfo = By.id("mNo");
    private static final By smokingRoom = By.xpath("//label[contains(text(),'Smoking room')]");
    private static final By lateCheckIn = By.xpath("//label[contains(text(),'Late check-in')]");
    private static final By mmtFoundationCheckbox = By.xpath("//p[@class='makeFlex hrtlCenter font12 appendBottom15']//label[1]");
    private static final By payNow = By.xpath("//div[contains(text(),'Pay Now')]");

    public void travellerInfo(String fname, String lname, String Phone) {
        sendText(firstName, fname);
        sendText(lastName, lname);
        sendText(mobileInfo, Phone);
        delay();
        clickElement(smokingRoom);
        clickElement(lateCheckIn);
        ((JavascriptExecutor)driver).executeScript("scroll(0,document.body.scrollHeight)");
        clickElement(mmtFoundationCheckbox);
        clickElement(payNow);
    }
}
