package com.codecraft.flutter.FormsTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FromsTest extends TestBase {


    @Test
    public void formTestcase() {

        Pages.LandingPage().gotoForms();

        Assert.assertTrue(Pages.FormsPage().verifyFormElements());

        Assert.assertTrue(Pages.FormsPage().enterFirstName());

        Assert.assertTrue(Pages.FormsPage().enterLastName());

        Assert.assertTrue(Pages.FormsPage().enterEmailName());

        Assert.assertTrue(Pages.FormsPage().enterDate());

        Assert.assertTrue(Pages.FormsPage().selectGender());

        Assert.assertTrue(Pages.FormsPage().selectCountry());

        Assert.assertTrue(Pages.FormsPage().selectTnC());

        Assert.assertTrue(Pages.FormsPage().submitForm());
    }
}