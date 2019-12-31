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
    WebElement deliveryInfo;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement paymentInfo;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement orderConfirmation;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement processedOrder;
    @FindBy(xpath = "//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
    WebElement cashOnDeliveryBtn;
    @FindBy(xpath = "//tr[@class='moduleRow']//input[@name='payment']")
    WebElement paypalBtn;
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

    public String getDeliveryInfoTitle()
    {
        String text = deliveryInfo.getText();
        log.info("Delivery Info: " + text);
        return text;
    }

    public String getPaymentInfoTitle()
    {
        String text = paymentInfo.getText();
        log.info("Payment Info: " + text);
        return text;
    }

    public String getProcessOrderTitle()
    {
        String text = processedOrder.getText();
        log.info("Processed order: " + text);
        return text;
    }

    public String getOrderConfTitle()
    {
        String text = orderConfirmation.getText();
        //String text = driver.findElement(By.xpath("//h1[contains(text(),'Order Confirmation')]"));
        log.info("Order Confirmation: " + text);
        return text;
    }

    public String verifyCart()
    {
        //come up with better names for the variables
        //easier way to do it would be get to the confirmation page and use totals from there instead of
        //calculating it all myself. Would save me a few lines of code
        String[] items = new String[5];
        String[] items1 = new String[5];
        double[] totals = new double[5];
        double count = 0.0;
        double values = 0.0;
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\abcle\\IdeaProjects\\MyFirstSelenium\\files\\Purchase Items.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i < items.length; i++)
            {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cellValue = row.getCell(0);
                items[i] = String.valueOf(cellValue);
            }

            for (int j = 0; j < totals.length; j++)
            {
                XSSFRow row = sheet.getRow(j);
                XSSFCell cellValue = row.getCell(1);
                totals[j] = Double.parseDouble(String.valueOf(cellValue));
            }
        } catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        log.info("The items in the list are: " + Arrays.toString(items));
        for (int k = 0; k < totals.length; k++)
        {
            count += totals[k];
        }
        log.info("The total cost of the items are: " + count);

        for (int l = 0; l < items.length; l++)
        {
            WebElement we = driver.findElement(By.xpath
                    ("/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr["
                            + (l+1) + "]/td[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]/strong[1]"));
            items1[l] = we.getText();
        }
        for (int m = 0; m < totals.length; m++)
        {
            WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr["+ (m+1) +"]/td[2]/strong[1]"));
            values += Double.parseDouble(we.getText().substring(1));
        }

        log.info("The values of the purchased items are: " + values);
        log.info("items: " + Arrays.toString(items));
        log.info("items: " + Arrays.toString(items1));

        if((Arrays.toString(items).equals(Arrays.toString(items1))) && (count == values))
        {
            checkOutBtn.click();
            continueButton.click();
            cashOnDeliveryBtn.click();
            continueButton.click();
            confirmOrder.click();
            return orderConfirmation.getText();
        } else {
            log.info("The totals to not match up.");
            return "Info is not correct.";
        }
    }

}
