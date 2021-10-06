package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {


    private static final  String
        TITLE = "//android.view.View/android.view.View[1]/android.view.View[1]",
        FOOTER_ELEMENT = "//*[@text='View article in browser']",
        BOOKMARK_BUTTON = "//*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
        SNACKBAR_ICON = "//*[@resource-id='org.wikipedia:id/snackbar_action']",
        FOLDER_NAME_FIELD = "//*[@resource-id='org.wikipedia:id/text_input']",
        ADD_BKMRK_OK_BTN = "//*[@resource-id='android:id/button1']",
        TITLE_NAME_TPL = "//android.view.View[@content-desc='{SUBSTRING}']";




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




    public  void addArticleToMyList (String name_of_folder) {

        this.WaitForElementAndClick(
                By.xpath(BOOKMARK_BUTTON),
                "Cannot click Bookmark icon ",
                5);


        this.WaitForElementAndClick(
                By.xpath(SNACKBAR_ICON),
                "Cannot click snackbar icon ",
                5);


        this.waitForElementAndSendKeys(
                By.xpath(FOLDER_NAME_FIELD),
                name_of_folder,
                "Cannot create a Bookmark folder",
                5);

        this.WaitForElementAndClick(
                By.xpath(ADD_BKMRK_OK_BTN),
                "Cannot click 'OK' Button ",
                5);

    }

    public void WaitForElementPresentByTitle (String substring) {

        String title_result_xpath = getResultTitleElement(substring);

        this.WaitForElementAndClick(By.xpath(title_result_xpath),
                "Cannot find relevant text of the article title - " + substring,
                10);
    }



    private static String getResultTitleElement (String substring)
    {
        return TITLE_NAME_TPL.replace("{SUBSTRING}", substring);
    }





}



