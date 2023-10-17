package Pages;

import Utilities.MyMethods;
import Utilities.ParameterDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DialogContent extends MyMethods {
    public DialogContent() {
        PageFactory.initElements(ParameterDriver.getDriver(), this);
    }

    @FindBy(css = "input[id='search']")
    public WebElement searchInbox;

    @FindBy(css = "button[id=\"search-icon-legacy\"]")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id='video-title']")
    public List<WebElement> listOfVideoElements;


}
