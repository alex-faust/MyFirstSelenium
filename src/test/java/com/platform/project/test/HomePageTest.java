package com.platform.project.test;

import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.*;
import com.platform.project.pageObjects.LogInPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;


public class HomePageTest
{
    WebDriver driver;
    HomePage homePage;
    LogInPage logInPage;
    WebDriverManager webDriverManager;
    //Commons c;
    Logger log;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        //c = new Commons();
        log = Logger.getLogger(HomePageTest.class);

    }

    @Test
    public void openHomePage()
    {
        homePage.openHomePage();
        assertResult(driver, homePage.getPageTitle(), "Welcome to iBusiness");

    }

    @Test
    public void openHomePage2()
    {
        homePage.openHomePage();
        assertResult(driver, homePage.getPageTitle(), "Welcome to IBusiness");
    }

    /*@Test
    public void clickOnText()
    {
        homePage.clickLogInText();
        assertResult(driver, logInPage.getPageTitle(),"Welcome, Please Sign In");
    }*/

    @Test
    public void openAllLinks()
    {
        homePage.openHomePage();
        homePage.checkAllLinks();
    }
    @AfterMethod
    public void cleanUp()
    {

        driver.quit();
    }

}
