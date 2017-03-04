package com.deo.tw.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.awt.*;

/**
 * Created by chandrad on 8/7/16.
 */
public class BaseTest {

    protected WebDriver driver ;

    @BeforeClass
    public void setUp(){
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://qa1.learn.cisco/");
        //driver.get("https://t10-qa.xkit.co");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int Width = (int) toolkit.getScreenSize().getWidth();
        int Height = (int) toolkit.getScreenSize().getHeight();
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(Width, Height));
    }

    public void getDriver(){

    }

    //    @AfterClass
    //    public void tearDown(){
    //        driver.close();
    //        driver.quit();
    //    }

}
