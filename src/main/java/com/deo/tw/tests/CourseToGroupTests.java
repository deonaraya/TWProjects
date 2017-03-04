package com.deo.tw.tests;

import com.deo.tw.pages.SignInPage;
import org.testng.annotations.Test;

/**
 * Created by chandrad on 8/24/16.
 */
public class CourseToGroupTests extends BaseTest {

    @Test
    public void courseAssociate(){

        SignInPage signin = new SignInPage(driver);
        signin.signInSuccess("jessie@mailinator.com", "Cisco@123").
                navToUser().navToGroup().
                searchAndEdit("course_group1").
                navToCourseAssociatePage().
                checkAllnCancel().navToCourseAssociatePage().
                checkAllnSave().navToCourseAssociatePage().
           //     searchnSelectCourse("Rana Kelley");
        searchnSelectCourse("Maia Lowery");

    }

    @Test
    public void removeAllAssociations(){
        String courseName ="Maia Lowery";
      //  String courseName = "Rana Kelley";
        SignInPage signIn = new SignInPage(driver);
        signIn.signInSuccess("jessie@mailinator.com", "Cisco@123").
                navToUser().navToGroup()
                .searchAndEdit("course_group1").navToCourseAssociatePage().checkAllnSave().
                removeAssociations().
                navToCourseAssociatePage().searchnAssociateCourse(courseName);
    }
}
