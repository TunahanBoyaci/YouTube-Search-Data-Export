package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MyMethods {
    public WebDriverWait wait = new WebDriverWait(ParameterDriver.getDriver(), Duration.ofSeconds(10));

    public void clickMethod(WebElement webElement) {
        waitUntilVisible(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        scrollToElement(webElement);
        webElement.click();
    }

    public void sendKeysMethod(WebElement webElement, String text) {
        waitUntilVisible(webElement);
        scrollToElement(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void assertText(WebElement webElement, String text) {
        waitUntilVisible(webElement);
        Assert.assertTrue(webElement.getText().contains(text));
    }

    public void waitUntilVisible(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void scrollToElement(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) ParameterDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }


}