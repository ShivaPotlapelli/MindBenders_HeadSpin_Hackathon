package org.mindbenders.makemytrip.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCommonUtils {

    static java.text.DateFormat dateFormat;
    static Date date;

    public static final String DateFormat = "yyyyMMddHH:mm:ss";
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "//testScreenshots//";

    public static String getDate(String format) {
        dateFormat = new SimpleDateFormat(format);
        date = new Date();
        return dateFormat.format(date);
    }

    public static void takeScreenShot(WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(SCREENSHOT_PATH + "//screenshot//" + getDate(DateFormat) + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
