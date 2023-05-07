package com.codecraft.flutter.timer;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class TimerPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public TimerPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement clockElement = find.bySemanticsLabel("clock");

    protected FlutterElement clockKeyElement = find.byValueKey("clock_Key");
}
