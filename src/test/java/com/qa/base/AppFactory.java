package com.qa.base;

import com.qa.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.utils.configReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class AppFactory {

    public static AppiumDriver driver;
    public static configReader configReader;
    protected HashMap<String, String> stringHashMap = new HashMap<>();

    InputStream stringsIs;

    Utilities utilities;

    static Logger log = LogManager.getLogger(AppFactory.class.getName());

    @BeforeTest
    @Parameters({"platformName", "platformVersion", "deviceName"})

    public void initializer(String platformName, String platformVersion, String deviceName) throws IOException, URISyntaxException, ParserConfigurationException, SAXException {

        try {
            configReader = new configReader();
            utilities = new Utilities();

            log.debug("This is debug message");
            log.info("This is info message");
            log.warn("This is warn message");
            log.error("This is error message");
            log.fatal("This is fatal message");

            String xmlFileName = "strings/string.xml";
            stringsIs = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            stringHashMap = utilities.parseStringXML(stringsIs);

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