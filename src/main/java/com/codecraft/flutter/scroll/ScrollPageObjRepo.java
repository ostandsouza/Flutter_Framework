package com.codecraft.flutter.scroll;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class ScrollPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public ScrollPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement listElement = find.byType("ListView");

    protected FlutterElement gaganElement = find.byValueKey("Gagan_Key");

    protected FlutterElement janeElement = find.byValueKey("Jane_Key");
}
