package com.codecraft.flutter.swipe;

import com.codecraft.flutter.slide.SliderPageObjRepo;
import com.codecraft.flutter.utils.Direction;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class SwipePage extends FlutterBase {

    public SwipePageObjRepo sp;

    public SwipePage(){
        super(DriverFactory.getDriverForFramework());
        sp = new SwipePageObjRepo(driver);
    }


    public boolean swipeLeftAction() throws InterruptedException {
        slideToElement(sp.lisaElement, Direction.LEFT);
        Thread.sleep(5000);
        return !isVisisble(sp.lisaElement);
    }

    public boolean swipeRightAction() throws InterruptedException {
        slideToElement(sp.lisaElement, Direction.RIGHT);
        Thread.sleep(5000);
        return !isVisisble(sp.lisaElement);
    }
}
