package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.CreateAccountSuccess;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountSuccessTest
{
    WebDriver driver;
    Commons c;
    LogInPage logInPage;
    HomePage homePage;
    WebDriverManager webDriverManager;
    CreateAccountSuccess cas;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        cas = new CreateAccountSuccess(driver);
        c = new Commons();
    }

    @Test
    public void createAnAccountTest()
    {
        homePage.openHomePage();
        homePage.clickCreateAccount();
        logInPage.createAnAccount();
        c.assertResult(driver, cas.getPageTitle(), "Your Account Has Been Created!");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
