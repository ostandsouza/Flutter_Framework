package com.codecraft.flutter.landing;

import com.codecraft.flutter.utils.Direction;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;

public class LandingPage extends FlutterBase {
    public LandingPageObjRepo lp;

    public LandingPage()  {
        super(DriverFactory.getDriverForFramework());
        lp = new LandingPageObjRepo(driver);
        waitUntilAppLoads();
    }

    public void gotoTap() {
        lp.tapElement.click();
    }

    public void gotoDoubleTap() {
        lp.doubleTapElement.click();

    }

    public void gotoScroll() {
        lp.verticalSwipeElement.click();
    }

    public void gotoLongPress() {
        lp.LongPressElement.click();
    }

    public void gotoCarousel(){
        scrollIntoView(lp.listElement);
        lp.carouselElement.click();
    }

    public void gotoSwipe() {
        lp.swipeLeftElement.click();
    }

    public void gotoSlider() {
        lp.sliderElement.click();
    }

    public void gotoDrag() {
        scrollToElement(lp.listElement,lp.dragNDropElement, Direction.DOWN);
        lp.dragNDropElement.click();
    }

    public void gotoWebViewT() {
        scrollToElement(lp.listElement,lp.webTrueElement,Direction.DOWN);
        lp.webTrueElement.click();
    }

    public void gotoWebViewF() {
        scrollToElement(lp.listElement,lp.webFalseElement,Direction.DOWN);
        lp.webFalseElement.click();
    }

    public void gotoForms() {
        scrollToElement(lp.listElement,lp.formElement,Direction.DOWN);
        lp.formElement.click();
    }

    public void gotoTimer() {
        scrollToElement(lp.listElement,lp.timerElement,Direction.DOWN);
        lp.timerElement.click();
    }

    public void gotoWrapper() {
//
//        System.out.println(driver.executeScript("flutter: getRenderTree"));
//
//        System.out.println(driver.executeScript("flutter: getLayerTree"));
//
//        System.out.println(driver.executeScript("flutter: getDiagnosticsTree"));
        scrollToElement(lp.listElement,lp.wrapperElement,Direction.DOWN);


//        scrollToElement(find.bySemanticsLabel("listElements"), find.byValueKey("Wrapper_Key"), Direction.DOWN);

//        scrollIntoView(find.byValueKey("Carousel_Key"));
//
//        scrollIntoView(find.byValueKey("Hybrid_Key"));

//        System.out.println(driver.executeScript("flutter:clearTimeline"));
//
//        System.out.println(driver.executeScript("flutter:forceGC"));
//
//        System.out.println(driver.executeScript("flutter:checkHealth"));
//
//        System.out.println(driver.executeScript(
//                "flutter:getSemanticsId",
//                find.bySemanticsLabel("listElements")));
//        System.out.println(driver.executeScript(
//                "flutter:getSemanticsId",
//                find.byValueKey("listview")));
//        System.out.println(driver.executeScript(
//                "flutter:getRenderObjectDiagnostics",
//                find.byValueKey("listview").getId(),
//                new HashMap<String, Object>() {{
//                    put("includeProperties", true);
//                    put("subtreeDepth", 2);
//                }}));
//        System.out.println(driver.executeScript(
//                "flutter:waitForAbsent",
//                find.byValueKey("Wrapper_Key")));
//        find.bySemanticsLabel("Tap").getRawMap().entrySet().stream().forEach(System.out::println);
//        System.out.println(driver.executeScript(
//                "flutter:getCenter",
//                find.bySemanticsLabel("listElements")));
//
//        System.out.println();
//        find.pageBack().click();

        lp.wrapperElement.click();
//        find.bySemanticsLabel("Wrapper").click();
    }
}
    