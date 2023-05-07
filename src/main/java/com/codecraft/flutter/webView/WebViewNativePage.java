package com.codecraft.flutter.webView;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.forms.FormsNativePage;
import com.codecraft.flutter.utils.Base;
import com.codecraft.flutter.utils.DriverFactory;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class WebViewNativePage extends Base {

    public WebViewNativePageObjRepo wnp;

    private static final Logger logger = LogManager.getLogger(FormsNativePage.class.getName());

    public WebViewNativePage() {
        super(DriverFactory.getDriverForFramework());
        wnp = new WebViewNativePageObjRepo(driver);
    }

    @Step(value = "Web Search...{method}")
    public void webSearch(){
        switchContext("WEBVIEW").manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wnp.searchElement.sendKeys("Abcd");
    }

    @Step(value = "Native Search...{method}")
    public void nativeSearch() throws InterruptedException {
        switchContext("NATIVE_APP").manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Thread.sleep(2000);

        wnp.editElement.sendKeys("Abcd");
    }

    @Step(value = "Back to previous page...{method}")
    public void backToFlutter() {
        switchContext("FLUTTER").manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Pages.LandingPage().goBackToPreviousPage();
    }

}
