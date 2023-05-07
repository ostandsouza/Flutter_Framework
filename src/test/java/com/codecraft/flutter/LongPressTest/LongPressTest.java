package com.codecraft.flutter.LongPressTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LongPressTest extends TestBase {


    @Test
    public void longPressTestcase(){

        Pages.LandingPage().gotoLongPress();

        Assert.assertTrue(Pages.LongPressPage().pressAction());

    }
}
