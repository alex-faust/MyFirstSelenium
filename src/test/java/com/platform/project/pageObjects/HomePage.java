package com.platform.project.pageObjects;

import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HomePage
{
    @FindBy(xpath = "//*[@id=\"bodyContent\"]/h1")
    WebElement pageTitle;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/a[1]/u[1]")
    WebElement logYourselfIn;
    @FindBy(xpath = "//html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/a[2]/u[1]")
    WebElement createAccount;
    @FindBy(xpath = "//select[@name='manufacturers_id']")

    private WebDriver driver;
    ProductInfoPage productInfo;
    private Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openHomePage()
    {
        log.info("Opening Homepage");
        driver.get(ReadPropertyFile.getConfigPropertyVal("homePageUrl"));
    }

    public String getPageTitle()
    {
        log.info("Getting title");
        //WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/h1"));
        String title = pageTitle.getText();
        log.info("Home page title is: " + title);
        return title;
    }

    public void clickLogInText()
    {
        //logger.info("Logging in");
        //driver.findElement(By.xpath("//u[contains(text(),'log yourself in')]")).click();
        logYourselfIn.click();
    }

    public void clickCreateAccount()
    {
        createAccount.click();
    }

    public void checkAllLinks()
    {
        HttpURLConnection huc;
        String homePage = ReadPropertyFile.getConfigPropertyVal("homePageUrl");
        int respCode;
        String url;
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        Iterator<WebElement> it = allLinks.iterator();

        log.info("All the web links are: ");

        while (it.hasNext())
        {
            url = it.next().getAttribute("href");
            System.out.println(url);
            if (url == null || url.isEmpty())
            {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if (!url.startsWith(homePage))
            {
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try
            {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();

                if (respCode >= 400)
                {
                    System.out.println(url + " is a broken link.");
                } else
                {
                    System.out.println(url + " is a valid link.");
                }
            } catch (MalformedURLException mue)
            {
                mue.printStackTrace();
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    public String[] dropDownMenu()
    {
        WebElement element = driver.findElement(By.name("manufacturers_id"));
        Select selectElement = new Select(element);
        List<WebElement> elements = selectElement.getOptions();
        log.info("There are " + (elements.size()-1) + " items in the drop down.");
        String[] values = new String[elements.size() - 1];
        for(int i = 1; i < elements.size(); i++)
        {
            String[] temp = elements.get(i).getText().split(" ");
            values[i-1] = Arrays.toString(temp);
        }

        for(String s: values)
        {
            log.info("The values are: " + s);
        }
        return values;
    }

    public void selectForItem()
    {
        //String searchForItem;
        String[] items = new String[5];
        try
        {
            FileInputStream file = new FileInputStream
                    (new File("C:\\Users\\abcle\\IdeaProjects\\MyFirstSelenium\\files\\Purchase Items.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            XSSFRow row1 = sheet.getRow(0);
            XSSFCell cellValue1 = row1.getCell(0);
            items[0] = String.valueOf(cellValue1);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //for(int i = 0; i < items.length; i++)
        //{
            WebElement webElement = driver.findElement(By.linkText(items[0]));
            webElement.click();
            driver.navigate().back();
            //System.out.println(webElement);
        //}
    }



}
