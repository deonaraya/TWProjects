package com.deo.tw.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by chandrad on 8/1/16.
 */
public class TrainingCatalogPage extends BasePage<TrainingCatalogPage> {

    @FindBy(className = "sortCatalogCustom")
    private WebElement catalogPageLoad;

    @FindBy(id = "catalog-grid-view")
    private WebElement gridView;

    @FindBy(id = "catalog-list-view")
    private WebElement listView;

    @FindBy(className = "icon-caret-down")
    private WebElement openSort;

    @FindBy(className ="qa_automation_Name")
    private WebElement sortByName;

    @FindBy(id ="qa_automation_Delivery Type")
    private WebElement sortByDeliveryType;

    @FindBy(xpath ="//a[@data-icon-before='sort-descending']")
    private WebElement sortAscending;

    @FindBy(xpath ="//a[@data-icon-before='sort-ascending']")
    private WebElement sortDescending;

    @FindBy(xpath ="(.//div[@class='VS-search-inner'])[1]")
    private WebElement filterOpen;

    @FindBy(xpath ="//div[@class='search_input ui-menu not_editing not_selected']")
    private WebElement filterOpen4;

    @FindBy(xpath = "//div[text()='Filter By Categories / Location / Type / ExtendedMetadata']")
    private WebElement filterOpen3;

    @FindBy(className ="ui-autocomplete-input")
    private WebElement filter;

    @FindBy(xpath = "//input[@class='search_facet_input ui-menu VS-interface ui-autocomplete-input']")
    private WebElement filterOpen2;

    @FindBys(@FindBy(xpath = "//li[@role='presentation']/a"))
    private List<WebElement> filterLevel1;

    @FindBy(xpath ="(.//ul[contains(@id,'ui-id-')])[1]")
    private WebElement filterBlock;

    @FindBy(id ="tc-search1")
    private WebElement searchField;

    @FindBy(xpath ="//input[@value='all')]")
    private WebElement dateFilterAll;

    @FindBy(xpath ="//input[@value='1week']")
    private WebElement dateFilter1week;

    @FindBy(xpath ="//input[@value='1month']")
    private WebElement dateFilter1month;

    @FindBy(xpath ="//input[@value='3months']")
    private WebElement dateFilter3months;

    @FindBy(xpath ="//label[@class='tc-course-title']//a")
    private WebElement courseLink;

    @FindBy(xpath ="//label[@class='tc-course-title']//a")
    private List<WebElement> courseTitle;

    @FindBy(className ="tc-course-type")
    private List<WebElement> courseType;

    @FindBy(xpath ="//div[@class='tc-course-section-inner']/p")
    private List<WebElement> courseDescription;

    @FindBy(id ="tc-catid")
    private List<WebElement> categoryName;

    @FindBy(xpath ="//div[contains(@class,'tc-upcoming-sessions')]")
    private List<WebElement> sessionDetails;

    @FindBy(className ="align-center")
    private WebElement pageCount ;

    @FindBy(xpath ="//ul[@class='pagination']//input[contains(@class,'pageVal')]")
    private WebElement pageNav;

    By spinner = By.xpath(".//i[@class='icon-spin icon-spinner-dots loading-icon bigger-230']");

    public TrainingCatalogPage(WebDriver driver) {
        super(driver);
        long start = System.currentTimeMillis();
        instantiatePage(this);
       // waitforAjax();
        waitForElement(getPageLoadCondition());
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println("Total Time for " + this.getClass().getSimpleName() + ":" +totalTime + "ms");
    }
    @Override
    protected void instantiatePage(TrainingCatalogPage page) {
        PageFactory.initElements(driver, page);
    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(catalogPageLoad);
    }

    public WebElement filterBy(String filterData){
       WebElement filterByText  = driver.findElement(By.xpath("//li[@role='presentation']/a[text()='" +filterData+ "']"));
        return filterByText;
    }

    public CourseDetailPage navToCourseDetail(){
        courseLink.click();
        waitforAjax();
        return new CourseDetailPage(driver);
    }

    public TrainingCatalogPage searchCoursebyName(String courseName){
        searchField.sendKeys("\""+courseName+"\"");
        searchField.sendKeys(Keys.ENTER);
        waitforAjax();
        return this;
    }

    public TrainingCatalogPage filterAndAssert() throws InterruptedException {

        Actions actions = new Actions(driver);

        actions.moveToElement(filterOpen);
        actions.clickAndHold(filterOpen);
        actions.release();

        actions.click(filterOpen);
        actions.doubleClick();
        actions.contextClick();

        driver.findElement(By.className("VS-search-box-wrapper")).click();
       // driver.findElement(By.className("VS-placeholder")).click();

System.out.print("modal not opening");
        Thread.sleep(2000);


        filter.sendKeys("Category");
      //  filter.sendKeys(Keys.ARROW_DOWN);
        filter.sendKeys(Keys.TAB);
      //  filter.clear();
     //   filter.sendKeys("Category");
      //  filter.sendKeys(Keys.ARROW_DOWN);

        for (WebElement we: filterLevel1)
        {
            System.out.println(we.getText());
        }

        Thread.sleep(2000);

          filterOpen2.sendKeys("Finance");
       //   filterOpen2.sendKeys(Keys.ARROW_DOWN);
          filterOpen2.sendKeys(Keys.TAB);


//        waitForElement(ExpectedConditions.visibilityOf(filterBy("Category")));
//        filterBy("Category").click();

        for (WebElement we: filterLevel1)
        {
            System.out.println(we.getText());
        }


     //   filterBy("Finance").click();




//        Thread.sleep(2000);
//        filter.sendKeys("Category");
//        filter.sendKeys(Keys.ARROW_DOWN);
//        filter.sendKeys(Keys.ENTER);
//
//        Thread.sleep(2000);
//        filterBy("Galaxy").click();
//        for (WebElement we: filterLevel1)
//        {
//            System.out.println(we);
//        }
//      //  filterOpen2.sendKeys("Galaxy");
      //  filterOpen2.sendKeys(Keys.ARROW_DOWN);
      //  filterOpen2.sendKeys(Keys.ENTER);
        waitforAjax();
        return this;
    }

    public TrainingCatalogPage validateContent(){
        for (WebElement a:courseTitle) {
            System.out.println(a.getText());
        }

        for (WebElement b:courseDescription) {
            System.out.println(b.getText());
        }

        for (WebElement c:courseType) {
            System.out.println(c.getText());
        }

        for (WebElement d:categoryName) {
            System.out.println(d.getText());
        }

        for (WebElement e:sessionDetails) {
            System.out.println(e.getText());
        }

        return this;
    }

    public void waitForPageToLoad(WebDriver driver) {
        ExpectedCondition < Boolean > pageLoad = new
                ExpectedCondition < Boolean > () {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return jQuery.active").equals(0);
                    }
                };
        Wait< WebDriver > wait = new WebDriverWait(driver, 60);

            wait.until(pageLoad);

    }
}
