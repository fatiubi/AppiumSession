import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class connectionString {

    AppiumDriver driver;

    @BeforeTest
    public void initializer() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("appium:app", System.getProperty("user.dir") + "/App/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723"), capabilities);
        System.out.println("Starting Appium server");

    }

    @Test
    public void sampleTest(){
        System.out.println("This is my First sample Test");
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }


}


