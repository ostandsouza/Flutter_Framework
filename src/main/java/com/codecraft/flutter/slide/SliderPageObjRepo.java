package com.codecraft.flutter.slide;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class SliderPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public SliderPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement sliderElement = find.byValueKey("slide");

    protected FlutterElement sliderSuccessElement = find.bySemanticsLabel("Slider moved Successful");
}

