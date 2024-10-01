package com.qa.testCases;

import com.qa.base.AppFactory;
import org.testng.annotations.*;
import com.qa.pages.LoginPage;

public class loginTest extends AppFactory{

    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage();
    }

    @Test
    public void verifyValidLogin() throws InterruptedException {
        System.out.println("This test is used to login with valid Username and valid Password");
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
        Thread.sleep(30000);


    }


    @AfterMethod
    public void tearDown() {
        AppFactory.quitDriver();
    }

}
