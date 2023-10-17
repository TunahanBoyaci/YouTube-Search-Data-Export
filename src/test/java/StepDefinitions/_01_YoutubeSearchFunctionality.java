package StepDefinitions;

import Pages.DialogContent;
import Utilities.MyMethods2;
import Utilities.ParameterDriver;
import com.beust.ah.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.asynchttpclient.Param;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class _01_YoutubeSearchFunctionality {
    DialogContent dialogContent = new DialogContent();

    @Given("Navigate to the page")
    public void navigateToThePage() {
        ParameterDriver.getDriver().get("https://www.youtube.com/");
    }

    @And("Search for {string} keyword")
    public void searchForKeyword(String text) {
        dialogContent.sendKeysMethod(dialogContent.searchInbox, text);
    }

    @And("Click on Search Button")
    public void clickOnSearchButton() {
        dialogContent.clickMethod(dialogContent.searchButton);
    }

    @Then("Verify that at least {string} videos are displayed")
    public void verifyThatAtLeastVideosAreDisplayed(String text) {
        int number = Integer.parseInt(text);
        int count = 0;

        WebElement lastElement = dialogContent.listOfVideoElements.get(dialogContent.listOfVideoElements.size() - 1);
        JavascriptExecutor js = (JavascriptExecutor) ParameterDriver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", lastElement);
        dialogContent.waitUntilVisible(lastElement);
        MyMethods2.myWait(3);

        for (WebElement w : dialogContent.listOfVideoElements) {
            String title = w.getAttribute("aria-label");
            if (title != null) {
                count++;
            }
        }

        Assert.assertTrue(count > number);
    }

    @Then("Excel out the title of the last video")
    public void excelOutTheTitleOfTheLastVideo() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        for (int i = 0; i < dialogContent.listOfVideoElements.size(); i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(dialogContent.listOfVideoElements.get(i).getAttribute("aria-label"));
        }

        String path = "excelFiles/excelFile.xlsx";

        try {
            File directory = new File("excelFiles");
            if (!directory.exists()) {
                directory.mkdirs(); // Create directories if they don't exist
            }

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
