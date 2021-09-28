package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {

        this.driver = driver;


    }


    // Swipe actions
    public void swipeElementToLeft(By by, String error_message) {

        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(500)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }


    public void swipeUpToFindElement(By by, int max_swipes, String error_message) {

        // driver.findElements(by);
        driver.findElements(by).size(); //counts amount of elements found .by

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element swiping up " + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUp(int time_of_swipe) {

        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize(); // sends screen parameters at 'size'
        int x = size.width / 2;

        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(time_of_swipe).moveTo(x, end_y).release().perform();

    }


    //assertion

    public void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + " supposed to be not present ";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void SearchElementAssert(String xpath, String error_message, String attribute, String appium_assertion_msg, String expected_text) {

        WebElement search_element = waitForElementPresent(
                By.xpath(xpath),
                error_message,
                15);

        String article_title = search_element.getAttribute(attribute);
        Assert.assertEquals(
                appium_assertion_msg,
                expected_text,
                article_title);


    }

    // getting elements data
    public String WaitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeOutSeconds);
        return element.getAttribute(attribute);
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();

    }


// element presence

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }


    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
    public WebElement waitForElementPresent(By by, String error_message) {

        return waitForElementPresent(by, error_message, 5);

    }


    public boolean WaitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement WaitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
    public WebElement WaitForElementPresentByXpath(String xpath, String error_message) {

        return WaitForElementPresentByXpath(xpath, error_message, 5);

    }

    public boolean WaitForElementNotPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }


    // actions
    public WebElement WaitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }


    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }

    public WebElement WaitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds) {
        WebElement element = WaitForElementPresentByXpath(xpath, error_message, 5);
        element.click();
        return element;
    }

    public WebElement WaitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds) {
        WebElement element = WaitForElementPresentByXpath(xpath, error_message, 5);
        element.sendKeys(value);
        return element;
    }


//        // 03. wait
//    private WebElement WaitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.withMessage(error_message + "\n");
//        By by = By.xpath(xpath);
//        return wait.until(
//                ExpectedConditions.presenceOfElementLocated(by));
//    };
//
//    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
//    private WebElement WaitForElementPresentByXpath(String xpath, String error_message) {
//
//        return WaitForElementPresentByXpath(xpath,error_message, 5);
//
//    };


}
