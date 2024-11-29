package Merchant_Collection_Portal.Pages;

import Merchant_Collection_Portal.BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;

public class LandingPage extends PageBase {

    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Select elements on the page here and create methods to interact with them
}
