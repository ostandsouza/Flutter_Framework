package com.codecraft.flutter.press;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class LongPressPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public LongPressPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement imageElement = find.bySemanticsLabel("image");

    protected FlutterElement closeElement = find.bySemanticsLabel("Close");
}
