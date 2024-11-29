package Merchant_Collection_Portal.Tests;

import Merchant_Collection_Portal.BaseClasses.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import static utils.extentReports.ExtentTestManager.startTest;

public class SampleTests extends TestBase {

    @Test(priority = 1, description = "Sample test")
    public void sample_test(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "test");

        Assert.assertEquals(driver.getCurrentUrl(), "https://baastest.9psb.com.ng/portal/login", "Expected and actual values are not the same, check logs");
        sleep(3);
    }
}
