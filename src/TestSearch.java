import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");

        SearchPageObject.waitForBackButtonToAppear();
        SearchPageObject.clickBackButtonAtSearchPage();
        SearchPageObject.waitForSearchLangButtonToNotAppear();  // doesn't work

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
    public void testCompareArticleTitle() {

//        //search & click item SKIP
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[contains(@text, 'SKIP')]"),
//                "Cannot find SKIP element",
//                5);
//
//
//        // click on search wikipedia field
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[@text='Search Wikipedia']")
//                , "Cannot find element",
//                5);
//
//        //  send test in search wikipedia field + 04.xpath concat
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
//                "Java",
//                "Cannot find search input",
//                5);


//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[@text='Object-oriented programming language']"),
//                "Cannot find Object-oriented programming language topic searched by Java",
//                5);


        //String article_title = title_element.getAttribute("text");





        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();


//        WebElement title_element = MainPageObject.waitForElementPresent(
//                By.xpath("//android.view.View/android.view.View[1]/android.view.View[1]"),
//                "Cannot find article 'Java Script' title",
//                15);

        Assert.assertEquals(
                "We see unexpected article title",
                "Java (programming language)",
                article_title
        );

        System.out.println(article_title + " was found");

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
        Assert.assertEquals(
                "Appium sees unexpected text in search field ",
                "Search Wikipedia",
                article_title
        );


    }


    @Test
    public void testSearchResultAppearsAndDisappearsAfterCleanEx3() {

        //search & click item SKIP
        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        // click on search wikipedia field
        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);

        //  send test in search wikipedia field + 04.xpath concat
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot find search input",
                5);


        MainPageObject.SearchElementAssert(
                "//android.view.ViewGroup[1]/android.widget.TextView[2]",
                "No Indonesian island in search results",
                "text",
                "Appium sees unexpected text in search",
                "Indonesian island");

        MainPageObject.SearchElementAssert(
                "//android.view.ViewGroup[2]/android.widget.TextView[2]",
                "No High-level programming language in search results",
                "text",
                "Appium sees unexpected text in search",
                "High-level programming language");


        MainPageObject.SearchElementAssert(
                "//android.view.ViewGroup[3]/android.widget.TextView[2]",
                "No Object-oriented programming language in search results",
                "text",
                "Appium sees unexpected text in search",
                "Object-oriented programming language");


        MainPageObject.waitForElementAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Cannot find search input",
                5);

// didn't worked
        MainPageObject.WaitForElementNotPresent(
                By.xpath("//*[@resource-id='222org.wikipedia:id/search_lang_button']"),
                "Element is not hidden",
                5);

    }


    @Test
    public void testSwipeArticle() {



        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Appium");
        SearchPageObject.clickByArticleWithSubstring("Self-contained and compressed executable format for the Linux platform");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

//        //search & click item SKIP
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[contains(@text, 'SKIP')]"),
//                "Cannot find SKIP element",
//                5);
//
//
//        // click on search wikipedia field
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[@text='Search Wikipedia']")
//                , "Cannot find element",
//                5);

        //  send test in search wikipedia field + 04.xpath concat
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
//                "Appium",
//                "Cannot find search input",
//                5);


//
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Appium')]"),
//                "Cannot find 'Appium' topic ",
//                5);
//

//        MainPageObject.swipeUpToFindElement(
//                By.xpath("//*[@text='View article in browser']"),
//                10,
//                "'View article in browser' link is absent");
    }


    @Test
    public void testAmountOfEmptySearch() {


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);

        String search_line = "987kby8v6";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                5);

        String empty_result_label = "//*[@text='No results']";
        String search_result_locator = "//*[@class='android.view.ViewGroup'][2]";


        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find result " + empty_result_label,
                15);


        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line);

    }


    @Test
    public void testAssertSearchResults() {

        String name_of_search = "Linkin Park Discography";
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list'] //*[@class='android.widget.TextView']";

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);


        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                name_of_search,
                "Cannot find search input",
                5);


        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find search request" + name_of_search,
                5);


        int amount_of_search_results = MainPageObject.getAmountOfElements
                (By.xpath(search_result_locator));

        Assert.assertTrue("We found too few results",
                amount_of_search_results > 0);
        System.out.println("Amount of searched results is " + amount_of_search_results);

    }


    @Test
    public void testChangeScreenOrientationOnSearchResult() {

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);

        String search_line = "Java (programming language)";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java' topic searched by  " + search_line,
                5);


        String title_before_rotation = MainPageObject.WaitForElementAndGetAttribute(
                By.xpath("//*[@text=('Object-oriented programming language')]"),
                "text",
                "Cannot find subtitle of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.WaitForElementAndGetAttribute(
                By.xpath("//*[@text=('Object-oriented programming language')]"),
                "text",
                "Cannot find subtitle of article",
                15
        );

        Assert.assertEquals(
                "Article subtitle have being changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject.WaitForElementAndGetAttribute(
                By.xpath("//*[@text=('Object-oriented programming language')]"),
                "text",
                "Cannot find subtitle of article",
                15
        );

        Assert.assertEquals(
                "Article subtitle have being changed after second screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }


    @Test
    public void testCheckSearchArticleInBackground() {

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);


        String search_line = "Java (programming language)";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "Cannot find search input",
                5);


        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java' topic searched by  " + search_line,
                5);


        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent( // bug in Wiki
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' topic after returning from background",
                5);

    }


    @Test
    public void testAddBookmarkAtTheTestFolder() {

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);


        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium",
                "Cannot find search input",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Appium')]"),
                "Cannot find 'Appium' topic ",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannot click Bookmark icon ",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot click snackbar icon ",
                5);

        String name_of_folder = "TestFolder";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot create a Bookmark folder",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1']"),
                "Cannot click 'OK' Button ",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot click 'ViewList' Button ",
                5);


        MainPageObject.swipeUp(300);

