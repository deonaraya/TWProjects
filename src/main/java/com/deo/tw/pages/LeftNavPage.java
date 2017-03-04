package com.deo.tw.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by chandrad on 7/31/16.
 */
public class LeftNavPage extends BasePage<LeftNavPage> {

    @FindBy(id = "sidebar")
    private WebElement leftNavLoad;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/vkm']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement workSpace;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/vkm']")
    public WebElement vkm ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/activity']")
    public WebElement activityStream ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/formalLearning/catalog']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement learning;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/formalLearning/catalog']")
    public WebElement trainingCatalog ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/Learning/enrollments?reporteesSort=progress']")
    public WebElement myEnrollments ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/Learning/shared']")
    public WebElement sharedLearning;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/Learning/learningPlans']")
    public WebElement plp ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/documents/catalog']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement knowledgeCenter;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/documents/catalog']")
    public WebElement knowledgeLibrary ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/documents/my']")
    public WebElement myFiles ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/documents/shared']")
    public WebElement sharedFies ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/communities/all']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement collab;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/communities/all']")
    public WebElement communities ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/communities/my']")
    public WebElement mycommunities ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/questions']")
    public WebElement discussions ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/wikis']")
    public WebElement wiki ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/rssfeeds']")
    public WebElement rssFeeds ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/people/contacts']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement people;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/people/contacts']")
    public WebElement allPeople ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/people/expertUsers']")
    public WebElement experts ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/collections']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement myCollections;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/collections']")
    public WebElement collections ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/notes']")
    public WebElement notes ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/sharedItems']")
    public WebElement sharedFolders ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/drives']")
    public WebElement externalData ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/myTeam']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement manager;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/myTeam']")
    public WebElement myTeam ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/pendingApprovals']")
    public WebElement pendingAprovals ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/user']/ancestor::ul[@class='submenu']//preceding-sibling::button/span")
    public WebElement admin;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/user']")
    public WebElement user ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/system']")
    public WebElement system ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/mobile']")
    public WebElement mobile ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/collaborate']")
    public WebElement collaborate ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/reporting']")
    public WebElement reporting ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/personalizeSite']")
    public WebElement personalizeSite ;

    @FindBy(xpath = "//ul[@class='submenu']//a[@href='#/admin/emailpreferences']")
    public WebElement emailPrederences ;

    public LeftNavPage(WebDriver driver) {
        super(driver);
        long start = System.currentTimeMillis();
        instantiatePage(this);
        waitforAjax();
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println("Total Time for " + this.getClass().getSimpleName() + ":" +totalTime + "ms");
    }

    @Override
    protected void instantiatePage(LeftNavPage page) {
          PageFactory.initElements(driver, page);
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(leftNavLoad);
    }

    public TrainingCatalogPage navToTrainingCatalog(){
        learning.click();
        trainingCatalog.click();
        return new TrainingCatalogPage(driver);
    }

    public CourseDetailPage navToSharedLearning(){
        learning.click();
        sharedLearning.click();
        return new CourseDetailPage(driver);
    }

    public KnowledgeLibraryPage navToKnowledgeLibrary(){
        knowledgeCenter.click();
        knowledgeLibrary.click();
        return new KnowledgeLibraryPage(driver);
    }

    public MyFilesPage navToMyFiles(){
        knowledgeCenter.click();
        myFiles.click();
        return new MyFilesPage(driver);
    }

    public UserPage navToUser(){
        admin.click();
        user.click();
        return new UserPage(driver);
    }
}

