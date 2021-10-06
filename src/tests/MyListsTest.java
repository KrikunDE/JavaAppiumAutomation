package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTest extends CoreTestCase {

    @Test
    public void testAddBookmarkAtTheTestFolder() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Appium");
        SearchPageObject.clickByArticleWithSubstring("Self-contained and compressed executable format for the Linux platform");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.addArticleToMyList ("TestFolder");

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickViewLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.swipeListElement();

    }


}
