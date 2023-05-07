package com.codecraft.flutter.ScrollTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScrollTest extends TestBase {


    @Test
    public void scrollTestcase() {

        Pages.LandingPage().gotoScroll();

        Assert.assertTrue(Pages.ScrollPage().scrollActionDown());

        Assert.assertTrue(Pages.ScrollPage().scrollActionUp());

    }
}
