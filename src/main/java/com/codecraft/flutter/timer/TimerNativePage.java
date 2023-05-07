package com.codecraft.flutter.timer;

import com.codecraft.flutter.forms.FormsNativePage;
import com.codecraft.flutter.utils.Base;
import com.codecraft.flutter.utils.DriverFactory;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class TimerNativePage extends Base {
    public TimerNativePageObjRepo tnp;

    private static final Logger logger = LogManager.getLogger(FormsNativePage.class.getName());

    public TimerNativePage() {
        super(DriverFactory.getDriverForFramework());
        tnp = new TimerNativePageObjRepo(driver);
    }

    @Step(value = "Verification of clock elements...{method}")
    public boolean verifyClockOpened(){
        switchContext("NATIVE_APP").manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return tnp.selectTimeElement.isDisplayed();
    }

    @Step(value = "Change the clock time...{method}")
    public void changeClockTime(String hr, String min){
        tnp.switchTextInput.click();
        tnp.timeInputHr.click();
        tnp.timeInputHr.sendKeys(hr);
        tnp.timeInputMin.click();
        tnp.timeInputMin.sendKeys(min);
        tnp.okElement.click();
        switchContext("FLUTTER");
    }
}

