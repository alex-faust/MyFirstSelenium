package com.platform.project.commons;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile
{
    private static Logger logger = Logger.getLogger(ReadPropertyFile.class);
    private static String configFileLocation = "./src/test/resources/config.properties";
    private static String xmlFileLocaton = "./files/userPassTest.xlsx";

    private static String readFile(String file, String key)
    {
        String value = null;
        try
        {
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream(file);
            prop.load(in);

            value = prop.getProperty(key);
            logger.info("Value in property file for" + key + " is " + value);
        } catch (IOException ioe){
            ioe.printStackTrace();
            logger.info("Couldn't locate the property file");
        }
        return value;
    }

    /*private static String readXMLFile(String file, String key)
    {
        String value = null;
        try
        {
            Workbook workbook = WorkbookFactory.create(new File(xmlFileLocaton));
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter df = new DataFormatter();
            sheet.getRow(1);


        } catch (IOException ioe) {
            logger.info("Something wrong with the xml file");
            ioe.printStackTrace();
        } catch (InvalidFormatException e)
        {
            e.printStackTrace();
        }
    }*/

    public static String getConfigPropertyVal(final String key)
    {
        String configPropertyVal = readFile(configFileLocation, key);
        return configPropertyVal != null ? configPropertyVal.trim() : configPropertyVal;
        //if configpropertyVal is not equal to null, return configPropertyVal.trim, if not, return configPropertyVal if it is null
    }

    /*public static String getXMLPropertyVal(final String key)
    {

    }*/

}
