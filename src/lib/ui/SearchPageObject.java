package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {


    private static final String
            SKIP_BUTTON = "//*[contains(@text, 'SKIP')]",
            SEARCH_INIT_ELEMENT = "//*[@class='android.widget.TextView'] [@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_BACK_BUTTON = "//*[@class='android.widget.ImageButton']",
            SEARCH_LANG_BUTTON = "//*[@resource-id='org.wikipedia:id/search_lang_button']", // false positive test
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list'] //*[@class='android.widget.TextView']",
            ANOTHER_SEARCH_RESULT_ELEMENT = "//*[@class='android.view.ViewGroup'][2]",
            SEARCH_EMPTY_REULT_ELEMENT = "//*[@text='No results']",
            CLEAR_QUERY_BTN = "//android.widget.ImageView[@content-desc='Clear query']",

            BOOKMARK_ICON = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
            SNACKBAR_ICON = "//*[@resource-id='org.wikipedia:id/snackbar_action']",
            FOLDER_NAME_FIELD = "//*[@resource-id='org.wikipedia:id/text_input']",
            OK_BUTTON = "//*[@resource-id='android:id/button1']",
            SECOND_ARTICLE_LINK = "//android.view.View[@content-desc='software']/android.widget.TextView",
            MORE_OPTIONS_ELEMENT = "//android.widget.ImageView[@content-desc='More options']",
            ADD_TO_READING_LIST_BTN = "//*[@class='android.widget.TextView'] [contains(@text, 'Add to reading list')]",
            VIEW_LIST_BTN = "//*[@resource-id='org.wikipedia:id/snackbar_action']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    //Templates methods
    public void initSearchInput() {

        this.WaitForElementAndClick(By.xpath(SKIP_BUTTON),
                "Cannot find SKIP element",
                5);

        this.WaitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
    }

    //Templates methods

    public void typeSearchLIne(String search_line) {

        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot send search input",
                5);
    }


    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.WaitForElementPresentByXpath(search_result_xpath,
                "Cannot find search result with substring " + substring,
                5);
    }


    public String getSearchResultTitle(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.WaitForElementPresentByXpath(search_result_xpath,
                "Cannot find search result with substring " + substring,
                5);
        return substring;

    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }


    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.WaitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                10);
    }


    public void waitForBackButtonToAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_BACK_BUTTON),
                "Cannot find back button by class element",
                5);
    }

    public void waitForSearchLangButtonToNotAppear() {
        this.WaitForElementNotPresent(By.xpath(SEARCH_LANG_BUTTON),
                "Element 'language icon' is not hidden",
                5);
    }


    public void clickBackButtonAtSearchPage() {
        this.WaitForElementAndClick(By.xpath(SEARCH_BACK_BUTTON),
                "Cannot find back button by class element",
                5);
    }


    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find search request",
                5
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));


    }

    public void waitForEmptyResultsLabel() {

        this.WaitForElementPresentByXpath(SEARCH_EMPTY_REULT_ELEMENT,
                "Cannot find empty page result",
                15);

        this.assertElementNotPresent(By.xpath(ANOTHER_SEARCH_RESULT_ELEMENT),
                "We suppose not to find any result");
    }


    public void searchAndAssertSubtitleTextElement(String subtitle_text) {

        WebElement search_element = waitForElementPresent(
                By.xpath("//*[//android.view.ViewGroup/android.widget.TextView] [@text='" + subtitle_text + "']"),
                subtitle_text + " subtitle search element is missing",
                15);

        String article_title = search_element.getAttribute("text");
        Assert.assertEquals(
                "Appium sees unexpected text in search result page",
                subtitle_text,
                article_title);

    }


    public WebElement clearSearchField() {
        WebElement element = waitForElementPresent(By.xpath(SEARCH_INPUT),
                "Cannot find search field element",
                5);
        element.clear();
        return element;
    }

    public void waitForClearQueryElementNotPresent() {
        this.WaitForElementNotPresent(
                By.xpath(CLEAR_QUERY_BTN),
                "'Clear query' Element is not hidden",
                5);

    }




    // adding first article

    public void createFirstBookmark(String name_of_folder) {
        this.WaitForElementAndClick(
                By.xpath(BOOKMARK_ICON),
                "Cannot click Bookmark icon ",
                5);


        this.WaitForElementAndClick(
                By.xpath(SNACKBAR_ICON),
                "Cannot click snackbar icon ",
                5);

        // String name_of_folder = "TestFolder";

        this.waitForElementAndSendKeys(
                By.xpath(FOLDER_NAME_FIELD),
                name_of_folder,
                "Cannot create a Bookmark folder",
                5);

        this.WaitForElementAndClick(
                By.xpath(OK_BUTTON),
                "Cannot click 'OK' Button ",
                5);
    }



    public void createSecondBookmark (String name_of_folder) {

        this.WaitForElementAndClick(
                By.xpath(SECOND_ARTICLE_LINK),
                "Cannot click second article ",
                5);

        this.WaitForElementAndClick(
                By.xpath(MORE_OPTIONS_ELEMENT),
                "Cannot click 'More options' for second article ",
                5);

        this.WaitForElementAndClick(
                By.xpath(ADD_TO_READING_LIST_BTN),
                "Cannot click 'Add to reading list' Button",
                5);

        this.WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'] [contains(@text, '" + name_of_folder + "')]"),
                "Cannot click  ",
                5);
    }

    public void openTestFolder () {


        this.WaitForElementAndClick(
                By.xpath(VIEW_LIST_BTN),
                "Cannot click 'ViewList' Button ",
                5);

    }


    public void swipeToDeleteBookmarkElement (String element_title) {

        this.swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'] [contains(@text, '" + element_title + "')]"),
                "Сan't swipe " + element_title + " title item to delete");


    }
}
