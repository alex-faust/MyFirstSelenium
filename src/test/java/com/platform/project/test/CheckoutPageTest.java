package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.CheckOutPage;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTest
{
    WebDriver driver;
    HomePage homePage;
    LogInPage logInPage;
    WebDriverManager webDriverManager;
    CheckOutPage checkOutPage;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }

    @Test
    public void buyProducts1()
    {
        /**
         * clicking on home page after adding item to the card
         */
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromConfig();
        homePage.selectItems1();
        homePage.goToCartContents();
        Commons.check(driver, checkOutPage.verifyCart().equals("Your Order Has Been Processed!"), "buyProducts1Fail");
    }


    @Test
    public void buyProducts2()
    {
        /**
         * navigating backwards after adding item to the cart
         */
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromConfig();
        homePage.selectItems2();
        homePage.goToCartContents();
        Commons.check(driver, checkOutPage.verifyCart().equals("Your Order Has Been Processed!"), "buyProducts2Fail");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
