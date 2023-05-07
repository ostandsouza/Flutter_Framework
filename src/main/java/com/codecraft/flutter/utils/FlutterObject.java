package com.codecraft.flutter.utils;

import com.codecraft.flutter.utils.kotlin.FlutterFinder;
import io.appium.java_client.AppiumDriver;

public class FlutterObject {

    public FlutterFinder find;

    public FlutterObject(AppiumDriver driver){
        find = new FlutterFinder(driver);
    }
}