//        MainPageObject.swipeElementToLeft(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
//                "Сannot swipe Appium title item to delete");


    }


    @Test
    public void testAddTwoBookmarksAtTheTestFolderEx5() {

//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[contains(@text, 'SKIP')]"),
//                "Cannot find SKIP element",
//                5);
//
//
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[@text='Search Wikipedia']")
//                , "Cannot find element",
//                5);
//
//
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
//                "Appium",
//                "Cannot find search input",
//                5);
//
//        MainPageObject.WaitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Appium')]"),
//                "Cannot find 'Appium' topic ",
//                5);



        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Appium");
        SearchPageObject.clickByArticleWithSubstring("Self-contained and compressed executable format for the Linux platform");



        // adding first article
        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
                "Cannot click Bookmark icon ",
                5);


//        WaitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
//                "Cannot click Bookmark icon ",
//                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot click snackbar icon ",
                5);

        String name_of_folder = "TestFolder";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot create a Bookmark folder",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1']"),
                "Cannot click 'OK' Button ",
                5);

        // adding second article

        MainPageObject.WaitForElementAndClick(
                By.xpath("//android.view.View[@content-desc='automation']/android.widget.TextView"),
                "Cannot click second article ",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("        //android.widget.ImageView[@content-desc='More options']"),
                "Cannot click 'More options' for second article ",
                5);

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView'] [contains(@text, 'Add to reading list')]"),
                "Cannot click 'Add to reading list' Button",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'] [contains(@text, '" + name_of_folder + "')]"),
                "Cannot click  ",
                5);


        // open TestFolder & delete Appium article

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Cannot click 'ViewList' Button ",
                5);


//          Swipe isn't working
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Appium')]"),
                "Сan't swipe Appium title item to delete");



        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title']"),
                "Cannot find 'TestFolder' title",
                5);


        MainPageObject.WaitForElementPresentByXpath(
                "//*[contains(@text, 'TestFolder')]",
                "Cannot find 'TestFolder' title",
                5);

    }

    @Test
    public void testOpenArticleAndAssertTitleEx6() {

        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find SKIP element",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']")
                , "Cannot find element",
                5);


        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Irpin",
                "Cannot find search input",
                5);


        MainPageObject.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, 'Irpin')]"),
                "Cannot find 'Irpin' topic ",
                5);


        String subtitle_actual = MainPageObject.WaitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "text",
                "Cannot find subtitle of article",
                15
        );

        Assert.assertEquals(
                "Article subtitle is not 'city in Kyiv Oblast, Ukraine'",
                "city in Kyiv Oblast, Ukraine",
                subtitle_actual
        );


    }


}









