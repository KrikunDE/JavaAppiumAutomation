import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class SixthTest {

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
    public void sixthTest() {

        //search & click item SKIP
        WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5 );


        // click on search wikipedia field
        WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView']")
                , "Cannot find element",
                5 );

        //  send test in search wikipedia field + 04.xpath concat
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5);


        waitForElementAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Cannot find search input",
                5);


//  training version with concat = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']"

        WaitForElementNotPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find Object-oriented programming language topic searched by Java",
                5);
        System.out.println("FourthTestRun");


    }

    @Test
    public void cancelSearch () {

        WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5 );


        waitForElementPresent(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Cannot find announcement banner element",
                5);

        WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Cannot click Search field by Id element",
                5);

        WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot click back button by class element",
                5);

// doesn't work
        WaitForElementNotPresent(
                By.xpath("//*[@resource-id='222org.wikipedia:id/search_lang_button']"),
                "Element is not hidden",
                5);

        System.out.println("CancelSearch TestRun completed successfully");

    }



    @Test
    public void testCompareArticleTitle () {

        //search & click item SKIP
        WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5 );


        // click on search wikipedia field
        WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5 );

        //  send test in search wikipedia field + 04.xpath concat
        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5);

        WaitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find Object-oriented programming language topic searched by Java",
                5);

        WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"),
                "Cannot click ... icon",
                5);


        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_recently_viewed']"),
                "Cannot find 'Recently viewed' text",
                15);

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected menu option",
                "Recently viewed",
                article_title
        );

        System.out.println(article_title + "was found");

    }



    @Test
    public void testSearchTextAvailability () {

        //search & click item SKIP
        WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5 );


        WebElement search_element = waitForElementPresent(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Cannot find 'Search Wikipedia' text in search field",
                15);

        String article_title = search_element.getAttribute("text");
        Assert.assertEquals(
                "Appium sees unexpected text in search field ",
                "Search 1Wikipedia",
                article_title
        );


    }







        // 03. wait
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    // 04. overloading - allows to not set up a timeout parameter each time while adding it also works!
    private WebElement waitForElementPresent (By by, String error_message) {

        return waitForElementPresent(by, error_message, 5);

    }

    // 06.waits
    private WebElement WaitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element= waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    // 06.waits
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element= waitForElementPresent(by,error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSeconds)
    {
        WebElement element= waitForElementPresent(by,error_message, 5);
        element.clear();
        return element;
    }




    private boolean WaitForElementNotPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message+ "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }
}

