package com.codecraft.flutter.carousel;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class CarouselPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public CarouselPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }


    protected FlutterElement carouselListElement = find.byValueKey("carouselList");

    protected FlutterElement fifthImgElement = find.bySemanticsLabel("assets/fifth.jpeg");

    protected FlutterElement secondImgElement = find.bySemanticsLabel("assets/second.jpeg");

}
