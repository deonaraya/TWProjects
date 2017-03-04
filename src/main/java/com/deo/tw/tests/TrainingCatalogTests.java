package com.deo.tw.tests;

import com.deo.tw.pages.SignInPage;
import org.testng.annotations.Test;

/**
 * Created by chandrad on 8/7/16.
 */
public class TrainingCatalogTests extends BaseTest {

    @Test
    public void checkFw() throws InterruptedException {
        SignInPage signIn = new SignInPage(driver);
        signIn.signInSuccess("deonaraya1@gmail.com","Cisco@1234").navToTrainingCatalog().filterAndAssert().validateContent();
    }

    @Test
    public void courseShare(){
        SignInPage signIn = new SignInPage(driver);
        signIn.signInSuccess("deonaraya1@gmail.com","Cisco@1234").
                navToTrainingCatalog().searchCoursebyName("Bluebeam: On the iPad")
                .navToCourseDetail().validateCourse("Bluebeam: On the iPad").getShareUsers("CHU");

    }

}