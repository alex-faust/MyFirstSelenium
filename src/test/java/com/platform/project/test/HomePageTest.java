package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class HomePageTest
{
    WebDriver driver;
    HomePage homePage;
    LogInPage logInPage;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
    }

    @Test
    public void openHomePage()
    {
        homePage.openHomePage();
        Commons.check(driver, homePage.getPageTitle().equals("Welcome to iBusiness"), "Home Page Title didn't match.");
    }
    
    @Test
    public void openAllLinks()
    {
        homePage.openHomePage();
        Commons.check(driver, homePage.checkAllLinks() == 0, "There is a bad link somewhere." );
    }

    @Test
    public void dropDownMenu()
    {
        String[] elementB = new String[]{
            "[Canon]", "[Fox]", "[GT, Interactive]",
            "[Hewlett, Packard]", "[Logitech]", "[Matrox]", "[Microsoft]",
            "[Samsung]", "[Sierra]", "[Warner]"};
        homePage.openHomePage();
        String[] elementA = homePage.dropDownMenu();
        Commons.check(driver, Arrays.toString(elementA).equals(Arrays.toString(elementB)), "The drop down menu items are different");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
