package com.deo.tw.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by chandrad on 8/23/16.
 */
public class CourseAssociationPage extends BasePage<CourseAssociationPage> {

    @FindBy(className = "admin-table-content")
    private WebElement courseAssocationPageLoad;

    @FindBy(id = "peopleSearchBox")
    private WebElement courseSeach;

    @FindBys(@FindBy(xpath = "//div[@class='admin-table-content']//div[@class='row']//span[@class='lbl']"))
    private List<WebElement> courseSelect;

    @FindBy(xpath = "//input[@id='selectAll']")
    private WebElement selectAll;

    @FindBy(xpath = "//div[@class='container']//button[contains(text(),'Add')]")
    private WebElement addCourse;

    @FindBy(xpath = "//div[@class='container']//button[contains(text(),'Cancel')]")
    private WebElement cancelCourse;




    public CourseAssociationPage(WebDriver driver) {
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
        return ExpectedConditions.visibilityOf(courseAssocationPageLoad);
    }

    @Override
    protected void instantiatePage(CourseAssociationPage page) {
        PageFactory.initElements(driver,page);

    }

    public CourseAssociationPage searchnSelectCourse(String courseName){
            courseSeach.sendKeys("\" "+courseName+" \"");
            courseSeach.sendKeys(Keys.ENTER);
            waitforAjax();
        if (selectAll.isSelected())
        { selectAll.click();}
            selectAll.click();
        return this ;
    }

    public EditGroupPage searchnAssociateCourse(String courseName){
        courseSeach.sendKeys("\" "+courseName+" \"");
        courseSeach.sendKeys(Keys.ENTER);
        waitforAjax();
        if (selectAll.isSelected())
        { selectAll.click();}
        selectAll.click();
        addCourse.click();
        return new EditGroupPage(driver) ;
    }


    public EditGroupPage checkAllnCancel(){
        selectAll.click();
        cancelCourse.click();
        waitforAjax();
        return new EditGroupPage(driver);
    }


    public EditGroupPage checkAllnSave(){

        Actions actions = new Actions(driver);
        if (selectAll.isSelected())
        {  selectAll.click();}
        selectAll.click();
        actions.moveToElement(addCourse);
        addCourse.click();
        waitforAjax();
        return new EditGroupPage(driver);
    }




}
