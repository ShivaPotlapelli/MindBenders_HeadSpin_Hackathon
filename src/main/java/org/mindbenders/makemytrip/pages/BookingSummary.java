package org.mindbenders.makemytrip.pages;

import org.mindbenders.makemytrip.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;

public class BookingSummary extends TestBase {

    @FindBy(xpath ="//*[@id=\"top_rail_div\"]/div/div[1]/div[1]/div[1]/p[1]")
    public WebElement hotelName;

    @FindBy(xpath ="//*[@id=\"top_rail_div\"]/div/div[1]/div[2]/div[1]/p[2]/span[1]")
    public WebElement checkInDate;

    @FindBy(xpath ="//*[@id=\"top_rail_div\"]/div/div[1]/div[2]/div[2]/p[2]/span[1]")
    public WebElement checkOutDate;

    @FindBy(xpath = "//*[@id=\"top_rail_div\"]/div/div[1]/div[4]/p[1]")
    public WebElement travellerName;

    @FindBy(css =".contact_info")
    public WebElement contactInfo;

    @FindBy(css =".room_info>span:nth-child(2)")
    public WebElement roomInfo1;

    @FindBy(xpath = "//*[@id=\"top_rail_div\"]/div/div[1]/div[3]/p[2]/span[2]/span[2]")
    public WebElement roomInfo2;


    public BookingSummary() {
        PageFactory.initElements(driver, this);
    }

    public void verifyHotelName(String Name){
        String hotel = hotelName.getText();
        if(Name.equals(hotel)){
            System.out.println(("Hotel Name Verified : " + hotel));
        }else{
            System.out.println("Hotel Name Invalid");
        }
    }

    public void verifyTravellerDetails(String FirstName, String LastName, String Email, String Phone){
        String fName,lName, email,contact,name,details;
        String n[], c[];
        name = travellerName.getText();
        n = name.split("\\s",0);
        fName =n[0];
        lName = n[1];
        details = contactInfo.getText();
        c= details.split("\\s",0);
        contact =c[0] ;
        email = c[1];
        if(FirstName.equals(fName)){
            System.out.println("First Name Verified : " + fName);

        }else{
            System.out.println("Invalid First Name");
        }

        if(LastName.equals(lName)){
            System.out.println("Last Name Verified : " + lName);

        }else{
            System.out.println("Invalid Last Name");
        }

        if(Email.equals(email)){
            System.out.println("Email Verified : " + email);
        }else{
            System.out.println("Invalid Email Id");
        }

        if(Phone.equals(contact)){
            System.out.println("Contact Verified : " + contact);
        }else{
            System.out.println("Invalid Mobile Number");
        }



    }

    public void verifyDates(String checkIn, String checkOut, String iMonth, String oMonth){
        String inDate,inMonth,inYear,outDate,outMonth,outYear;
        String cIn = checkInDate.getText();
        String cOut = checkOutDate.getText();

        String cInn[],cOutt[];
        cInn = cIn.split("\\s",0);
        cOutt = cOut.split("\\s",0);
        inDate = cInn[0];
        inMonth = cInn[1];
        outDate = cOutt[0];
        outMonth = cOutt[1];


        if(checkIn.equals(inDate)){
            System.out.println("Check-In Date Verified : " + inDate);
        }else{
            System.out.println("Invalid Check-In Date");
        }

        if(checkOut.equals(outDate)){
            System.out.println("Check-Out Date Verified : " + outDate);
        }else{
            System.out.println("Invalid Check-Out Date");
        }

        if(iMonth.equals(inMonth)){
            System.out.println("Check-In Month Verified : " + inMonth);
        }else{
            System.out.println("Invalid Check-In Month");
        }

        if(oMonth.equals(outMonth)){
            System.out.println("Check-Out Month Verified : " + outMonth);
        }else{
            System.out.println("Invalid Check-Out Month");
        }
    }
}
