package org.mindbenders.makemytrip.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.mindbenders.makemytrip.utilities.LoggerClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;


import org.mindbenders.makemytrip.utilities.Actions;
import org.mindbenders.makemytrip.utilities.WebdriverListeners;

public class TestBase {

    protected static WebDriver driver;
    protected static Properties properties;
    protected static Actions selenium_Actions;
    protected static WebdriverListeners eventListener;
    protected static EventFiringWebDriver event_driver;
    protected static ChromeOptions chromeOptions;
    protected static Logger log;

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

        selenium_Actions = new Actions();

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

    public void tearDownMain() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}