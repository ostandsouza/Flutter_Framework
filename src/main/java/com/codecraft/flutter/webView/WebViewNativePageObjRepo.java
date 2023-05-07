package com.codecraft.flutter.webView;

import com.codecraft.flutter.utils.NativeObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebViewNativePageObjRepo extends NativeObject {

    public AppiumDriver driver;

    @FindBy(xpath = "//*[@name='q']")
    protected WebElement searchElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditTex")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Google Search\"]/following-sibling:: XCUIElementTypeOther[1]\n")
    protected WebElement editElement;

    public WebViewNativePageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }
}
