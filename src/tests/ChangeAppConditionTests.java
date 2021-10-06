package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResult() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenPortrait();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();

        assertEquals(
                "Article subtitle have being changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

    }


    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLIne("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String article_title = "Java (programming language)";
        ArticlePageObject.WaitForElementPresentByTitle(article_title);
        this.runAppInBacground();
        ArticlePageObject.WaitForElementPresentByTitle(article_title); // there is a bug in app


    }


}
