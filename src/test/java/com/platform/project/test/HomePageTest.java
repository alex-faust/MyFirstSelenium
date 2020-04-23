package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HomePageTest
{
    WebDriver driver;
    HomePage homePage;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homePage = new HomePage(driver);

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

    public static boolean isElementVisible2(WebDriver driver, By id, int seconds)
    {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(seconds, TimeUnit.SECONDS);

        try
        {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(id)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Test
    public void searchStuff()
    {
        homePage.openHomePage();
        if(isElementVisible2(driver, By.name("keywords"), 100))
        {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    public void passText(String text)
    {
        WebElement element = driver.findElement(By.name("keywords"));
        element.sendKeys(text);
        if (isElementVisible2(driver, By.xpath("//body//input[3]"), 100))
        {
            WebElement el = driver.findElement(By.xpath("//body//input[3]"));
            el.click();
        } else {
            System.out.println("button is not found");
        }
    }

    @Test
    public void emptyQuery()
    {
        homePage.openHomePage();
        passText("");
        if(isElementVisible2(driver, By.className("messageStackError") , 100))
        {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Test (groups = {"Smoke Test 1"})
    public void robotTest()
    {
        homePage.openHomePage();
        Commons.check(driver, homePage.usingRobotClass().equals("A Bug's Life"),
                "RobotTest text does not match.");

    }

    @Test (groups = {"Smoke Test 2"})
    public void actionsTest()
    {
        homePage.openHomePage();
        Commons.check(driver, homePage.usingActionsTest().equals("A Bug's Life"),
        "ActionTest text does not match.");
    }

    @Test
    public void scrollToItem()
    {
        homePage.openHomePage();
        homePage.scrollElement();
    }

    //@DataProvider

    @AfterMethod
    public void cleanUp()
    {
        //driver.quit();
    }
}
