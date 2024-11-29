package utils.helpers;

import org.testng.annotations.DataProvider;

import static Merchant_Collection_Portal.BaseClasses.TestBase.testData;

public class TestDataProvider {

    @DataProvider(name = "passwordValidationData")
    public static Object[][] passwordValidationData() {
        // Fetch the properties from your test data (assuming testData is already initialized properly)
        return new Object[][]{
                {testData.getProperty("invalidPassword2.password"), testData.getProperty("invalidPassword2.errorMessage")},
                {testData.getProperty("invalidPassword3.password"), testData.getProperty("invalidPassword3.errorMessage")},
                {testData.getProperty("invalidPassword4.password"), testData.getProperty("invalidPassword4.errorMessage")},
                {testData.getProperty("invalidPassword5.password"), testData.getProperty("invalidPassword5.errorMessage")},
                {testData.getProperty("invalidPassword6.password"), testData.getProperty("invalidPassword6.errorMessage")}
        };
    }
}
