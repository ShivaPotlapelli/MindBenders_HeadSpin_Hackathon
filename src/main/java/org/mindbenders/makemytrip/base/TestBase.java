package org.mindbenders.makemytrip.base;

import org.apache.log4j.PropertyConfigurator;
import org.mindbenders.makemytrip.utilities.LoggerClass;
import org.mindbenders.makemytrip.utilities.WebdriverListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;


public class TestBase {

    protected static WebDriver driver;
    protected static Properties properties;
    protected static WebdriverListeners eventListener;
    protected static EventFiringWebDriver event_driver;
    protected static ChromeOptions chromeOptions;
    protected static Logger log;
    public int a ;

    public TestBase() {

        try {
            properties = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/org/mindbenders/makemytrip/config/config.properties");
            properties.load(ip);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("io exception");

        }

    }

    protected static void init() {
        startLogger();
        String browserName = properties.getProperty("browser");
        driver = getDriver(browserName);
        log.info(browserName + " is configured");

        event_driver = new EventFiringWebDriver(driver);
        eventListener = new WebdriverListeners();
        event_driver.register(eventListener);
        driver = event_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

        driver.get(properties.getProperty("url"));

    }

    private static WebDriver getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            return new ChromeDriver(chromeOptions);
        }
        return null;
    }

    private static void startLogger() {
        log = Logger.getLogger(LoggerClass.class);
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");

    }

    public static void delay()
    {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static WebElement getElement(By locatorPath)
    {
        return driver.findElement(locatorPath);
    }
    public static List<WebElement> getElements(By locatorPath)
    {
        return driver.findElements(locatorPath);
    }



    public static  void clickElement(By locatorPath)
    {
        delay();
        getElement(locatorPath).click();
    }
    public static  void datePicker(By locatorPath,String month,String day) {
        delay();
        List<WebElement> elements = getElements(locatorPath);
        for (WebElement ele : elements) {
            String date = ele.getAttribute("aria-label");
            String months = date.split(" ")[1];
            String days = date.split(" ")[2];
            System.out.println(months+days);
            if (month.equalsIgnoreCase(months) && day.equalsIgnoreCase(days)) {
                System.out.println(months+days);
                ele.click();
            }
        }
    }
        public static void doubleClick(By locatorPath){

        Actions action = new Actions(driver);
        WebElement element = driver.findElement(locatorPath);

        action.doubleClick(element);
        action.perform();
    }


    public static void sendText(By locatorPath, String text)
    {
        delay();
        getElement(locatorPath).sendKeys(text);
    }

    public void tearDownMain() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}