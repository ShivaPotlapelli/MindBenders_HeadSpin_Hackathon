package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class BookingPage extends TestBase {
    private static final By hotel = By.cssSelector("[href*='hotels']");
    private static final By city = By.cssSelector("[data-cy='city']");
    private static final By enterCity = By.className("react-autosuggest__input");
    private static final By dropDownPick = By.cssSelector("[id='react-autowhatever-1-section-0-item-0']");
    private static final By checkin = By.cssSelector("[data-cy='checkin']");
    private static final By datepicker = By.className("DayPicker-Day");

    public void Booking(String area, String month, String day) {
        clickElement(hotel);
        clickElement(city);
        sendText(enterCity, area);
        clickElement(dropDownPick);
        clickElement(checkin);
        clickElement(checkin);
        datePicker(datepicker, month, day);
    }
}
