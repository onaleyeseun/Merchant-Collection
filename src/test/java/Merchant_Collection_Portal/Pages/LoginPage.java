package Merchant_Collection_Portal.Pages;

import Merchant_Collection_Portal.BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends PageBase {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locate the SignUp button using @FindBy annotation
    @FindBy(partialLinkText = "Sign") // Adjust locator as needed
    private WebElement signUpButton;

    // Method to click the SignUp button
    public void clickSignUpButton() {
        click(signUpButton); // Using the click() method from PageBase
    }
}
