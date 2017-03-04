package com.deo.tw.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by chandrad on 8/1/16.
 */
public class CourseDetailPage extends BasePage<CourseDetailPage> {

    @FindBy(className = "cd-header-title")
    private WebElement courseDetailPageLoad;

    @FindBy(id = "cd-share")
    private WebElement shareCourseIcon;

    @FindBy(className = "lighter")
    private WebElement courseName;

    @FindBy(className = "tc-course-type")
    private WebElement courseType;

    @FindBy(xpath = "//h4[text()=' Share a Course']")
    private WebElement shareModalTitle;

    @FindBy(xpath = "//input[@class='tt-query']")
    private WebElement userSuggestTextField;

    @FindBys(@FindBy(xpath = "//span[@class='tt-dropdown-menu']//p"))
    private List<WebElement> userSuggest;

    @FindBy(xpath = "//h4[text()=' Share a Course']//ancestor::div[@class='modal-content']//button[contains(text(),'Save')]")
    private WebElement saveCourseShare;

    @FindBy(xpath = "//h4[text()=' Share a Course']//ancestor::div[@class='modal-content']//button[text()='Cancel']")
    private WebElement cancelCourseShare;

    @FindBy(xpath = "//h4[text()=' Share a Course']//ancestor::div[@class='modal-content']//i[@class='icon-remove white']")
    private WebElement closeShareModal;

    @FindBy(id = "cd-recentreaders")
    private WebElement recentParticipantsSection;

    @FindBys(@FindBy(xpath = "//span[@class='name']/a"))
    private List<WebElement> recentParticipantsName;

    @FindBys(@FindBy(xpath = "//span[@class='email']//smaller"))
    private List<WebElement> recentParticipantsEmail;

    public CourseDetailPage(WebDriver driver) {
        super(driver);
        long start = System.currentTimeMillis();
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println("Total Time for " + this.getClass().getSimpleName() + " : " +totalTime + "ms");
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(courseDetailPageLoad);
    }

    @Override
    protected void instantiatePage(CourseDetailPage page) {
        PageFactory.initElements(driver,page);
    }

    public CourseDetailPage validateCourse(String CourseName){
        Assert.assertEquals("Course Details Page for course did not match", CourseName,courseName.getText());
        return this;
    }


    public CourseDetailPage getShareUsers(String userSuggestText){
        shareCourseIcon.click();
        waitforAjax();
        waitForElement(ExpectedConditions.visibilityOf(shareModalTitle));
        userSuggestTextField.sendKeys(userSuggestText);
        waitforAjax();
        waitForElement(ExpectedConditions.visibilityOfAllElements(userSuggest));
        for (WebElement user: userSuggest) {
            System.out.println(user.getText());
        }
        return this;
    }
}

