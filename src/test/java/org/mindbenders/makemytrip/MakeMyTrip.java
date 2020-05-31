package org.mindbenders.makemytrip;

import org.mindbenders.makemytrip.base.TestBase;
import org.mindbenders.makemytrip.pages.BookingPage;
import org.mindbenders.makemytrip.pages.BookingSummary;
import org.mindbenders.makemytrip.pages.LoginPage;
import org.mindbenders.makemytrip.pages.TravellerPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MakeMyTrip extends TestBase {

    BookingPage bookingPage;
    LoginPage loginPage;
    TravellerPage travellerPage;
    BookingSummary bookingSum;


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        init();
        loginPage = new LoginPage();
        bookingPage = new BookingPage();
        travellerPage = new TravellerPage();
        bookingSum = new BookingSummary();
    }
    
    @BeforeTest(alwaysRun = true)
    public void loginAdnVerify() {
        loginPage.userLogin(properties.getProperty("UserName"), properties.getProperty("Password"));
    }

    @Test
    public void bookHotel() {
    bookingPage.Booking(properties.getProperty("city"),properties.getProperty("checkInMonth"),properties.getProperty("checkInDay"),properties.getProperty("checkOutMonth"),properties.getProperty("checkOutDay"));

    }
    
    @Test
    public void travellerDetails() {
    	travellerPage.travellerInfo(properties.getProperty("FirstName"), properties.getProperty("LastName"), properties.getProperty("Phone"));
    }
    
    @Test
    public void verifyDetails(){
        bookingSum.verifyHotelName(properties.getProperty("hotel"));
        bookingSum.verifyTravellerDetails(properties.getProperty("FirstName"),properties.getProperty("LastName"),properties.getProperty("UserName"),properties.getProperty("Phone"));
        bookingSum.verifyDates(properties.getProperty("checkInDay"),properties.getProperty("checkInMonth"),properties.getProperty("checkOutDay"),properties.getProperty("checkOutMonth"));
    }

   

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        tearDownMain();
    }
}
