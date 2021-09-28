package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {


    private static final  String
        TITLE = "//android.view.View/android.view.View[1]/android.view.View[1]",
        FOOTER_ELEMENT = "//*[@text='View article in browser']";

            ;

    public ArticlePageObject (AppiumDriver driver)

    {
        super(driver);
    }

    public WebElement waitForTitleElement ()
    {
        return this.waitForElementPresent(By.xpath(TITLE),
                "Cannot find article 'Java Script' title",
                10);

    }

    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();

        return title_element.getAttribute("text");

    }

    public void swipeToFooter () {

        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                20,
                "'View article in browser' link is absent");
    }


}
