import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;


public class ConnectionString {

    AppiumDriver driver;

    @BeforeTest
    public void initializer() throws MalformedURLException, URISyntaxException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", System.getProperty("user.dir") + "/App/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
        capabilities.setCapability("appium:noReset", false);
        //capabilities.setCapability("appium:newCommandTimeout", 5000);
       // capabilities.setCapability("appium:avd","Pixel_3a_API_35");
        //capabilities.setCapability("appium:avdTimeout", 5000);

        // Create a URI object for the Appium server
        //URL url = new URL("http://localhost:4723") ;

        // Initialize the AndroidDriver
        driver = new AndroidDriver(new URL("http://localhost:4723"), capabilities);

      //  driver = new AndroidDriver(new URI(.toURL("http://localhost:4723"));
        System.out.println("Starting Appium server");

    }

    @Test
    public void sampleTest(){
        System.out.println("This is my First sample Test");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}


