package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParameterDriver {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static ThreadLocal<String> threadDriverName = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (threadDriver.get() == null) {

            if (threadDriverName.get() == null) {
                threadDriverName.set("chrome");
            }
            closePreviousDrivers();

            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.SEVERE);

            switch (threadDriverName.get()) {
                case "firefox":
                    threadDriver.set(new FirefoxDriver());
                    break;
                case "edge":
                    threadDriver.set(new EdgeDriver());
                    break;
                default:
//                    System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chrome.exe");
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--remote-allow-origins=*"); // To solve the error with Chrome v111
//                    threadDriver.set(new ChromeDriver(options));
//                    break;

                    threadDriver.set(new ChromeDriver());
                    break;
            }
            threadDriver.get().manage().window().maximize();
        }
        return threadDriver.get();
    }

    public static void setThreadDriverName(String browserName){
        threadDriverName.set(browserName);
    }

    public static void quitDriver() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            WebDriver driver = null;
            threadDriver.set(driver);
        }
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
        TakesScreenshot takesScreenshot = (TakesScreenshot) ParameterDriver.getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("screenShots/screenShot" + localDateTime.format(dateTimeFormatter) + ".png"));
    }
}
