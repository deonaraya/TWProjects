package com.deo.tw.tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chandrad on 9/21/16.
 */
public class MobProxy {

    @Test
    public void getData() throws IOException {

        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start();

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        WebDriver driver = new FirefoxDriver(capabilities);


        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT,CaptureType.RESPONSE_HEADERS,
                CaptureType.RESPONSE_COOKIES,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.RESPONSE_BINARY_CONTENT);

        proxy.newHar("learn.cisco");
        driver.get("https://t10-qa.xkit.co");

        WebElement a = new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("msg_page-box")));
        driver.findElement(By.name("email")).sendKeys("jessie@mailinator.com");
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        WebElement c = new WebDriverWait(driver,25).until(ExpectedConditions.presenceOfElementLocated(By.className("brandingLoginForm")));
        driver.findElement(By.id("IDToken2")).sendKeys("Cisco@123");
        driver.findElement(By.name("Login.Submit")).click();
//        WebElement b = new WebDriverWait(driver,90).until(ExpectedConditions.presenceOfElementLocated(By.className("user-info")));
//        System.out.println(b.getText());

        WebDriverWait  waitTime = new WebDriverWait(driver,30);
        waitTime.until(new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean)js.executeScript("return jQuery.active == 0");
            }});


        System.out.println("login completed");
        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)


        //signIn.signInSuccess("deonaraya1@gmail.com","Cisco@1234");

        Har har = proxy.getHar();
        String strFilePath = "/Users/chandrad/Desktop/demo.json";

        System.out.println("done with capturing the har");

        for (HarEntry entry : har.getLog().getEntries()) {
            if (entry.getRequest().getUrl().contains("knowledgecenter")) {

                System.out.println(String.valueOf(entry.getRequest().getUrl()));
                System.out.println(String.valueOf(entry.getResponse().getStatus()));
                System.out.println(String.valueOf(entry.getResponse().getContent().getText()));
            }
//            if ((String.valueOf(entry.getResponse().getStatus()).startsWith("4"))
//                    || (String.valueOf(entry.getResponse().getStatus()).startsWith("5"))) {
//                System.out.println(String.valueOf(entry.getResponse().getHeaders()));
//                System.out.println(String.valueOf(entry.getResponse().getContent()));
//                System.out.println(String.valueOf(entry.getResponse().getError()));
//                System.out.println(String.valueOf(entry.getResponse().getStatusText()));
//                //throw new UnsupportedOperationException("Not implemented");
//            }
//
//            System.out.println(String.valueOf(entry.getRequest().getUrl()));
//            System.out.println(String.valueOf(entry.getResponse().getStatus()));
//            System.out.println(String.valueOf(entry.getResponse().getRedirectURL()));
//            System.out.println(String.valueOf(entry.getResponse().getContent().toString()));
//            System.out.println(String.valueOf(entry.getResponse().getContent().getText()));
//            for ( HarNameValuePair nd : entry.getResponse().getHeaders()) {
//                System.out.println(String.valueOf(nd));
//            }
        }

      //  try
        {
            FileOutputStream fos = new FileOutputStream(strFilePath);
            har.writeTo(fos);
        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        proxy.stop();


        System.out.println("Done");

    }
}
