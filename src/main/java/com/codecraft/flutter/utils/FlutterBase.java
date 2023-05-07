package com.codecraft.flutter.utils;

import com.codecraft.flutter.utils.kotlin.FlutterFinder;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import com.codecraft.flutter.utils.kotlin.finder.Serializer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.jetbrains.kotlin.codegen.And;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class FlutterBase{

    public AppiumDriver driver;
    public FlutterFinder find;
    private Map<String,String> getProperties;
    private String setup, runOn;

    public FlutterBase(AppiumDriver driver) {
        this.driver = driver;
        this.find = new FlutterFinder(this.driver);
    }

    /**
     *This method is used to perform tap action on Flutter element
     *@param element: Flutter Element locator
     *@return Null
     *@author Ostan dsouza
     */
    public void tapGesture(FlutterElement element) {
        int val = driver instanceof AndroidDriver? 1: 1;
        driver.executeScript("flutter: longTap", Serializer.serialize((Map<String, Object>)element.getRawMap()), new HashMap<String, Object>() {{
            put("durationMilliseconds", val);
            put("frequency", 100);
        }});
    }

    /**
     * This method is used to perform multiple tap on the flutter element
     * @param element: Flutter Element locator
     * @param count: Count of the tap action
     * @return Null
     * @author Ostan dsouza
     */
    public void multipleTapGesture(FlutterElement element, int count) {
        int val = driver instanceof AndroidDriver? 1: 10;
        while (count + 2 > 0) {
            driver.executeAsyncScript("flutter: longTap", Serializer.serialize((Map<String, Object>)element.getRawMap()), new HashMap<String, Object>() {{
                put("durationMilliseconds", val);
                put("frequency", 1000);
            }});
//            element.click();
            count--;
        }

    }

    /**
     * This method is used to perform action for the slider buttons
     * @param element: Flutter Element locator
     * @param percentage: What percentage is required
     * @author: Ostan Dsouza
     */
    public void sliderGesture(FlutterElement element, int percentage) {
        int val = driver instanceof AndroidDriver? 10: 100;
        Map center = (Map) driver.executeScript("flutter:getBottomRight", Serializer.serialize((Map<String, Object>)element.getRawMap()));

        driver.executeScript("flutter: scroll", Serializer.serialize((Map<String, Object>)element.getRawMap()), new HashMap<String, Object>() {{
            put("dx", - ((center.get("dx") instanceof Long ? (long) center.get("dx") : (double) center.get("dx")) * percentage/100));
            put("dy", 0);
            put("durationMilliseconds", val);
            put("frequency", 1000);
        }});
    }

    /**
     * This method is used to perform tap and hold action
     * @param element: Flutter Element locator
     * @return Null
     * @author Ostan dsouza
     */
    public void pressGesture(FlutterElement element) {
        int val = driver instanceof AndroidDriver? 500: 5000;
        driver.executeScript("flutter: longTap", Serializer.serialize((Map<String, Object>)element.getRawMap()), new HashMap<String, Object>() {{
            put("durationMilliseconds", val);
            put("frequency", 1000);
        }});
    }

    /**
     * This method is used to scroll to a specific Flutter Element
     * @param element: Flutter Element locator
     * @param ele: Target Flutter Element
     * @param dir: The direction need to be scrolled(Up,Down, Left, Right)
     * @return Null
     * @author Ostan Dsouza
     */
    public void scrollToElement(FlutterElement element, FlutterElement ele, Direction dir) {
        boolean flag = isVisisble(ele);

        Map center = (Map) driver.executeScript("flutter:getCenter", Serializer.serialize((Map<String, Object>)element.getRawMap()));

        while (!flag) {

            scroll(element, dir, center);

            flag = isVisisble(ele);

            if (flag && Double.compare(getElementPosition(ele).get("dy") instanceof Long ? (long) ((Long) getElementPosition(ele).get("dy")).doubleValue() : (double) getElementPosition(ele).get("dy"), 650d) > 0)
                scroll(element, dir, center);
        }
    }

    /**
     * This method is used to slide to a specific Flutter Element
     * @param element: Flutter Element locator
     * @param dir: The direction need to be scrolled(Up,Down, Left, Right)
     * @return Null
     * @author Ostan Dsouza
     */
    public void slideToElement(FlutterElement element, Direction dir) {

        Map center = (Map) driver.executeScript("flutter:getCenter", Serializer.serialize((Map<String, Object>)element.getRawMap()));

        scroll(element, dir, center);

    }


    /**
     * This method is used to do Scroll
     * @param element: Flutter Element locator
     * @param point: Center Cortinates of the flutter element
     * @param dir: The direction need to be scrolled(Up,Down, Left, Right)
     * @return Null
     * @author Ostan Dsouza
     */
    private void scroll(FlutterElement element, Direction dir, Map point) {
        int val = driver instanceof AndroidDriver? 10: 100;
        switch (dir) {
            case DOWN:
                driver.executeScript("flutter: scroll", element.getId(), new HashMap<String, Object>() {{
                    put("dx", 0);
                    put("dy", -(point.get("dy") instanceof Long ? (long) point.get("dy") : (double) point.get("dy")));
                    put("durationMilliseconds", val);
                    put("frequency", 1000);
                }});
                break;

            case UP:
                driver.executeScript("flutter: scroll", element.getId(), new HashMap<String, Object>() {{
                    put("dx", 0);
                    put("dy", point.get("dy") instanceof Long ? (long) point.get("dy") : (double) point.get("dy"));
//                put("dy", 50);
                    put("durationMilliseconds", val);
                    put("frequency", 1000);
                }});
                break;
            case LEFT:
                driver.executeScript("flutter: scroll", element.getId(), new HashMap<String, Object>() {{
                    put("dx", -(point.get("dx") instanceof Long ? (long) point.get("dx") : (double) point.get("dx")));
//                    put("dx", -100);
                    put("dy", 0);
                    put("durationMilliseconds", val);
                    put("frequency", 1000);
                }});
                break;
            case RIGHT:
                driver.executeScript("flutter: scroll", element.getId(), new HashMap<String, Object>() {{
                    put("dx", (point.get("dx") instanceof Long ? (long) point.get("dx") : (double) point.get("dx")));
//                    put("dx", -100);
                    put("dy", 0);
                    put("durationMilliseconds", val);
                    put("frequency", 1000);
                }});
                break;
        }
    }

    /**
     * This method is used to Scroll till the element can be viewed in the screen
     * @param element: Flutter Element locator
     * @return Null
     * @author Ostan Dsouza
     */
    public void scrollIntoView(FlutterElement element) {
        driver.executeScript("flutter: scrollIntoView", Serializer.serialize((Map<String, Object>)element.getRawMap()), new HashMap<String, Object>() {{
            put("alignment", 0.1);
        }});
    }

    /**
     * This method is used to perform Tap and Drag action
     * @param elementFrom: Flutter Element locator
     * @param elementTo: Flutter Element locator
     * @return Null
     * @author Ostan Dsouza
     */
    public void DragGesture(FlutterElement elementFrom, FlutterElement elementTo) {
        int val = driver instanceof AndroidDriver? 10: 100;
        Map point = (Map) driver.executeScript("flutter:getCenter", Serializer.serialize((Map<String, Object>)elementFrom.getRawMap()));
        Map point1 = (Map) driver.executeScript("flutter:getCenter", Serializer.serialize((Map<String, Object>)elementTo.getRawMap()));
        if (Double.compare((double) point.get("dy"), (double) point1.get("dy")) < 0) {
            driver.executeScript("flutter: scroll", Serializer.serialize((Map<String, Object>)elementFrom.getRawMap()), new HashMap<String, Object>() {{
                put("dx", point1.get("dx") instanceof Long ? (long) point1.get("dx") : (double) point1.get("dx"));
                put("dy", point1.get("dy") instanceof Long ? ((long) point1.get("dy") - (point.get("dy") instanceof Long ? (long) point.get("dy") : (double) point.get("dy"))) : (double) point1.get("dy") - (point.get("dy") instanceof Long ? (long) point.get("dy") : (double) point.get("dy")));
                put("durationMilliseconds", val);
                put("frequency", 1000);
            }});
        } else {
            driver.executeScript("flutter: scroll", Serializer.serialize((Map<String, Object>)elementFrom.getRawMap()), new HashMap<String, Object>() {{
                put("dx", point1.get("dx") instanceof Long ? (long) point1.get("dx") : (double) point1.get("dx"));
                put("dy", point1.get("dy") instanceof Long ? -((long) point1.get("dy") - (point.get("dy") instanceof Long ? (long) point.get("dy") : (double) point.get("dy"))) : -((point.get("dy") instanceof Long ? (long) point.get("dy") : (double) point.get("dy")) - (double) point1.get("dy")));
                put("durationMilliseconds", val);
                put("frequency", 1000);
            }});
        }
    }

    /**
     * This method is used to get the context handles which can be used to iterate over context of the Driver
     * @return Set of Context handles that can iterate over available contexts
     * @author Ostan Dsouza
     */
    public Set<String> getAllContext() {
        if (driver instanceof AndroidDriver)
            return ((AndroidDriver) driver).getContextHandles();
        else
            return ((IOSDriver) driver).getContextHandles();
    }

    /**
     * This method is used to check element is visible or nor
     * @param element: Flutter Element locator
     * @return True if found
     * @author Ostan dsouza
     */
    public boolean isVisisble(FlutterElement element) {
        int val = driver instanceof AndroidDriver? 3000: 3000;
        try {
            driver.executeScript("flutter:waitFor", Serializer.serialize((Map<String, Object>)element.getRawMap()), val);
            return true;
        } catch (Exception ignored) {
                return false;
        }
    }

    /**
     * This method is used to get the size of the element
     * @param element: Flutter Element locator
     * @return Height and width of the element
     * @author Ostan dsouza
     */
    public HashMap<String, Integer> getElementSize(FlutterElement element){
        String str = (String) ((ArrayList<Map<Object, Object>>)((Map<Object, Object>)driver.executeScript(
                "flutter:getRenderObjectDiagnostics",
                element.getId(),
                new HashMap<String, Object>() {{
                    put("includeProperties", true);
                    put("subtreeDepth", 0);
                }})).get("properties")).stream().filter(x->((String)x.get("name")).equalsIgnoreCase("size")).collect(Collectors.toList()).get(0).get("description");

        Pattern p = Pattern.compile("([0-9]+)[.]");
        Matcher m = p.matcher(str.split(",")[0]);
        HashMap<String, Integer> map = new HashMap<>();
        if (m.find())
            map.put("height",parseInt(m.group(1).replace(".","")));
        m = p.matcher(str.split(",")[1]);
        if (m.find())
            map.put("width",parseInt(m.group(1).replace(".","")));
        return map;
    }

    /**
     * This method is used to get the Position of the element is the screen
     * @param element: Flutter Element locator
     * @return The Top Left point of the element
     * @author Ostan Dsouza
     */
    public Map<String, Object> getElementPosition(FlutterElement element){
        Map point = (Map) driver.executeScript("flutter:getTopLeft", Serializer.serialize((Map<String, Object>)element.getRawMap()));
        return point;
    }

    /**
     * This method is used to navigate to the previous screen
     * @return Null
     * @author Ostan Dsouza
     */
    public void goBackToPreviousPage(){
        find.pageBack().click();
    }

    /**
     * This method is used to wait until the screen frame is loaded for the Flutter pages
     * @return Null
     * @author Ostan Dsouza
     */
    public void waitUntilAppLoads(){
        driver.executeScript("flutter:waitForFirstFrame");
    }

    /**
     * This method is used to wait until a particular element is loaded for the Native Pages
     * @param driver1: Appium driver object
     * @param ele: Flutter Element locator
     * @return Null
     * @author Ostan Dsouza
     */
    public void waitUntilAppLoads(AppiumDriver driver1, WebElement ele){
        WebDriverWait wwait = new WebDriverWait(driver1,Duration.ofSeconds(20));
        wwait.until(ExpectedConditions.visibilityOf(ele));
    }

    /**
     * This method is used to perform sleep for a particular amount of time
     * @param secs: Time in sec to wait
     * @return Null
     * @author Prasanjit Kar
     */
    public  void waitForSeconds(int secs){
        try {
            int timeout = secs * 1000;
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to call wait for an element to be loaded
     * @param element: Flutter Element locator
     * @param secs: Time in sec to wait
     * @return: True if the Element is found within the timeout
     * @author: Ostan Dsouza
     */
    public  boolean waitSecsForElement(FlutterElement element, int secs){
        try {
            int timeout = secs * 1000;
            driver.executeScript("flutter:waitFor", Serializer.serialize((Map<String, Object>)element.getRawMap()), timeout);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
