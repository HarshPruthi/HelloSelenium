package SelLearnPackage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by harsh on 30/05/2017.
 */
public class WilliamHillBet {

    public static void main(String args[]) throws InterruptedException {
        System.setProperty( "webdriver.gecko.driver", "C:\\Harsh\\Selenium\\geckodriver.exe" );
        WebDriver driver;
        int i = 0;
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait( 20, TimeUnit.SECONDS );
        driver.get( "https://www.williamhill.com.au" );
        driver.findElement( By.xpath( ".//span[.='Racing']" ) ).click();
        Thread.sleep( 5000 );
        driver.findElement( By.xpath( ".//a[@href='/horse-racing/tomorrow']/span[.='Tomorrow'][@data-reactid='155']" ) ).click();
        Thread.sleep( 5000 );
        //check if the relevant page has loaded

        ArrayList<WebElement> raceElements = (ArrayList<WebElement>) driver.findElements( By.xpath( ".//a[contains(@href,'warwick')]" ) );
        System.out.println("There are "+ raceElements.size() +"  links ");
        String firstRace;
        firstRace = raceElements.get( 0 ).getAttribute( "href" );
        raceElements.get( 0 ).click();
        //Above click is somehow not working. Alternatively directly moving to page by using href attribute
        driver.navigate().to( firstRace );
        Thread.sleep( 2000 );
        //select the favourite horse and palce the bet
        driver.findElement( By.xpath( ".//span[.='Win']" ) );
        Thread.sleep( 5000 );
        System.out.println("I am about to Finish");
        driver.findElement( By.cssSelector( ".BetButton_betButton_Ywf.BetButton_favourite_3eO" ) ).click();
        Thread.sleep( 5000 );
        WebElement quickBetWindow=driver.findElement(By.xpath( "//div[contains(@class,'QuickBet_root')]" ));
        ArrayList<WebElement> quickBetElements = (ArrayList<WebElement>) quickBetWindow.findElements( By.tagName( "input" ) );
        for (i=1;i<=quickBetElements.size();i++)
        {
            System.out.println("name of the input elements" + quickBetElements.get( i-1 ).getAttribute( "placehoder" ));
        }
        System.out.println(quickBetElements.size());
        quickBetWindow.findElement( By.xpath( "//div/input[@placeholder='Stake']" )).click();
        quickBetWindow.findElement( By.xpath( "//div/input[@placeholder='Stake']" )).sendKeys( "100" );
        quickBetWindow.findElement( By.xpath( "//span/div/button[.='Add to Bet Slip']" )).click();
        //Assert Bet in betslip
        Assert.assertTrue( driver.findElement( By.className( "SideBar_badge_2QJ" ) ).getText().equals( "1" ) );
        System.out.println(driver.findElement( By.className( "SideBar_badge_2QJ" ) ).getText());
        driver.close();

    }


}
