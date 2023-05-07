package com.codecraft.flutter.UPITest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UPITest extends TestBase {


    @Test()
    public void wrapperTestcase() {

        Pages.LandingPage().gotoWrapper();

        Assert.assertTrue(Pages.UPIPage().incrementAction());

        Assert.assertTrue(Pages.UPINativePage().enterContactDetails());

        Assert.assertTrue(Pages.UPINativePage().enterCardDetails());

        Assert.assertTrue(Pages.UPIPage().enterOTP());

    }
}
