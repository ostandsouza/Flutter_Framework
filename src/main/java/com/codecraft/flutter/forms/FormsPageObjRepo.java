package com.codecraft.flutter.forms;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class FormsPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public FormsPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement firstNameElement = find.byValueKey("first_Key");

    protected FlutterElement lastNameElement = find.byValueKey("last_Key");

    protected FlutterElement emailElement = find.byValueKey("email_Key");

    protected FlutterElement dateElement = find.byValueKey("date_Key");

    protected FlutterElement maleElement = find.byValueKey("male_Key");

    protected FlutterElement femaleElement = find.byValueKey("female_Key");

    protected FlutterElement dropElement = find.byValueKey("drop_Key");

    protected FlutterElement checkElement = find.byValueKey("check_Key");

    protected FlutterElement submitElement = find.byValueKey("submit_Key");

    protected FlutterElement countryElement = find.text("Russia");

    protected FlutterElement successElement = find.byValueKey("success_Key");
}
