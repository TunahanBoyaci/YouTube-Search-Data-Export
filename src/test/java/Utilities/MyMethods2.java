package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Random;

public class MyMethods2 {
    public static void myWait(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void successMessageValidation(){
        WebElement successMessage = ParameterDriver.getDriver().findElement(By.cssSelector("div[class='alert alert-success alert-dismissible']"));
        Assert.assertTrue(successMessage.isDisplayed());
        Assert.assertTrue(successMessage.getText().contains("successfully updated"));
    }

    public static String generateRandomFirstName() {
        String[] firstNames = {
                "John", "Jane", "Michael", "Emily", "David", "Sophia", "Robert",
                "Olivia", "William", "Emma", "Joseph", "Isabella", "James", "Mia",
                "Christopher", "Abigail", "Matthew", "Charlotte", "Andrew", "Amelia"};

        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];
    }

    public static String generateRandomLastName() {
        String[] firstNames = {
                "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
                "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White",
                "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Clark"};

        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];
    }

    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        int length = 12;

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            stringBuilder.append(randomChar);
        }
        String randomString = stringBuilder.toString();
        return randomString + "@gmail.com";
    }

    public static String generateRandomPhoneNumber() {
        String characters = "0123456789";
        int length = 10;

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }
}
