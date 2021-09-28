package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {


    private  static  final String
            SKIP_BUTTON = "//*[contains(@text, 'SKIP')]",
            SEARCH_INIT_ELEMENT = "//*[@class='android.widget.TextView'] [@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_BACK_BUTTON = "//*[@class='android.widget.ImageButton']",
            SEARCH_LANG_BUTTON = "//*[@resource-id='org.wikipedia:id/search_lang_button']", // false positive test
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//android.view.ViewGroup[3]/android.widget.TextView[2] [@text='{SUBSTRING}']";




    public SearchPageObject (AppiumDriver driver)

    {
        super(driver);
    }


    //Templates methods
    public void initSearchInput ()
    {

        this.WaitForElementAndClick (By.xpath(SKIP_BUTTON),
                "Cannot find SKIP element",
                5);

        this.WaitForElementAndClick (By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
    }

    //Templates methods

    public void typeSearchLIne (String search_line) {

        this.waitForElementAndSendKeys (By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot send search input",
                5);
    }


    public void waitForSearchResult (String substring) {

        String search_result_xpath = getResultSearchElement(substring);

         this.WaitForElementPresentByXpath(search_result_xpath,
            "Cannot find search result with substring " + substring,
            5);
    }





    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }




    public void clickByArticleWithSubstring (String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.WaitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                10);
    }



    public void waitForBackButtonToAppear ()
    {
        this.waitForElementPresent(By.xpath(SEARCH_BACK_BUTTON),
                "Cannot find back button by class element",
                5);
    }

    public void waitForSearchLangButtonToNotAppear ()
    {
        this.WaitForElementNotPresent(By.xpath(SEARCH_LANG_BUTTON),
                "Element 'language icon' is not hidden",
                5 );
    }


    public void clickBackButtonAtSearchPage ()
    {
        this.WaitForElementAndClick(By.xpath(SEARCH_BACK_BUTTON),
                "Cannot find back button by class element",
                5);
    }


}
