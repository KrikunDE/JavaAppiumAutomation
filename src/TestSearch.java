import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class TestSearch extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testFirst() { //search & click item SKIP
        WebElement element = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        element.click();
        System.out.println("FirstTestRun");
    }

    @Test
    public void testSecond() {

        //search & click item SKIP
        WebElement skipButton = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        skipButton.click();

        // click on search wikipedia field
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        element_to_init_search.click();

        // 02.sendkeys send test in search wikipedia field
        WebElement element_to_enter_search_line = MainPageObject.WaitForElementPresentByXpath("//*[contains(@text, 'Search Wikipedia')]", "Cannot find search input");
        element_to_enter_search_line.sendKeys("Appium");

        System.out.println("SecondTestRun");


    }

    @Test
    public void testThird() {

        //search & click item SKIP
        MainPageObject.WaitForElementByXpathAndClick("//*[contains(@text, 'SKIP')]", "Cannot find SKIP element", 5);


        // click on search wikipedia field
        MainPageObject.WaitForElementByXpathAndClick("//*[@class='android.widget.TextView']", "Cannot find element", 5);

        //  send test in search wikipedia field + 04.xpath concat
        MainPageObject.WaitForElementByXpathAndSendKeys(
                "//*[@resource-id='org.wikipedia:id/search_src_text']",
                "Java",
                "Cannot find search input",
                5);


        MainPageObject.WaitForElementPresentByXpath("//*[@text='Object-oriented programming language']",
                "Cannot find Object-oriented programming language topic searched by Java");
        System.out.println("ThirdTestRun");


    }

    @Test
    public void testFourth() {
//
//        //search & click item SKIP
//        MainPageObject.WaitForElementByXpathAndClick("//*[contains(@text, 'SKIP')]", "Cannot find SKIP element", 5);
//
//
//        // click on search wikipedia field
//        MainPageObject.WaitForElementByXpathAndClick("//*[@class='android.widget.TextView']", "Cannot find element", 5);
//
//        //  send test in search wikipedia field + 04.xpath concat
//        MainPageObject.WaitForElementByXpathAndSendKeys(
//                "//*[@resource-id='org.wikipedia:id/search_src_text']",
//                "Java",
//                "Cannot find search input",
//                5);
//
//
//        MainPageObject.WaitForElementPresentByXpath("//*[@text='Object-oriented programming language']",
//                "Cannot find Object-oriented programming language topic searched by Java");
//        System.out.println("FourthTestRun");

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");


    }

    @Test
    public void testSixth() {

        //search & click item SKIP
        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        // click on search wikipedia field
        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView']")
                , "Cannot find element",
                5);

        //  send test in search wikipedia field + 04.xpath concat
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5);


        MainPageObject.waitForElementAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Cannot find search input",
                5);


        MainPageObject.WaitForElementNotPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find Object-oriented programming language topic searched by Java",
                5);
        System.out.println("FourthTestRun");


    }


    @Test
    public void testCancelSearchEx2() {

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.waitForElementPresent(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Cannot find announcement banner element",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView']"),
                "Cannot click Search field by Id element",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot click back button by class element",
                5);

// doesn't work
        MainPageObject.WaitForElementNotPresent(
                By.xpath("//*[@resource-id='222org.wikipedia:id/search_lang_button']"),
                "Element is not hidden",
                5);

        System.out.println("CancelSearch TestRun completed successfully");

    }


    @Test
    public void testSearchTextAvailabilityEx4() {

        //search & click item SKIP
        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        WebElement search_element = MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.LinearLayout[1]/androidx.cardview.widget.CardView/android.widget.TextView"),
                "Cannot find 'Search Wikipedia' text in search field",
                15);

        String article_title = search_element.getAttribute("text");
        assertEquals(
                "Appium sees unexpected text in search field ",
                "Search Wikipedia",
                article_title
        );


    }


    }










