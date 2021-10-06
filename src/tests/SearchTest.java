package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTest extends CoreTestCase {

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
    public void testAmountOfNotEmptySearch() {

        String name_of_search = "Linkin Park Discography";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne(name_of_search);

        SearchPageObject.getAmountOfFoundArticles();
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("987kby8v6");
        SearchPageObject.waitForEmptyResultsLabel();


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
//        String search_line = "987kby8v6";
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
//                search_line,
//                "Cannot find search input",
//                5);
//
//        String empty_result_label = "//*[@text='No results']";
//        String search_result_locator = "//*[@class='android.view.ViewGroup'][2]";
//
//
//        MainPageObject.waitForElementPresent(
//                By.xpath(empty_result_label),
//                "Cannot find result " + empty_result_label,
//                15);
//
//
//        MainPageObject.assertElementNotPresent(
//                By.xpath(search_result_locator),
//                "We found some results by request " + search_line);

    }

    @Test
    public void testOpenArticleAndAssertTitleEx6() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Irpin");

        String article_subtitle = SearchPageObject.getSearchResultTitle("city in Kyiv Oblast, Ukraine" );
        SearchPageObject.clickByArticleWithSubstring("city in Kyiv Oblast, Ukraine");

        assertEquals(
                "Article subtitle is not 'city in Kyiv Oblast, Ukraine'",
                "city in Kyiv Oblast, Ukraine",
                article_subtitle
        );


    }

    @Test
    public void testSearchResultAppearsAndDisappearsAfterCleanEx3() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");

        SearchPageObject.searchAndAssertSubtitleTextElement("Indonesian island");
        SearchPageObject.searchAndAssertSubtitleTextElement("High-level programming language");
        SearchPageObject.searchAndAssertSubtitleTextElement("Object-oriented programming language");
        SearchPageObject.clearSearchField();
        SearchPageObject.waitForClearQueryElementNotPresent();

    }


    @Test
    public void testAddTwoBookmarksAtTheTestFolderEx5() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("AppImage");
        SearchPageObject.clickByArticleWithSubstring("Self-contained and compressed executable format for the Linux platform");

        String name_of_folder = "TestFolder";

        SearchPageObject.createFirstBookmark(name_of_folder);
        SearchPageObject.createSecondBookmark(name_of_folder);
        SearchPageObject.openTestFolder();
        SearchPageObject.swipeToDeleteBookmarkElement("AppImage");

    }

}