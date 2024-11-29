package Merchant_Collection_Portal.Pages;

import Merchant_Collection_Portal.BaseClasses.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignUpPage extends PageBase {

    WebDriver driver;
    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Select elements on the page here and create methods to interact with them


    // Web elements using @FindBy annotations
    @FindBy(name = "businessName")
    private WebElement businessNameField;

    @FindBy(name = "businessAddress")
    private WebElement businessAddressField;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "emailAddress")
    private WebElement emailField;

    @FindBy(css = "input.PhoneInputInput")
    private WebElement phoneNumberField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement continueButton;

    @FindBy(id = "successMessageId")
    private WebElement successMessage;

    @FindBy(css = ".Toastify__toast-container")
    private WebElement errorContainer;

    @FindBy(css = ".text-red-500.text-sm")
    private List<WebElement> mandatoryFieldErrors;

    @FindBy(xpath = "//input")
    private List<WebElement> inputFields;

    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div[2]/div/form/div[6]/div")
    private WebElement passwordLengthError;

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/form/div[7]/div")
    private WebElement passwordMismatchError;

    @FindBy(xpath = "//*[@id='root']/div[2]/div[2]/div[2]/div/form/div[4]/div")
    private WebElement emailErrorElement;



    public boolean isSignUpFormVisible() {
        return businessNameField.isDisplayed();
    }

    // Methods to interact with elements
    public void enterBusinessName(String businessName) {
        enterText(businessNameField, businessName);
    }

    public void enterBusinessAddress(String businessAddress) {
        enterText(businessAddressField, businessAddress);
    }

    public void enterFirstName(String firstName) {
        enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        enterText(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        enterText(phoneNumberField, phoneNumber);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        enterText(confirmPasswordField, confirmPassword);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    public List<WebElement> getInputFields() {
        return inputFields;
    }

    // Method to retrieve the password field error message text
    public String getPasswordLenghthError() {
        return passwordLengthError.getText();
    }

    // Method to retrieve the password field error message text
    public String getPasswordMismatchError() {
        return passwordMismatchError.getText();
    }

    public String getEmailErrorMessage() {
        return emailErrorElement.getText();
    }


    // method to fill out the entire form
    public void fillSignUpForm(String businessName, String businessAddress, String firstName, String lastName,
                               String email, String phoneNumber, String password, String confirmPassword) {
        enterBusinessName(businessName);
        enterBusinessAddress(businessAddress);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPhoneNumber(phoneNumber);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickContinueButton();
    }

    // Method to retrieve the alert error message text
    public String getErrorMessage() {
        waitForVisibility(errorContainer);
        WebElement errorMessage = errorContainer.findElement(By.cssSelector(".Toastify__toast-body > div:nth-child(2)"));
        return errorMessage.getText();
    }

       // Retrieve all error messages on the input fields
    public List<WebElement> getMandatoryFieldErrors() {
        return mandatoryFieldErrors;
    }

    // Method to verify if expected fields are present
    public boolean isFieldPresent(String expectedFieldName) {
        for (WebElement field : inputFields) {
            String fieldName = field.getAttribute("name");
            String fieldType = field.getAttribute("type");

            // Match based on field name or type (special case for 'tel')
            if (fieldName != null && fieldName.equals(expectedFieldName)) {
                return true;
            }
            if ("tel".equals(expectedFieldName) && "tel".equals(fieldType)) {
                return true;
            }
        }
        return false;
    }


}
