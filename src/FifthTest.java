import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class FifthTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/dkrykun/Downloads/apk/wiki.apk");

        // Asus_Z
        capabilities.setCapability("deviceName", "android_ASUS_Z");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformVersion", "10");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        //emulator
//        capabilities.setCapability("deviceName", "Nexus5Emulator");
//        capabilities.setCapability("automationName", "UiAutomator2");
//        capabilities.setCapability("platformVersion", "7.1.1");


    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void fourthTest() {

        //search & click item SKIP
        WaitForElementByXpathAndClick("//*[contains(@text, 'SKIP')]","Cannot find SKIP element", 5 );


        // click on search wikipedia field
        WaitForElementByXpathAndClick("//*[@text='Search Wikipedia']", "Cannot find element", 5 );

        //  send test in search wikipedia field + 04.xpath concat
        WaitForElementByXpathAndSendKeys(
                "//*[@resource-id='org.wikipedia:id/search_src_text']",
                "Java",
                "Cannot find search input",
                5);

//  training version with concat = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']"

        WaitForElementPresentByXpath ("//*[@text='Object-oriented programming language']",
                "Cannot find Object-oriented programming language topic searched by Java");
        System.out.println("FourthTestRun");


    }

    @Test
    public void cancelSearch () {

        WaitForElementByXpathAndClick("//*[contains(@text, 'SKIP')]",
                "Cannot find SKIP element",
                5 );


        WaitForElementPresentByXpath("//*[@text='Search Wikipedia']",
                "Cannot find announcement banner element",
                5);

        WaitForElementByXpathAndClick("//*[@text='Search Wikipedia']",
                "Cannot click Search field by Id element",
                5);

        WaitForElementByXpathAndClick("//*[@class='android.widget.ImageButton']",
                "Cannot click back button by class element",
                5);

// doesn't work
        WaitForElementNotPresentByXpath("//*[@class='android.widget.ImageButton']",
                "Element is not hidden",
                5);

        System.out.println("CancelSearch TestRun completed successfully");

    }


    // 03. wait
    private WebElement WaitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
    private WebElement WaitForElementPresentByXpath(String xpath, String error_message) {

        return WaitForElementPresentByXpath(xpath,error_message, 5);

    }

    // 06.waits
    private WebElement WaitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element= WaitForElementPresentByXpath(xpath, error_message, 5);
        element.click();
        return element;
    }

    // 06.waits
    private WebElement WaitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element= WaitForElementPresentByXpath(xpath,error_message, 5);
        element.sendKeys(value);
        return element;
    }

    // 07.search_id

    private WebElement WaitForElementPresentById(String id, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }


    private WebElement WaitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds)
    {
        WebElement element= WaitForElementPresentById(id, error_message, 5);
        element.click();
        return element;
    }

    private WebElement WaitForElementByIdAndSendKeys(String id, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element= WaitForElementPresentById(id,error_message, 5);
        element.sendKeys(value);
        return element;
    }


// doesn't work
    private boolean WaitForElementNotPresentByXpath (String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message+ "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }
}

