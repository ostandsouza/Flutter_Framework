package com.codecraft.flutter.tapTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TapTest extends TestBase {


    @Test
    public void tapTestcase() {

        Pages.LandingPage().gotoTap();

        Pages.TapPage().tapAction();

    }

}
