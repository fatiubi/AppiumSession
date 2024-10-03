package com.qa.testCases;

import com.google.gson.stream.JsonToken;
import com.qa.base.AppFactory;
import com.qa.pages.ProductPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.*;
import com.qa.pages.LoginPage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

public class loginTest extends AppFactory{

    LoginPage loginPage;
    ProductPage productPage;
    InputStream inputStream;
    JSONObject loginUser;
    JSONTokener jsonTokener;

    @BeforeMethod
    public void setup(Method method) {
        loginPage = new LoginPage();
        System.out.println("\n********** " + method.getName() + " **********\n");
    }

    @BeforeClass
    public void setupDataStream() throws IOException {
        try {
            String dataFileName = "data/loginUser.json";
            inputStream = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener jsonTokener = new JSONTokener(Objects.requireNonNull(inputStream));
            loginUser = new JSONObject(jsonTokener);

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void verifyInvalidUsername() {
        System.out.println("This test is used to login with invalid Username and valid Password");
        loginPage.enterUserName(loginUser.getJSONObject("invalidUser").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("invalidUser").getString("password"));
        loginPage.clickLoginBtn();

        String expectedErrorMessage = "Username and password do not match any user in this service.";
        String actualErrorMessage = loginPage.getErrorMessage();

        System.out.println("Actual Error Message is " + actualErrorMessage + "\nExpected Error Message is " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void verifyInvalidPassword() {
        System.out.println("This test is used to login with valid Username and invalid Password");
        loginPage.enterUserName(loginUser.getJSONObject("invalidPassword").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("invalidPassword").getString("password"));
        loginPage.clickLoginBtn();

        String expectedErrorMessage = "Username and password do not match any user in this service.";
        String actualErrorMessage = loginPage.getErrorMessage();

        System.out.println("Actual Error Message is " + actualErrorMessage + "\nExpected Error Message is " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }

    @Test
    public void verifyValidLogin() {
        System.out.println("This test is used to login with valid Username and valid Password");
        loginPage.enterUserName(loginUser.getJSONObject("validUserAndPassword").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("validUserAndPassword").getString("password"));
        productPage = loginPage.clickLoginBtn();

        String expectedTitle = "PRODUCTS";
        String actualTitle = productPage.getTitle();

        System.out.println("Actual Product Title is " + actualTitle + "\nExpected Product Title is " + expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }



    @AfterTest
    public void tearDown() {
        AppFactory.quitDriver();
    }

}
