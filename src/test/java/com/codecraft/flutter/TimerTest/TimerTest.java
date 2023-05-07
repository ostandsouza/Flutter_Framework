package com.codecraft.flutter.TimerTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TimerTest extends TestBase {


    @Test
    public void timerTestcase() {

        Pages.LandingPage().gotoTimer();

        Assert.assertTrue(Pages.TimerPage().openClock());

        Assert.assertTrue(Pages.TimerPage().setClock("12","00").contains(":"));

    }
}
