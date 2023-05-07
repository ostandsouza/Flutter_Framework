package com.codecraft.flutter.forms;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.dragNdrop.DragNDropPageObjRepo;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class FormsPage extends FlutterBase {

    public FormsPageObjRepo fp;

    public FormsPage(){
        super(DriverFactory.getDriverForFramework());
        fp = new FormsPageObjRepo(driver);
    }

    public boolean verifyFormElements(){
        return  isVisisble(fp.firstNameElement) && isVisisble(fp.lastNameElement) && isVisisble(fp.emailElement) && isVisisble(fp.dateElement) && isVisisble(fp.maleElement) && isVisisble(fp.femaleElement) && isVisisble(fp.dropElement) && isVisisble(fp.checkElement) && isVisisble(fp.submitElement);
    }


    public boolean enterFirstName(){
        fp.firstNameElement.sendKeys("Ostan");
        return  fp.firstNameElement.getText().equalsIgnoreCase("Ostan");
    }

    public boolean enterLastName(){
        fp.lastNameElement.sendKeys("Dsouza");
        return  fp.lastNameElement.getText().equalsIgnoreCase("Dsouza");
    }

    public boolean enterEmailName(){
        fp.emailElement.sendKeys("Ostan@codecraft.co.in");
        return fp.emailElement.getText().equalsIgnoreCase("Ostan@codecraft.co.in");
    }

    public boolean enterDate() {
        fp.dateElement.click();
        Pages.FormsNativePage().dateSelection();
        return !fp.dateElement.getText().isEmpty();
    }

    public boolean selectGender(){
        fp.maleElement.click();
        return isVisisble(find.byValueKey("male_Key"));
    }

    public boolean selectCountry(){
        fp.dropElement.click();
        fp.countryElement.click();
        return isVisisble(fp.countryElement);
    }

    public boolean selectTnC(){
        fp.checkElement.click();
        return isVisisble(fp.checkElement);
    }

    public boolean submitForm(){
        fp.submitElement.click();
        return isVisisble(fp.successElement);
    }
}
