package com.codecraft.flutter.landing;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class LandingPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public LandingPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement tapElement = find.bySemanticsLabel("Tap");

    protected FlutterElement doubleTapElement = find.byValueKey("DoubleTap_Key");

    protected FlutterElement verticalSwipeElement = find.byValueKey("Vertical Swipe_Key");

    protected FlutterElement LongPressElement = find.byValueKey("Long Press_Key");

    protected FlutterElement carouselElement = find.byValueKey("Carousel_Key");

    protected FlutterElement listElement = find.bySemanticsLabel("listElements");

    protected FlutterElement swipeLeftElement = find.byValueKey("Swipe Left_Key");

    protected FlutterElement sliderElement = find.byValueKey("Slider_Key");

    protected FlutterElement dragNDropElement = find.byValueKey("Drag n Drop_Key");

    protected FlutterElement webTrueElement = find.byValueKey("Web True_Key");

    protected FlutterElement webFalseElement = find.byValueKey("Web False_Key");

    protected FlutterElement formElement = find.byValueKey("Forms_Key");

    protected FlutterElement timerElement = find.byValueKey("Timer_Key");

    protected FlutterElement wrapperElement = find.byValueKey("Wrapper_Key");

}
