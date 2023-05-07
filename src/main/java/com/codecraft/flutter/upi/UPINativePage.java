package com.codecraft.flutter.upi;

import com.codecraft.flutter.forms.FormsNativePage;
import com.codecraft.flutter.timer.TimerNativePageObjRepo;
import com.codecraft.flutter.utils.Base;
import com.codecraft.flutter.utils.DriverFactory;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.time.Duration;

public class UPINativePage extends Base {

    public UPINativePageObjRepo upin;

    private static final Logger logger = LogManager.getLogger(FormsNativePage.class.getName());

    public UPINativePage() {
        super(DriverFactory.getDriverForFramework());
        upin = new UPINativePageObjRepo(driver);
    }

    @Step(value = "Verification of payment gateway...{method}")
    public boolean verifyUPIOpened(){
        switchContext("NATIVE_APP").manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if(isElementVisible(upin.hideAlert))
            upin.hideAlert.click();
        waitUntilElementIsVisible(upin.merchantNameElement);
        return upin.merchantNameElement.isDisplayed();
    }

    @Step(value = "Enter Contact Details...{method}")
    public boolean enterContactDetails(){
        upin.contactElement.sendKeys("9874563215");
        if(isElementVisible(upin.emailElement))
            upin.emailElement.sendKeys("abc@gmail.com");
        upin.footerElement.click();
        return upin.cardElement.isDisplayed();
    }

    @Step(value = "Enter Card Details...{method}")
    public boolean enterCardDetails(){
        upin.cardElement.click();
        if(isElementVisible(upin.otpElement))
            upin.otpElement.click();
        upin.cardNumberElement.sendKeys("4111111111111111");
        upin.cardExpiryElement.sendKeys("12/23");
        upin.cardNameElement.sendKeys("abcd");
        upin.cardCVVElement.sendKeys("666");
        upin.footerElement.click();
        if(isElementVisible(upin.otpElement))
            upin.otpElement.click();
        return upin.otpFormElement.isDisplayed();
    }

    @Step(value = "Enter OTP...{method}")
    public void enterOTP(){
        upin.otpFormElement.sendKeys("1234");
        upin.submitOTP.click();
        switchContext("FLUTTER");
    }
}
