package org.mindbenders.makemytrip.base;

import org.apache.log4j.PropertyConfigurator;
import org.mindbenders.makemytrip.utilities.LoggerClass;
import org.mindbenders.makemytrip.utilities.WebdriverListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class TestBase {

	protected static WebDriver driver;
	protected static Properties properties;
	protected static WebdriverListeners eventListener;
	protected static EventFiringWebDriver event_driver;
	protected static ChromeOptions chromeOptions;
	protected static Logger log;
	public int a;

	public TestBase() {

		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/org/mindbenders/makemytrip/config/config.properties");
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
		String mode = properties.getProperty("mode");
		driver = getDriver(mode, browserName);
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

	private static WebDriver getDriver(String mode, String browserName) {
		if (mode.equalsIgnoreCase("local") && browserName.equalsIgnoreCase("chrome")) {
			// For Local Execution
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeOptions);
		} else if (mode.equalsIgnoreCase("remote")) {
			// For Remote Execution
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setBrowserName(properties.getProperty("browser"));
			capability.setVersion(properties.getProperty("version"));
			capability.setCapability("headspin:newCommandTimeout", "300");
			try {
				return new RemoteWebDriver(new URL(properties.getProperty("hub_url")), capability);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static void startLogger() {
		log = Logger.getLogger(LoggerClass.class);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");

	}

	public static void delay() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static WebElement getElement(By locatorPath) {
		return driver.findElement(locatorPath);
	}

	public static List<WebElement> getElements(By locatorPath) {
		return driver.findElements(locatorPath);
	}

	public static void clickElement(By locatorPath) {
		delay();
		getElement(locatorPath).click();
	}

	public static void datePicker(By locatorPath, String month, String day) {
		delay();
		List<WebElement> elements = getElements(locatorPath);
		for (WebElement ele : elements) {
			String date = ele.getAttribute("aria-label");
			String months = date.split(" ")[1];
			String days = date.split(" ")[2];
			// System.out.println(months+days);
			if (month.equalsIgnoreCase(months) && day.equalsIgnoreCase(days)) {
				// System.out.println(months+days);
				ele.click();
				break;
			}
		}
	}

	public static void guestSelect(By locatorPath, String count) {
		delay();
		List<WebElement> elements = getElements(locatorPath);
		for (WebElement ele : elements) {
			String persons = ele.getAttribute("data-cy");
			System.out.println(persons);
			if (persons.contains(count)) {
				ele.click();
				break;
			}
		}
	}

	public static void hotelRoom(By locatorPath, int count) {
		delay();
		List<WebElement> elements = getElements(locatorPath);
		int i = 1;
		for (WebElement ele : elements) {
			if (i == count) {
				ele.click();
				break;
			}
			i++;
		}
	}

	public static void switchWindow() {
		Set<String> handles = driver.getWindowHandles();
		String[] individualHandle = new String[handles.size()];
		Iterator it = handles.iterator();
		int i = 0;
		while (it.hasNext()) {
			individualHandle[i] = (String) it.next();
			i++;
		}

		driver.switchTo().window(individualHandle[1]);
	}

	public static void doubleClick(By locatorPath) {

		Actions action = new Actions(driver);
		WebElement element = driver.findElement(locatorPath);

		action.doubleClick(element);
		action.perform();
	}

	public static void sendText(By locatorPath, String text) {
		delay();
		getElement(locatorPath).sendKeys(text);
	}

	public static void slide(WebDriver driver) {
		WebElement slider = driver.findElement(By.cssSelector(".input-range__slider"));
		Actions move = new Actions(driver);
		Action action = move.dragAndDropBy(slider, 8, 0).build();
		action.perform();
	}

	public static void moveToElement(WebDriver driver, WebElement Element) {
		new Actions(driver).moveToElement(Element).perform();
	}

	public void tearDownMain() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}