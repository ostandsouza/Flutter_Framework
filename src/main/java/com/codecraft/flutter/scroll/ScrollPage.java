package com.codecraft.flutter.scroll;

import com.codecraft.flutter.utils.Direction;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;

public class ScrollPage extends FlutterBase {

    public ScrollPageObjRepo sp;

    public ScrollPage(){
        super(DriverFactory.getDriverForFramework());
        sp = new ScrollPageObjRepo(driver);
    }


    public boolean scrollActionDown(){
        scrollToElement(sp.listElement, sp.gaganElement, Direction.DOWN);
        return  isVisisble(sp.gaganElement);
    }

    public boolean scrollActionUp(){
        scrollToElement(sp.listElement, sp.janeElement, Direction.UP);
        return  isVisisble(sp.janeElement);
    }
}
