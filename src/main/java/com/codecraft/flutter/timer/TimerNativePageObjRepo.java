package com.codecraft.flutter.timer;

import com.codecraft.flutter.utils.NativeObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class TimerNativePageObjRepo extends NativeObject {

    public AppiumDriver driver;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(accessibility = "SELECT TIME")
    @iOSXCUITFindBy(accessibility = "SELECT TIME")
    protected WebElement selectTimeElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"CANCEL\"]/preceding-sibling::android.widget.Button[1]")
    @iOSXCUITFindBy(accessibility = "Switch to text input mode")
    protected WebElement switchTextInput;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='ENTER TIME']/following-sibling::android.widget.EditText[1]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"Hour\"`]")
    protected WebElement timeInputHr;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='ENTER TIME']/following-sibling::android.widget.EditText[2]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"Minute\"`]")
    protected WebElement timeInputMin;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(accessibility = "OK")
    @iOSXCUITFindBy(accessibility = "OK")
    protected WebElement okElement;

    public TimerNativePageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

}