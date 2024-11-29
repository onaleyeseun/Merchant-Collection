package Merchant_Collection_Portal.Tests;

import Merchant_Collection_Portal.BaseClasses.TestBase;
import Merchant_Collection_Portal.Pages.LoginPage;
import Merchant_Collection_Portal.Pages.SignUpPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import utils.helpers.TestDataProvider;


import java.lang.reflect.Method;
import java.util.List;

import static utils.extentReports.ExtentTestManager.startTest;

public class SignUpTest extends TestBase {

    SignUpPage signUpPage;
    LoginPage loginPage;

    public void initializer(){
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void beforeEachTest() {
       driver.get(testData.getProperty("baseURL"));

        initializer();

        // Click the Sign Up button
        loginPage.clickSignUpButton();
    }


    @Test(priority = 1, description = "Verify Sign Up page loads correctly")
    public void verifySignUpPageLoads(Method method) {
     startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        // Verify the Sign Up page loaded
        String expectedSignUpURL = testData.getProperty("signUpURL");
        Assert.assertEquals(driver.getCurrentUrl(), expectedSignUpURL, "Sign Up page URL does not match");
        Assert.assertTrue(signUpPage.isSignUpFormVisible(), "Sign Up form is not visible");
    }

    @Test(priority = 2, description = "Verify all expected input fields are present on the form")
    public void verifyAllInputFieldsArePresent(Method method) {
        // Start the test
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        // Get the list of all input fields from the SignUpPage
        List<WebElement> inputFields = signUpPage.getInputFields();

        // Expected input field names (based on name attribute or type for certain fields)
        String[] expectedFieldNames = testData.getProperty("expectedFieldNames").split(",");

        // Verify each field is present
        for (String expectedFieldName : expectedFieldNames) {
            boolean fieldFound = signUpPage.isFieldPresent(expectedFieldName);

            // Assert that the field is present in the form
            Assert.assertTrue(fieldFound, "Field with name '" + expectedFieldName + "' is not present on the form");
            System.out.println("Field with name '" + expectedFieldName + "' is present.");
        }
    }


    @Test(priority = 3, description = "Verify Error Message for Already Existing Account")
    public void verifyAlreadyExistingAccount(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        String existingEmail = testData.getProperty("existingEmail");

        // Fill the sign-up form with an existing email
        signUpPage.fillSignUpForm(
                testData.getProperty("businessName"),
                testData.getProperty("businessAddress"),
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                existingEmail,
                testData.getProperty("phoneNumber"),
                testData.getProperty("validPassword"),
                testData.getProperty("validPassword")
        );

        // Verify the error message
        String actualErrorMessage = signUpPage.getErrorMessage();
        String expectedErrorMessage = "An account with email '" + existingEmail + "' is pending verification, Kindly check your email for a verification link";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");
    }

    @Test(priority = 4, description = "Verify Blank Form Submission Shows Errors for All Mandatory Fields")
    public void verifyBlankFormSubmissionShowsErrors(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        signUpPage.clickContinueButton();

        // Retrieve all error messages
        List<WebElement> errorMessages = signUpPage.getMandatoryFieldErrors();
        Assert.assertFalse(errorMessages.isEmpty(), "No error messages found! Expected errors for mandatory fields.");

        // Verify all mandatory fields show error messages
        for (WebElement errorMessage : errorMessages) {
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for a mandatory field.");
        }
    }

    @Test(priority = 5, description = "Verify password error messages for invalid passwords", dataProvider = "passwordValidationData", dataProviderClass = TestDataProvider.class)
    public void verifyPasswordErrorMessages(Method method, String password, String expectedErrorMessage) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        // Print debug info
        System.out.println("Testing with Password: " + password);
        System.out.println("Expected Error Message: " + expectedErrorMessage);

        // Fill the form with an invalid password
        signUpPage.fillSignUpForm(
                testData.getProperty("businessName"),
                testData.getProperty("businessAddress"),
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                testData.getProperty("email"),
                testData.getProperty("phoneNumber"),
                password,  // Invalid password from DataProvider
                password   // Confirm password as the same
        );

        // Submit the form
        signUpPage.clickContinueButton();

        // Retrieve and assert the password error message
        String actualErrorMessage = signUpPage.getErrorMessage();  // Method to fetch error message from the form
        System.out.println("Actual Error Message: " + actualErrorMessage);

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
                "Password validation failed! Expected: " + expectedErrorMessage + ", but got: " + actualErrorMessage);


    }


    @Test(priority = 6, description = "Verify 'Must be at least 8 characters' Password Error")
    public void verifyPasswordLengthErrorMessage(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");


       // Fill the sign-up form with a password less than 8 characters
        signUpPage.fillSignUpForm(
                testData.getProperty("businessName"),
                testData.getProperty("businessAddress"),
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                testData.getProperty("email"),
                testData.getProperty("phoneNumber"),
                testData.getProperty("shortPassword"),
                testData.getProperty("shortPassword")
        );

        // Retrieve the error message displayed for the password
        String actualErrorMessage = signUpPage.getPasswordLenghthError();

        // Expected error message
        String expectedErrorMessage = "Must be at least 8 characters";

        // Assert that the actual error message matches the expected one
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
                "Password length error message does not match!");
    }

    @Test(priority = 7, description = "Verify 'Passwords must match' Error")
    public void verifyPasswordMismatchErrorMessage(Method method) {
        // Start the test
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");


        // Fill the sign-up form with mismatched passwords
        signUpPage.fillSignUpForm(
                testData.getProperty("businessName"),
                testData.getProperty("businessAddress"),
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                testData.getProperty("email"),
                testData.getProperty("phoneNumber"),
                testData.getProperty("validPassword"), // Valid password
                testData.getProperty("invalidPassword")  // Mismatched confirm password
        );

        // Retrieve the actual error message displayed for the password mismatch
        String actualErrorMessage = signUpPage.getPasswordMismatchError();

        // Expected error message
        String expectedErrorMessage = "Passwords must match";

        // Assert that the actual error message matches the expected one
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
                "Password mismatch error message does not match!");
    }


    @Test(priority = 8, description = "Verify email format validation")
    public void verifyEmailFormatValidation(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        // Enter invalid email formats in the form
        String invalidEmail = "invalid_email_format";
        signUpPage.fillSignUpForm(
                testData.getProperty("businessName"),
                testData.getProperty("businessAddress"),
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                invalidEmail,  // Passing invalid email
                testData.getProperty("phoneNumber"),
                testData.getProperty("validPassword"),
                testData.getProperty("confirmPassword")
        );

        // Submit the form
        signUpPage.clickContinueButton();

       String actualErrorMessage = signUpPage.getEmailErrorMessage();


        // Assert the error message is as expected
        String expectedErrorMessage = "Invalid email address";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
                "Email format validation failed! Expected: " + expectedErrorMessage + ", but got: " + actualErrorMessage);
    }



}
