package com.codecraft.flutter.carousel;

import com.codecraft.flutter.doubleTap.DoubleTapPageObjRepo;
import com.codecraft.flutter.utils.Direction;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class CarouselPage extends FlutterBase {

    public CarouselPageObjRepo cp;

    public CarouselPage(){
        super(DriverFactory.getDriverForFramework());
        cp = new CarouselPageObjRepo(driver);
    }


    public boolean carouselRightAction(){
        slideToElement(cp.carouselListElement, Direction.LEFT);
        return !isVisisble(cp.fifthImgElement);
    }

    public boolean carouselLeftAction() {
        slideToElement(cp.carouselListElement, Direction.RIGHT);
        return !isVisisble(cp.secondImgElement);
    }
}
