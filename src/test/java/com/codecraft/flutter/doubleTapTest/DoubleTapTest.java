package com.codecraft.flutter.doubleTapTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DoubleTapTest extends TestBase {


    @Test
    public void doubleTestcase(){

        Pages.LandingPage().gotoDoubleTap();

        Assert.assertTrue(Pages.DoubleTapPage().doubleTapAction());

    }
}
