package com.codecraft.flutter.tap;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class TapPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public TapPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement switchElement = find.bySemanticsLabel("switch");

    protected FlutterElement tapSuccessElement = find.bySemanticsLabel("Tap Successful");
}
