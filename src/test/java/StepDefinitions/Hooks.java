package StepDefinitions;

import Utilities.ParameterDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()){
            final byte[] byteImage = ((TakesScreenshot) ParameterDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(byteImage, "image/png", scenario.getName());
        }
        ParameterDriver.quitDriver();
    }
}
