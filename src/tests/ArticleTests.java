package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {



    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();


        String article_title = ArticlePageObject.getArticleTitle();


        assertEquals(
                "We see unexpected article title",
                "Java (programming language)",
                article_title
        );

        System.out.println(article_title + " was found");
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

    }

}
