package com.codecraft.flutter.upi;

import com.codecraft.flutter.utils.NativeObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class UPINativePageObjRepo extends NativeObject {

    public AppiumDriver driver;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='merchant-name']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Phone Number\"`]")
    protected WebElement merchantNameElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='contact']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Phone Number\"`]")
    protected WebElement contactElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='email']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Email\"`]")
    protected WebElement emailElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='footer']")
    @iOSXCUITFindBy(accessibility = "Proceed")
    @iOSXCUITFindBy(accessibility = "Pay Now")
    protected WebElement footerElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='user-details']")
    @iOSXCUITFindBy(accessibility = "Allow Once")
    protected WebElement userDetailElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[contains(@text,'Pay using Card')]")
    @iOSXCUITFindBy(accessibility = "Pay using Card")
    protected WebElement cardElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='otp-sec']")
    @iOSXCUITFindBy(accessibility = "Pay without Saving Card")
    protected WebElement otpElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_number']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Card Number\"]/preceding-sibling:: XCUIElementTypeTextField\n")
    protected WebElement cardNumberElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_expiry']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Expiry\"]/preceding-sibling:: XCUIElementTypeTextField")
    protected WebElement cardExpiryElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_name']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Card Holder's name\"]/preceding-sibling:: XCUIElementTypeTextField")
    protected WebElement cardNameElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_cvv']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"CVV\"]/preceding-sibling:: XCUIElementTypeTextField")
    protected WebElement cardCVVElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='otpForm']/android.widget.EditText")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value == \"OTP\"`]")
    protected WebElement otpFormElement;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='merchant-name']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Hide alert forever !\"`]")
    protected WebElement hideAlert;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(xpath = "Submit")
    @iOSXCUITFindBy(iOSClassChain = "v")
    protected WebElement submitOTP;

    public UPINativePageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

}