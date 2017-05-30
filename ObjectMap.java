package SelLearnPackage.pageObjects;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by harsh on 30/05/2017.
 */
public class ObjectMap {
    Properties prop;
    public ObjectMap (String strPath)
    {

        prop=new Properties(  );
        try {
            FileInputStream fis = new FileInputStream(strPath);
            prop.load(fis);
            fis.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public By getLocator(String strElement) throws Exception
    {
        // retrieve the specified object from the object list
        String locator = prop.getProperty(strElement);
        // extract the locator type and value from the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
        switch (locatorType.toLowerCase())
        {
            case "xpath" :
                return By.xpath(locatorValue);
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name( locatorValue );
            case "classname":
                return By.className( locatorType );
            case "tagname":
                By.tagName( locatorValue );
            default:
                throw new Exception( "Unknown Locator Type -" +locatorType );

        }


    }
}
