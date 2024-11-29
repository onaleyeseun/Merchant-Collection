package Merchant_Collection_Portal.BaseClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {

    WebDriver driver;
    public JavascriptExecutor jsExecutor;
    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        jsExecutor = ((JavascriptExecutor) driver);
    }

    public void waitForVisibility(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(el));
    }
    public WebDriverWait waitForElement(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }
    public void enterText(WebElement el, String text) {
        waitForVisibility(el);
        el.sendKeys(text);
    }
    public void setAttribute(WebElement el, String attrName, String attrValue) {
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", el, attrName, attrValue);
    }
}
