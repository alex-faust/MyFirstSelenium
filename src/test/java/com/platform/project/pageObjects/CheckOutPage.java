package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class CheckOutPage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement orderConfirmation;
    @FindBy(xpath = "//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
    WebElement cashOnDeliveryBtn;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[6]/div[2]/span[1]/button[1]/span[2]")
    WebElement continueButton;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[3]/div[2]/span[1]/button[1]/span[2]")
    WebElement confirmOrder;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[2]/span[1]/span[1]/a[1]/span[2]")
    WebElement checkOutBtn;

    private WebDriver driver;
    private Logger log = Logger.getLogger(CheckOutPage.class);

    public CheckOutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyCart()
    {
        //come up with better names for the variables
        //easier way to do it would be get to the confirmation page and use totals from there instead of
        //calculating it all myself. Would save me a few lines of code
        String[] items = new String[5];
        String[] items1 = new String[5];
        double[] priceTotals = new double[5];
        double count = 0.0;
        double values = 0.0;
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\abcle\\IdeaProjects\\MyFirstSelenium\\files\\Purchase Items.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retrieve items
            for (int i = 0; i < items.length; i++)
            {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cellValue = row.getCell(0);
                items[i] = String.valueOf(cellValue);
            }
            //retrieve price totals
            for (int j = 0; j < priceTotals.length; j++)
            {
                XSSFRow row = sheet.getRow(j);
                XSSFCell cellValue = row.getCell(1);
                priceTotals[j] = Double.parseDouble(String.valueOf(cellValue));
            }
        } catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        //searching page for items and adding them to an array to compare items from excel sheet
        for (int l = 0; l < items.length; l++)
        {
            WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[" + (l + 1) + "]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]/strong[1]"));
            items1[l] = we.getText();
        }

        //retrieve the totals from the web elements and removing the dollar sign
        for (int m = 0; m < priceTotals.length; m++)
        {
            WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[" + (m + 1) + "]/td[2]/strong[1]"));
            values += Double.parseDouble(we.getText().substring(1));
        }

        //add the total of all the prices to compare to what is on the webpage
        for (int k = 0; k < priceTotals.length; k++)
        {
            count += priceTotals[k];
        }
        log.info("The total cost of the items are: " + count);

        log.info("Totals from excel are: " + Arrays.toString(priceTotals));
        log.info("Totals from web page are: " + values);

        //if the items in the list are the same as the items in the array...
        if ((Arrays.toString(items).equals(Arrays.toString(items1))) && (count == values))
        {
            checkOutBtn.click();
            continueButton.click();
            cashOnDeliveryBtn.click();
            continueButton.click();
            confirmOrder.click();
            return orderConfirmation.getText();
        } else
        {
            log.info("The totals to not match up.");
            return "Info is not correct.";
        }
    }
}
