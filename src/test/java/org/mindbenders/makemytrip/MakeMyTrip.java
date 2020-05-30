package org.mindbenders.makemytrip;

import org.mindbenders.makemytrip.base.TestBase;
import org.mindbenders.makemytrip.pages.BookingPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class MakeMyTrip extends TestBase {

    BookingPage bookingPage;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        init();
        bookingPage = new BookingPage();
    }

    @Test
    public void yourTestCase() {
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        tearDownMain();
    }
}
