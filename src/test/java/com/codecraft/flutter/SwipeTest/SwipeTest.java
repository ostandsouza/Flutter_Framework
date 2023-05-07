package com.codecraft.flutter.SwipeTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwipeTest extends TestBase {

    @Test
    public void swipeTestcase() throws InterruptedException {

        Pages.LandingPage().gotoSwipe();

        Assert.assertTrue(Pages.SwipePage().swipeLeftAction());

        Pages.SwipePage().goBackToPreviousPage();

        Pages.LandingPage().gotoSwipe();

        Assert.assertFalse(Pages.SwipePage().swipeRightAction());

    }
}
