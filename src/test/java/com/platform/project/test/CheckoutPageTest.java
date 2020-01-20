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
        Commons.check(driver, homePage.selectItems1().equals("Cart Contents (5)"),
                "failed to select items");
        homePage.goToCartContents();
        Commons.check(driver, checkOutPage.verifyCart().equals("Your Order Has Been Processed!"),
                "failed to buy products #1");
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
        Commons.check(driver, homePage.selectItems2().equals("Cart Contents (5)"),
                "failed to select items");
        homePage.goToCartContents();
        Commons.check(driver, checkOutPage.verifyCart().equals("Your Order Has Been Processed!"),
                "failed to buy products #2");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
