package com.deo.tw.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by chandrad on 8/7/16.
 */
public class SignInPage extends BasePage<SignInPage> {

    @FindBy(className = "msg_page-box")
    private WebElement ckLoginFrame;

    @FindBy(name = "email")
    private WebElement emailTextField;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    @FindBy(className = "brandingLoginForm")
    private WebElement cciLoginFrame;

    @FindBy(id = "IDToken2")
    private WebElement passwordTextField;

    @FindBy(name = "Login.Submit")
    private WebElement signinButton;

    @FindBy(xpath = "//div[@id='termsAndConditions']//h4[@class='modal-title']")
    private WebElement tncModalHeader;

    @FindBy(xpath = "//div[@id='termsAndConditions']//label/input")
    private WebElement tncCheckBox;

    @FindBy(xpath = "//div[@id='termsAndConditions']//button[@class='btn btn-primary' and text()='Continue']")
    private WebElement tncContinue;





    public SignInPage(WebDriver driver) {
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
         return ExpectedConditions.visibilityOf(ckLoginFrame);
    }

    @Override
    protected void instantiatePage(SignInPage page) {
        PageFactory.initElements(driver,page);

    }

    public LeftNavPage signInSuccess(String username, String password){
        emailTextField.sendKeys(username);
        nextButton.click();
        waitForElement(ExpectedConditions.visibilityOf(cciLoginFrame));
        passwordTextField.sendKeys(password);
        signinButton.click();
        waitforAjax();
        //WebElement b = new WebDriverWait(driver,90).until(ExpectedConditions.presenceOfElementLocated(By.className("user-info")));
        //System.out.println(b.getText());

        if (tncModalHeader.isDisplayed())
        {handleTnC();}
        else
        System.out.println("TNC did not appear");

        return new LeftNavPage(driver);
    }


    public void handleTnC(){

        tncCheckBox.click();
        tncContinue.click();

    }


}
