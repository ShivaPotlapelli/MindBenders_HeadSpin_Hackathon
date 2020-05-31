package org.mindbenders.makemytrip;

import org.mindbenders.makemytrip.base.TestBase;
import org.mindbenders.makemytrip.pages.BookingPage;
import org.mindbenders.makemytrip.pages.LoginPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MakeMyTrip extends TestBase {

    BookingPage bookingPage;
    LoginPage loginPage;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        init();
        loginPage = new LoginPage();
        bookingPage = new BookingPage();
    }
    
    @BeforeTest(alwaysRun = true)
    public void loginAdnVerify() throws InterruptedException {
        loginPage.userLogin(properties.getProperty("UserName"), properties.getProperty("Password"));
    }

    @Test
    public void bookHotel() {
    bookingPage.Booking(properties.getProperty("city"),properties.getProperty("checkInMonth"),properties.getProperty("checkInDay"),properties.getProperty("checkOutMonth"),properties.getProperty("checkOutDay"));
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        tearDownMain();
    }
}
