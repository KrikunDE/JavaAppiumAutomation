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


public class ThirdTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "android_ASUS_Z");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("app", "/Users/dkrykun/Downloads/apk/wiki.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void thirdTest() {

        //search & click item SKIP
        WaitForElementByXpathAndClick("//*[contains(@text, 'SKIP')]","Cannot find SKIP element", 5 );


        // click on search wikipedia field
        WaitForElementByXpathAndClick("//*[@class='android.widget.TextView']", "Cannot find element", 5 );

        //  send test in search wikipedia field + 04.xpath concat
        WaitForElementByXpathAndSendKeys(
                "//*[@resource-id='org.wikipedia:id/search_src_text']",
                "Java",
                "Cannot find search input",
                5);

//  training version with concat = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']"

   WaitForElementPressedByXpath ("//*[@text='Object-oriented programming language']",
            "Cannot find Object-oriented programming language topic searched by Java");
        System.out.println("ThirdTestRun");


    }
    // 03. wait
    private WebElement WaitForElementPressedByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
    private WebElement WaitForElementPressedByXpath(String xpath, String error_message) {

        return WaitForElementPressedByXpath (xpath,error_message, 5);

    }

  // 06.waits
    private WebElement WaitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element= WaitForElementPressedByXpath (xpath, error_message, 5);
        element.click();
        return element;
    }

    // 06.waits
    private WebElement WaitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element= WaitForElementPressedByXpath (xpath,error_message, 5);
        element.sendKeys(value);
        return element;
    }

}



