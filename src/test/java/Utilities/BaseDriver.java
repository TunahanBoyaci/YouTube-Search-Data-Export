package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            closePreviousDrivers();

            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.SEVERE);

            driver = new ChromeDriver();

            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void quitDriver() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
        driver = null;
    }

    public static void closePreviousDrivers() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShot() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_hh_mm_ss_SSS");
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("screenShots/screenShot" + localDateTime.format(dateTimeFormatter) + ".png"));
    }

}
