package com.platform.project.commons;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebDriverManager
{
    private WebDriver driver;
    private String osName = System.getProperty("os.name");
    private Logger log = Logger.getLogger(WebDriverManager.class);

    private WebDriver createDriver(String browser)
    {
        if (osName.toLowerCase().contains("windows"))
        {
            if (browser.equalsIgnoreCase("chrome"))
            {
                log.info("Chrome browser detected.");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();

            } else if (browser.equalsIgnoreCase("firefox"))
            {
                log.info("Firefox browser detected.");
                System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("ie"))
            {
                log.info("Internet Explorer detected.");
                System.setProperty("webdriver.ie.driver", "src//test//resources//drivers//IEDriverServer.exe");
                driver = new InternetExplorerDriver();

            } else
            {
                log.info("Default browser detected.");
                System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
                driver = new ChromeDriver();
            }
        } else if (osName.toLowerCase().contains("mac"))
        {
            if (browser.equalsIgnoreCase("chrome"))
            {
                log.info("Chrome browser detected.");
                System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
                driver = new ChromeDriver();

            } else if (browser.equalsIgnoreCase("firefox"))
            {
                log.info("Firefox browser detected.");
                System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("explorer"))
            {
                log.info("Safari detected.");
                System.setProperty("webdriver.safari.driver", "src//test//resources//drivers//internetExplorer.msu");
                driver = new SafariDriver();
            } else
            {
                log.info("Default browser detected.");
                System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
                driver = new ChromeDriver();
            }
            //also for linux
        }
        //Implicit wait
        //how long it will take for all pages to open
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        return driver;
    }

    //doing this so no one can use the same variable
    public WebDriver getDriver(String browser)
    {
        //String browserName = System.getProperty("browser");
        if (driver == null)
        {
            try
            {
                driver = createDriver(browser);
                log.info("Driver initialization successful.");
            } catch (Exception e)
            {
                log.info("Driver initialization failed.");
                e.printStackTrace();
            }
        } else
        {
            log.info("Driver already exists.");
        }
        return driver;
    }

    //will call from command line by default
    public WebDriver getDriver()
    {
        return getDriver(Commons.createEnvVariable("browser",
                ReadPropertyFile.getConfigPropertyVal("browser")));
    }
}
