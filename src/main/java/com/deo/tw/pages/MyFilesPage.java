package com.deo.tw.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by chandrad on 8/1/16.
 */
public class MyFilesPage extends BasePage<MyFilesPage> {

    @FindBy(id = "file-library-list")
    private WebElement myFilesPageLoad;


    public MyFilesPage(WebDriver driver) {
        super(driver);
        long start = System.currentTimeMillis();
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println("Total Time for " + this.getClass().getSimpleName() + ":" +totalTime + "ms");
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(myFilesPageLoad);
    }

    @Override
    protected void instantiatePage(MyFilesPage page) {
        PageFactory.initElements(driver,page);

    }
}
