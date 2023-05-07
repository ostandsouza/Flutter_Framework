package com.codecraft.flutter.carouselTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarouselTest extends TestBase {


    @Test
    public void carouselTestcase() {

        Pages.LandingPage().gotoCarousel();

        Assert.assertTrue(Pages.CarouselPage().carouselRightAction());
        Pages.CarouselPage().goBackToPreviousPage();

        Pages.LandingPage().gotoCarousel();
        Assert.assertTrue(Pages.CarouselPage().carouselLeftAction());

    }
}
