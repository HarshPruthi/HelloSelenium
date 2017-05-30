package SelLearnPackage.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by harsh on 30/05/2017.
 */

public class WHTestRun {
    private static WebDriver driver = null;
    public static void main(String args[])
    {
        System.out.println("Starting");
        ObjectMap omap = new ObjectMap("C:\\Harsh\\LearnJava\\SeleniumLearning\\src\\SelLearnPackage\\pageObjects\\objectMapWH.properties" );
        System.setProperty( "webdriver.gecko.driver", "C:\\Harsh\\Selenium\\geckodriver.exe" );
        driver=new FirefoxDriver(  );
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
        driver.get( "https://www.williamhill.com.au" );

        try {
            //Open and close login page
            WilliamHillHomePage.login(driver).click();
            WilliamHillHomePage.closelogin( driver ).click();
            //Verify that Bet Slip is empty
            WilliamHillHomePage.betslip( driver ).click();
            WebElement e = WilliamHillHomePage.betslip_page( driver );
            Assert.assertEquals(e.getText(),"Your Bet Slip is empty");
            WilliamHillHomePage.betslip( driver ).click();
            //Place a bet for tomorrow's race on favorite horse
            WilliamHillHomePage.racing( driver ).click();
            WilliamHillHomePage.tomorrowraces( driver ).click();
            driver.findElement( omap.getLocator( "wh.horseracingpage.tomorrow" ) ).click();
            Thread.sleep( 5000 );
            driver.navigate().refresh();
            ArrayList<WebElement> raceElements = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//a[contains(@href,'warwick')]" ) );
            String firstRace;
            firstRace = raceElements.get( 0 ).getAttribute( "href" );
            driver.navigate().to( firstRace );
            Thread.sleep( 2000 );
            driver.findElement( By.xpath( ".//span[.='Win']" ) ).click();
            driver.findElement( By.cssSelector( ".BetButton_betButton_Ywf.BetButton_favourite_3eO" ) ).click();
            Thread.sleep( 2000 );
            WebElement qbet = QuickBetPage.quickBetWindow( driver );
            qbet.findElement(omap.getLocator( "wh.quickbetpage.stake")).click();
            qbet.findElement(omap.getLocator( "wh.quickbetpage.stake")).sendKeys( "100" );
            QuickBetPage.addToBetPage( driver ).click();
            Assert.assertTrue( driver.findElement( By.className( "SideBar_badge_2QJ" ) ).getText().equals( "1" ) );
            //Clear Bet from BetSlip
            WilliamHillHomePage.betslip( driver ).click();
            WilliamHillHomePage.clearbet( driver ).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
driver.close();


    }
}
