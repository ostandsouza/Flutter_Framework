package com.codecraft.flutter.SlideTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SliderTest extends TestBase {


    @Test
    public void sliderTestcase() throws InterruptedException {

        Pages.LandingPage().gotoSlider();

        Assert.assertTrue(Pages.SliderPage().sliderAction());

    }

}
