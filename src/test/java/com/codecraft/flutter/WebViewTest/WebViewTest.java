package com.codecraft.flutter.WebViewTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebViewTest extends TestBase {

    @Test
    public void webEnabledTestcase() throws InterruptedException {
        Pages.LandingPage().gotoWebViewT();

        Thread.sleep(1000);

        Pages.WebViewNativePage().webSearch();

    }

    @Test
    public void webViewDisabledTestcase() throws InterruptedException {

        Pages.LandingPage().gotoWebViewF();

        Pages.WebViewNativePage().nativeSearch();

    }

    @AfterMethod
    public void goBack() {
        Pages.WebViewNativePage().backToFlutter();
    }


}
