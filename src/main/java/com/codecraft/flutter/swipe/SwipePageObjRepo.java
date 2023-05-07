package com.codecraft.flutter.swipe;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class SwipePageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public SwipePageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement lisaElement = find.byValueKey("Lisa_Msg");

}

