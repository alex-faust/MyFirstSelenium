package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.CheckOutPage;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;

public class CheckoutPageTest
{
    WebDriver driver;
    HomePage homePage;
    LogInPage logInPage;
    WebDriverManager webDriverManager;
    CheckOutPage checkOutPage;
    Logger log;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        checkOutPage = new CheckOutPage(driver);
        log = Logger.getLogger(HomePageTest.class);
    }

    @Test
    public void buyProducts()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromConfig();
        homePage.selectItems();
        homePage.goToCartContents();
        assertResult(driver, checkOutPage.verifyCart(),
                "Your Order Has Been Processed!");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
