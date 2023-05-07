package com.codecraft.flutter;

import com.codecraft.flutter.webView.WebViewNativePage;
import com.codecraft.flutter.carousel.CarouselPage;
import com.codecraft.flutter.doubleTap.DoubleTapPage;
import com.codecraft.flutter.dragNdrop.DragNDropPage;
import com.codecraft.flutter.forms.FormsNativePage;
import com.codecraft.flutter.forms.FormsPage;
import com.codecraft.flutter.landing.LandingPage;
import com.codecraft.flutter.press.LongPressPage;
import com.codecraft.flutter.scroll.ScrollPage;
import com.codecraft.flutter.slide.SliderPage;
import com.codecraft.flutter.swipe.SwipePage;
import com.codecraft.flutter.tap.TapPage;
import com.codecraft.flutter.timer.TimerNativePage;
import com.codecraft.flutter.timer.TimerPage;
import com.codecraft.flutter.upi.UPINativePage;
import com.codecraft.flutter.upi.UPIPage;
import com.codecraft.flutter.utils.Base;
import com.codecraft.flutter.utils.FlutterBase;

public class Pages {

    private static <T extends FlutterBase> T getFlutterPage(Class<T> pageType)  {
        T page;
        try {
            page = pageType.newInstance();
        } catch (InstantiationException e) { //make sure you use JDK 1.7
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return page;
    }

    public static LandingPage LandingPage(){return getFlutterPage(LandingPage.class);}

    public static TapPage TapPage(){return getFlutterPage(TapPage.class);}

    public static CarouselPage CarouselPage(){return getFlutterPage(CarouselPage.class);}

    public static DoubleTapPage DoubleTapPage(){return getFlutterPage(DoubleTapPage.class);}

    public static DragNDropPage DragNDropPage(){return getFlutterPage(DragNDropPage.class);}

    public static FormsPage FormsPage(){return getFlutterPage(FormsPage.class);}

    public static LongPressPage LongPressPage(){return getFlutterPage(LongPressPage.class);}

    public static ScrollPage ScrollPage(){return getFlutterPage(ScrollPage.class);}

    public static SliderPage SliderPage(){return getFlutterPage(SliderPage.class);}

    public static SwipePage SwipePage(){return getFlutterPage(SwipePage.class);}

    public static TimerPage TimerPage(){return getFlutterPage(TimerPage.class);}

    public static UPIPage UPIPage(){return getFlutterPage(UPIPage.class);}

    private static <T extends Base> T getNativePage(Class<T> pageType)  {
        T page;
        try {
            page = pageType.newInstance();
        } catch (InstantiationException e) { //make sure you use JDK 1.7
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return page;
    }

    public static FormsNativePage FormsNativePage(){return getNativePage(FormsNativePage.class);}

    public static TimerNativePage TimerNativePage(){return getNativePage(TimerNativePage.class);}

    public static UPINativePage UPINativePage(){return getNativePage(UPINativePage.class);}

    public static WebViewNativePage WebViewNativePage(){return getNativePage(WebViewNativePage.class);}

}
