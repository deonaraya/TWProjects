package com.deo.tw.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by chandrad on 8/1/16.
 */
public abstract class BasePage<P extends BasePage> {

    protected WebDriver driver;
    protected WebDriverWait waitTime;
    protected static final int PAGE_LOAD_TIMEOUT = 30;
    protected static final int POLLING_RATE = 2;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
    }

    /**
     * Method to get the condition for checking the page load
     *
     * @return ExpectedCondition for the element to be verified.
     */
    protected abstract ExpectedCondition<?> getPageLoadCondition();

    /**
     * Method for child page instantiation
     */
    protected abstract void instantiatePage(P page);

    protected void waitforAjax(){
        waitTime = new WebDriverWait(driver,130);
        waitTime.until(new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean)js.executeScript("return jQuery.active == 0");
            }});
    }

    protected void waitForPageToLoad(ExpectedCondition<?> expectedCondition) {

        Wait wait = new FluentWait(driver)
                .withTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(POLLING_RATE, TimeUnit.SECONDS);
        wait.until(getPageLoadCondition());
    }

    protected void waitForElement(ExpectedCondition expectedCondition) {
        waitTime = new WebDriverWait(driver,30);
        waitTime.until(expectedCondition);

    }




}
