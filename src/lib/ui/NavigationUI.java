package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final  String
            VIEW_LIST = "//*[@resource-id='org.wikipedia:id/snackbar_action']";


    public NavigationUI (AppiumDriver driver)
    {
        super (driver);

    }

    public void clickViewLists () {
        this.WaitForElementAndClick(
                By.xpath(VIEW_LIST),
                "Cannot click 'ViewList' Button ",
                5);

    }

}
