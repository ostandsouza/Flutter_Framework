package com.codecraft.flutter.doubleTap;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class DoubleTapPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public DoubleTapPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement doubleTapElement = find.bySemanticsLabel("Double Tap Me");

    protected FlutterElement DoubleTapSuccessElement = find.bySemanticsLabel("DoubleTap Successful");
}
