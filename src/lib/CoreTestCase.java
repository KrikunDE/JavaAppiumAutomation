package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;



public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus5Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("platformVersion", "7.1.1");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("app", "/Users/dkrykun/Downloads/apk/wiki.apk");


//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "android_ASUS_Z");
//        capabilities.setCapability("automationName", "Appium");
//        capabilities.setCapability("appPackage", "org.wikipedia");
//        capabilities.setCapability("appActivity", ".main.MainActivity");
//        capabilities.setCapability("platformVersion", "10");
//        capabilities.setCapability("app", "/Users/dkrykun/Downloads/apk/wiki.apk");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();

    }


    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    @Override // IV lesson
    protected TestResult createResult() {
        return super.createResult();
    }


    protected void rotateScreenPortrait () {

        driver.rotate(ScreenOrientation.PORTRAIT);

    }

    protected void rotateScreenLandscape () {
        driver.rotate(ScreenOrientation.LANDSCAPE);

    }
    protected void runAppInBacground () {

        driver.runAppInBackground(2);

    }

}




