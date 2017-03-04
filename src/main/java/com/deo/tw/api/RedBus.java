package com.deo.tw.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chandrad on 11/15/16.
 */
public class RedBus {

    WebDriver driver = new FirefoxDriver();


    @BeforeClass
    public void setUp() throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, KeyStoreException, InvalidKeySpecException, IllegalBlockSizeException {

        driver.get("http://www.redbus.in/search?fromCityName=Bangalore&fromCityId=122&toCityName=Hyderabad&toCityId=124&onward=17-Nov-2016&opId=0&busType=Any");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int Width = (int) toolkit.getScreenSize().getWidth();
        int Height = (int) toolkit.getScreenSize().getHeight();
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(Width, Height));
    }

    @Test
    public void capturePrice() throws InterruptedException {

        WebElement a = new WebDriverWait(driver,40).until(ExpectedConditions.presenceOfElementLocated(By.id("onward_view")));
        List<WebElement> travellerTitle = driver.findElements(By.xpath("//div[@class='service-name']"));
        List<WebElement> travellerFare = driver.findElements(By.xpath("//div[@class='fare']"));
        List<WebElement> travellerRating = driver.findElements(By.className("rating-count")) ;
        Map<String,String> traveller = new IdentityHashMap<>();

        System.out.println(travellerFare.size());
        System.out.println(travellerTitle.size());


        for (int i = 0; i < travellerFare.size();i++){
            traveller.put(travellerTitle.get(i).getText(),travellerFare.get(i).getText()) ;
        }
        System.out.println(traveller);
        System.out.println(traveller.size());

//        driver.findElement(By.id("cancellation")).click();
//        Thread.sleep(4000);
//        driver.findElement(By.className("redbus-logo")).click();
//        Thread.sleep(4000);
//
      //  Map<String,Integer> details = new TreeMap<>();
        for (WebElement title : travellerTitle)
        {
            System.out.println("printing titles " +title.getText());
        }

        for (WebElement fare : travellerFare)
        {
            System.out.println("printing fares " + fare.getText());
        }




    }
}
