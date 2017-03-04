package com.deo.tw.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by chandrad on 8/24/16.
 */
public class UserGroupPage extends BasePage<UserGroupPage> {

    @FindBy(className = "admin-table-content")
    private WebElement groupsPageLoad;

    @FindBy(id = "userGroupSearchBox")
    private WebElement groupSearch ;

    @FindBy(className = "icon-search")
    private WebElement searchIcon;

    @FindBy(xpath = "//button[@data-original-title='Edit User Group']")
    private WebElement editGroupIcon;



    public UserGroupPage(WebDriver driver) {
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
        return ExpectedConditions.visibilityOf(groupsPageLoad);
    }

    @Override
    protected void instantiatePage(UserGroupPage page) {
        PageFactory.initElements(driver,page);
    }

    public EditGroupPage searchAndEdit(String groupName){
        groupSearch.sendKeys(groupName);
        groupSearch.sendKeys(Keys.ENTER);
       // searchIcon.click();
        waitforAjax();
        editGroupIcon.click();
        waitforAjax();
        return new EditGroupPage(driver);
    }

}
