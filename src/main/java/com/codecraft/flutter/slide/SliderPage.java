package com.codecraft.flutter.slide;

import com.codecraft.flutter.scroll.ScrollPageObjRepo;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SliderPage extends FlutterBase {

    public SliderPageObjRepo sp;

    public SliderPage(){
        super(DriverFactory.getDriverForFramework());
        sp = new SliderPageObjRepo(driver);
    }


    public boolean sliderAction() throws InterruptedException {
        sliderGesture(sp.sliderElement,50);
        return  isVisisble(sp.sliderSuccessElement);
    }
}
