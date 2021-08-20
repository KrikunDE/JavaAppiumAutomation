import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class SecondTest {

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
    public void secondTest() {

        //search & click item SKIP
        WebElement skipButton = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        skipButton.click();

        // click on search wikipedia field
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        element_to_init_search.click();

        // 02.sendkeys send test in search wikipedia field
        WebElement element_to_enter_search_line = WaitForElementPressedByXpath("//*[contains(@text, 'Search Wikipedia')]", "Cannot find search input");
        element_to_enter_search_line.sendKeys("Appium");

        System.out.println("SecondTestRun");


    }
        // 03. wait
    private WebElement WaitForElementPressedByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    };

    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
    private WebElement WaitForElementPressedByXpath(String xpath, String error_message) {

        return WaitForElementPressedByXpath (xpath,error_message, 5);

    };

}


