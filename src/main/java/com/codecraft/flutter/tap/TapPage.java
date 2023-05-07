package com.codecraft.flutter.tap;

import com.codecraft.flutter.landing.LandingPageObjRepo;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class TapPage extends FlutterBase {
    public TapPageObjRepo tp;

    public TapPage(){
        super(DriverFactory.getDriverForFramework());
        tp = new TapPageObjRepo(driver);
    }

    public boolean tapAction(){
        tp.switchElement.click();
        return  isVisisble(tp.tapSuccessElement);
    }

    public boolean doubleTapAction(){
        multipleTapGesture(find.bySemanticsLabel("Double Tap Me"),2);
        return  isVisisble(find.bySemanticsLabel("DoubleTap Successful"));
    }
}
