package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import static org.mindbenders.makemytrip.utilities.TestCommonUtils.takeScreenShot;

public class BookingPage extends TestBase {
	private static final By hotel = By.cssSelector("[href*='hotels']");
	private static final By city = By.cssSelector("[data-cy='city']");
	private static final By enterCity = By.className("react-autosuggest__input");
	private static final By dropDownPick = By.cssSelector("[id='react-autowhatever-1-section-0-item-0']");
	private static final By checkin = By.cssSelector("[data-cy='checkin']");
	private static final By datepicker = By.className("DayPicker-Day");
	private static final By guest = By.cssSelector("[data-cy='guest']");
	private static final By roomMembers = By.cssSelector(".guestCounter >li");
	private static final By addRoom = By.cssSelector("[data-cy='addAnotherRoom']");
	private static final By apply = By.cssSelector("[data-cy='submitGuest']");
	private static final By travellingFor = By.cssSelector("[data-cy='travelForText']");
	private static final By travellingForWork = By.cssSelector("[data-cy='travelFor-Work']");
	private static final By search = By.cssSelector("[data-cy='submit']");
	private static final By mapRemoval = By.cssSelector("[data-cy='landingContainer']");
	private static final By userRating = By
			.xpath("//ul[@class='filterList']//label[contains(text(),'4.5 & above (Excellent)')]");
	private static final By fifthHotel = By.cssSelector("[class='makeFlex spaceBetween']");
	private static final By roomSelect = By.xpath("//a[contains(@class,'primaryBtn appendBottom15')]");


	public void Booking(String area, String checkInMonth, String checkInDay, String checkOutMonth, String checkOutDay) {
		clickElement(hotel);
		// select city
		clickElement(city);
		sendText(enterCity, area);
		delay();
		clickElement(dropDownPick);
		// checkin date
		clickElement(checkin);
		datePicker(datepicker, checkInMonth, checkInDay);
		// checkout date
		datePicker(datepicker, checkOutMonth, checkOutDay);
		// select Adult Guest
		clickElement(guest);
		guestSelect(roomMembers, "adults-2");
		// select Children Guest
		guestSelect(roomMembers, "children-2");
		// addRoom
		clickElement(addRoom);
		// select Adult Guest
		guestSelect(roomMembers, "adults-2");
		// select Children Guest
		guestSelect(roomMembers, "children-2");
		clickElement(apply);
		clickElement(travellingFor);
		clickElement(travellingForWork);
		clickElement(search);
		clickElement(mapRemoval);
		slide(driver);
		clickElement(userRating);
		delay();
		hotelRoom(fifthHotel,5);
		takeScreenShot(driver);
		switchWindow();
		clickElement(roomSelect);	
	}
}
