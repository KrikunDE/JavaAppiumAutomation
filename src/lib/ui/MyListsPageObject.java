package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {


        private static final  String
                SWIPED_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";


        public MyListsPageObject (AppiumDriver driver)
        {
            super (driver);

        }

        public void swipeListElement () {

            this.waitForElementPresent( By.xpath (SWIPED_ELEMENT),
                    "Cannot find element for swipe",
                    15);
            this.swipeElementToLeft(
                    By.xpath(SWIPED_ELEMENT),
                    "Cannot swipe element title item to delete");
        }

    }
