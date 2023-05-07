package com.codecraft.flutter.upi;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class UPIPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public UPIPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement incrementElement = find.byTooltip("Increment");

    protected FlutterElement successPaymentElement = find.bySemanticsLabel("Payment Successful");
}
