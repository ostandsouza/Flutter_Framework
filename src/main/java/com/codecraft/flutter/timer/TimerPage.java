package com.codecraft.flutter.timer;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.tap.TapPageObjRepo;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class TimerPage extends FlutterBase {

    public TimerPageObjRepo tp;

    public TimerPage(){
        super(DriverFactory.getDriverForFramework());
        tp = new TimerPageObjRepo(driver);
    }

    public boolean openClock(){
        tp.clockElement.click();
        return Pages.TimerNativePage().verifyClockOpened();
    }

    public String setClock(String hr, String min){
        Pages.TimerNativePage().changeClockTime(hr, min);
        return tp.clockKeyElement.getText();
    }
}
