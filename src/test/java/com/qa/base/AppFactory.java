package com.qa.base;

import com.qa.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import com.qa.utils.configReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class AppFactory {

    public static AppiumDriver driver;
    public static configReader configReader;

    @BeforeTest
    @Parameters({"platformName", "platformVersion", "deviceName"})

    public void initializer(String platformName, String platformVersion, String deviceName) throws MalformedURLException, URISyntaxException {

        try {
            configReader = new configReader();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:platformName", platformName);
            capabilities.setCapability("appium:platformVersion", platformVersion);
            capabilities.setCapability("appium:deviceName", deviceName);
            capabilities.setCapability("appium:automationName", configReader.getAutomationName());
            capabilities.setCapability("appium:app", System.getProperty("user.dir") + configReader.getApkPath());
            capabilities.setCapability("appium:appPackage", configReader.getAppPackage());
            capabilities.setCapability("appium:appActivity", configReader.getAppActivity());
            capabilities.setCapability("appium:noReset", configReader.getNoReset());


            URL url = new URL(configReader.getAppiumServerEndpointURL());
            driver = new AndroidDriver(url, capabilities);
            AppDriver.setDriver(driver);


        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            throw e;
        }
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utilities.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        this.waitForVisibility(element);
        element.click();
    }


    public void sendKeys(WebElement element, String text) {
        this.waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element, String attribute) {
        this.waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    @AfterTest
    public static void quitDriver() {
        if
        (driver != null) {
            driver.quit();


        }
    }
}