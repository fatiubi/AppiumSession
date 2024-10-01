package com.qa.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Driver;

import com.qa.configurationFileReader.configReader;
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

    @AfterTest
    public static void quitDriver() {
        if
        (driver != null) {
            driver.quit();


        }
    }
}