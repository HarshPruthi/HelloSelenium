package SelLearnPackage.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by harsh on 30/05/2017.
 */
public class QuickBetPage {
    private static WebElement element = null;
    private static ObjectMap objmap = new ObjectMap( "C:\\Harsh\\LearnJava\\SeleniumLearning\\src\\SelLearnPackage\\pageObjects\\objectMapWH.properties" );

    public static WebElement quickBetWindow(WebDriver driver) throws Exception {

        element = driver.findElement( objmap.getLocator( "wh.quickbetpage" ) );
        return element;
    }

    public static WebElement addToBetPage(WebDriver driver) throws Exception {

        element = driver.findElement( objmap.getLocator( "wh.quickbetpage.addbet" ) );
        return element;
    }
}