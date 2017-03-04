package com.deo.tw.pages;

import org.openqa.selenium.By;
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
public class EditGroupPage extends BasePage<EditGroupPage> {

    @FindBy(id = "file-library-list")
    private WebElement editGroupPageLoad;

    @FindBy(xpath = "//div[@id='course-activity-inner']//i[@data-original-title='Bulk add courses']")
    private WebElement courseAssociate;

    @FindBys(@FindBy(xpath = "//div[@id='course-activity-inner']//p[@data-original-title='remove']"))
    private List<WebElement> removeCourseAssocateIcons ;

    @FindBy(xpath = "//div[@id='course-activity-inner']//p[@data-original-title='remove']")
    private WebElement removeCourseAssocateIcon ;

    @FindBy(xpath = "//div[@class='success align-right']")
    private WebElement courseRemoveSuccess;

    @FindBys(@FindBy(xpath = "//div[contains(@class,'create-user-group-members')]//p[@data-original-title='remove']"))
    private List<WebElement> removeUserAssociationIcon ;

    @FindBy(id = "courseCatPLPSearchBox")
    private WebElement courseSearch ;


    public EditGroupPage(WebDriver driver) {
        super(driver);
        long start = System.currentTimeMillis();
        instantiatePage(this);
        waitforAjax();
      //  waitForElement(getPageLoadCondition());
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println("Total Time for " + this.getClass().getSimpleName() + ":" +totalTime + "ms");
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(editGroupPageLoad);
    }

    @Override
    protected void instantiatePage(EditGroupPage page) {
        PageFactory.initElements(driver,page);

    }

    public CourseAssociationPage navToCourseAssociatePage(){Actions actions = new Actions(driver);
       actions.moveToElement(courseAssociate);
        courseAssociate.click();
        waitforAjax();
        return new CourseAssociationPage(driver);
    }

    public EditGroupPage searchnRemoveAssociations(String courseName){
            courseSearch.sendKeys("\" "+courseName+" \"");
            courseSearch.sendKeys(Keys.ENTER);
            waitforAjax();
            removeCourseAssocateIcon.click();
        return this;
    }
    public EditGroupPage removeAssociations(){
           int i = 0;
        try {
            while (removeCourseAssocateIcon.isDisplayed()) {
                i += 1;

                removeCourseAssocateIcon.click();
                waitForElement(ExpectedConditions.visibilityOf(courseRemoveSuccess));
                waitForElement(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='success align-right']")));
                waitforAjax();
            }
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex) {
                removeCourseAssocateIcon.click();
                waitForElement(ExpectedConditions.visibilityOf(courseRemoveSuccess));
                waitForElement(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='success align-right']")));
                waitforAjax();
            }
               catch (org.openqa.selenium.NoSuchElementException ex){
                   ex.getLocalizedMessage();
               }

        System.out.println("loop run : " + i);
        return this;
    }
}
