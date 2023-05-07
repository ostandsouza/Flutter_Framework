package com.codecraft.flutter.doubleTap;

import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;

public class DoubleTapPage extends FlutterBase {
    public DoubleTapPageObjRepo dp;

    public DoubleTapPage() {
        super(DriverFactory.getDriverForFramework());
        dp = new DoubleTapPageObjRepo(driver);
    }


    public boolean doubleTapAction() {
//        multipleTapGesture(find.bySemanticsLabel("Double Tap Me"),2);
        dp.doubleTapElement.click();
        dp.doubleTapElement.click();
        dp.doubleTapElement.click();
        return isVisisble(dp.DoubleTapSuccessElement);
    }
}
//        multipleTapGesture(dp.doubleTap