package com.codecraft.flutter.press;

import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;

public class LongPressPage extends FlutterBase {

    public LongPressPageObjRepo pp;

    public LongPressPage(){
        super(DriverFactory.getDriverForFramework());
        pp = new LongPressPageObjRepo(driver);
    }


    public boolean pressAction(){
        pressGesture(pp.imageElement);
        return isVisisble(pp.closeElement);
    }
}
