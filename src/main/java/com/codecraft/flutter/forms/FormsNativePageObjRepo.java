package com.codecraft.flutter.forms;

import com.codecraft.flutter.utils.NativeObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class FormsNativePageObjRepo extends NativeObject {

    public AppiumDriver driver;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Select year\"]/preceding-sibling::android.widget.Button")
    @iOSXCUITFindBy(accessibility = "Switch to input")
    protected WebElement switchToInputElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label CONTAINS \"Enter Date\"`]")
    protected WebElement editElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"OK\"]")
    @iOSXCUITFindBy(accessibility = "OK")
    protected WebElement okElement;

    public FormsNativePageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

}