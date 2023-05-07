package com.codecraft.flutter.upi;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.timer.TimerPageObjRepo;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;

public class UPIPage extends FlutterBase {

    public UPIPageObjRepo upi;

    public UPIPage(){
        super(DriverFactory.getDriverForFramework());
        upi = new UPIPageObjRepo(driver);
    }


    public boolean incrementAction() {
        upi.incrementElement.click();
        return Pages.UPINativePage().verifyUPIOpened();
    }

    public boolean enterOTP() {
        Pages.UPINativePage().enterOTP();
        return isVisisble(upi.successPaymentElement);
    }

}
