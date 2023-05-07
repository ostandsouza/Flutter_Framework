package com.codecraft.flutter.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class Base extends DriverFactory {

    public AppiumDriver driver;

    public  long waitTime=90;
    public  String windowsPath="C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\Android\\android-sdk\\platform-tools\\";
    public  String linuxPath="/home/"+"System.getProperty(\"user.name\")"+"/Android/android-sdk/platform-tools/";
    public static final Logger logger = LogManager.getLogger(Base.class.getName());

    public Base(AppiumDriver driver){
        this.driver = driver;
    }

    /**
     *This method is used to check if element is visible or not
     *@param element: WebElement locator
     *@return True if the Element is found within the timeout.
     *@author Ostan dsouza
     */
    public  boolean isElementVisible(WebElement element)
    {
        try{
            if(element.isDisplayed())
                return true;
            return false;
        }
        catch (org.openqa.selenium.NoSuchElementException e)
        {
            return false;
        }

    }

    /**
     *This method is used to wait until element is hidden from view
     *@param element: WebElement locator
     *@return Null
     *@author Ostan dsouza
     */
    public  void waitForElementToBeInvisible(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTime));
        wait.until(invisibilityOfWebElementLocated(element));
    }

    /**
     *This method is used to wait until element is present in the view
     *@param element: WebElement locator
     *@return Null
     *@author Ostan dsouza
     */
    public  void waitUntilElementIsVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     *This method is used to wait until element has text in the view
     *@param element: WebElement locator
     *@param text: Text to be found
     *@return Null
     *@author Ostan dsouza
     */
    public  void waitUntilElementHasText(WebElement element, String text)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    /**
     *This private method is used to check invisibility of the webElement
     *@param element: WebElement locator
     *@return True if the Element is found.
     *@author Ostan dsouza
     */
    private  ExpectedCondition<Boolean> invisibilityOfWebElementLocated(final WebElement element)
    {
        return new ExpectedCondition<Boolean>() {
            //@Override
            public Boolean apply(WebDriver driver) {
                try
                {
                    if (element.isDisplayed())
                        return false;
                    return true;
                }
                catch (Exception e)
                {
                    return true;
                }
            }
        };
    }

    public  void waitUntilElementsAttributeHasChanged(WebElement element, String attribute, String initialValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(attributeValueOfElementChanged(element,attribute,initialValue));
    }

    /**
     *This private method is used to check change in attribute of the webElement
     *@param element: WebElement locator
     *@param attribute: Final attribute
     *@param initialValue: Initial attribute
     *@return True if the Element is found.
     *@author Ostan dsouza
     */
    private  ExpectedCondition<Boolean> attributeValueOfElementChanged(final WebElement element, final String attribute, final String initialValue){
        return new ExpectedCondition<Boolean>() {
            //@Override
            public Boolean apply(WebDriver driver) {
                try
                {
                    if (element.getAttribute(attribute).equalsIgnoreCase(initialValue))
                        return false;
                    return true;
                }
                catch (Exception e)
                {
                    return true;
                }
            }
        };

    }

    /**
     *This method is used to check the platform of the test.
     *@return True if the platform is android
     * False if platform is iOS.
     *@author Ostan dsouza
     */
    public  boolean isAndroid(){
        HashMap<String, String> getProperties = PropertyReader.getInstance().getPropValues("config.properties");
        String runOn = System.getenv("platform") == null ? getProperties.get("RUN_ON"): System.getenv("platform");
        if(runOn.contains("ANDROID_APP"))
            return true;
        return false;
    }

    /**
     *This method is used to naviagte back
     *@author Ostan dsouza
     */
    public  void navigateBack(){
        driver.navigate().back();
    }

    /**
     *This method is hide soft keyboard
     *@author Ostan dsouza
     */
    public  void hideKeyboard(){
        logger.debug("hide keyboard executed....");

        try{
            if (!isAndroid()){
                driver.findElement(MobileBy.AccessibilityId("Done")).click();
            }
            else{
                (driver instanceof AndroidDriver? (AndroidDriver)driver :(IOSDriver)driver).hideKeyboard();
            }
        }
        catch (Exception e){
            logger.error("Keyboard Already Closed");
        }
    }

    /**
     *This method is hide soft keyboard iOS only
     *@author Ostan dsouza
     */
    public void hideKeyboardiOS(){
        try{
            driver.findElement(AppiumBy.accessibilityId( "Done")).click();
        }
        catch (Exception e){
            logger.error("Keyboard Already Closed");
        }
    }


    /**
     *This method is used to tap based on co-ordinates
     * @param x: x co-ordinates
     * @param y: y co-ordinates
     *@return null
     *@author Ostan dsouza
     */
    //Tap by coordinates
    public  void tapByCoordinates (int x,  int y) {
        new TouchAction(driver instanceof AndroidDriver? (AndroidDriver)driver :(IOSDriver)driver)
                .tap(PointOption.point(x,y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
    }


    /**
     *This method is used to slide the sikebar
     *@param fromElement: initial WebElement locator
     *@param toElement: target WebElement locator
     *@return null
     *@author Ostan dsouza
     */
    protected  void swipeFromElementToElement(WebElement fromElement, WebElement toElement)
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executed");
        Point firstCoordinate,lastCoordinate;
        try
        {
            firstCoordinate = toElement.getLocation();
            lastCoordinate = fromElement.getLocation();

            new TouchAction(driver instanceof AndroidDriver? (AndroidDriver)driver :(IOSDriver)driver).press(PointOption.point(lastCoordinate.getX(),
                    lastCoordinate.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                    .moveTo(PointOption.point(firstCoordinate.getX()
                            , firstCoordinate.getY())).release().perform();


        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Swipe from element to element failed with exception ",e);
        }
    }
    public  void navigateRefresh(){
        driver.navigate().refresh();
    }

    public  void sleep(long timeout){
        try{
            Thread.sleep(timeout);
        }
        catch (InterruptedException e){

        }
    }

    /**
     *This method is used to slide the sikebar
     *@param seekbar: WebElement locator of slider head
     *@param position: position in percentage
     *@return null
     *@author Ostan dsouza
     */
    public  void slideSeekbar(WebElement seekbar, int position){
        System.out.println("Sliding");
        // get start co-ordinate of seekbar
        int start=seekbar.getLocation().getX();
        //Get width of seekbar
        int end=seekbar.getSize().getWidth();
        //get location of seekbar vertically
        int y=seekbar.getLocation().getY();

        // Select till which position you want to move the seekbar
        TouchAction action=new TouchAction(driver instanceof AndroidDriver? (AndroidDriver)driver :(IOSDriver)driver);


        //Move it x%
        int moveTo=(int)(end*(position/100));
        action.press(PointOption.point(start,y)).moveTo(PointOption.point(moveTo,y)).release().perform();

    }

    /**
     *This method is used to move to percentage without sliding
     *@param seekbar: WebElement locator of slider head
     *@param position: position in percentage
     *@return null
     *@author Ostan dsouza
     */
    public  void clickSeekbar(WebElement seekbar, int position){
        // get start co-ordinate of seekbar
        int start=seekbar.getLocation().getX();
        //Get width of seekbar
        int end=seekbar.getSize().getWidth();
        //get location of seekbar vertically
        int y=seekbar.getLocation().getY();

        // Select till which position you want to move the seekbar
        TouchAction action=new TouchAction(driver instanceof AndroidDriver? (AndroidDriver)driver :(IOSDriver)driver);


        //Move it x%
        int moveTo=(int)(end*(position/100));
        action.press(PointOption.point(moveTo,y)).release().perform();
    }

    public  String generateRandomString(int length){
        String randomString = "";
        randomString = RandomStringUtils.randomAlphabetic(length);
        return randomString;
    }

    public  String generateAlphaNumericString(int length){
        String randomAlphaNumericString = "";
        randomAlphaNumericString  = RandomStringUtils.randomAlphanumeric(length);
        return randomAlphaNumericString;
    }

    public  String generateRandomMobileNumber(){
        String mobileNumber = "";
        mobileNumber = RandomStringUtils.random(1,"789");
        mobileNumber=mobileNumber+RandomStringUtils.randomNumeric(9);
        return mobileNumber;
    }

    public  String generateRandomEmail(){
        String email = RandomStringUtils.randomAlphanumeric(12).toLowerCase();
        return email+"@mailinator.com";
    }

    public  String generateRandomNumber(){
        return RandomStringUtils.randomNumeric(13);
    }

    public  void executeCommand(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(args);
        Process pc;
        try {
            pc = pb.start();
            pc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *This method is used to turn wifi off onloy on android
     *@return null
     *@author Ostan dsouza
     */
    public  void turnOffWifi() {
        String path=checkOS();
        try {
            Runtime.getRuntime().exec(path + "adb.exe shell am broadcast -a io.appium.settings.wifi --es setstatus disable");
            sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *This method is used to turn wifi on onloy on android
     *@return null
     *@author Ostan dsouza
     */
    public  void turnOnWifi() {
        String path=checkOS();
        try {
            Runtime.getRuntime().exec(path + "adb.exe shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
            sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *This method is used to perform sleep for a particular amount of time
     *@param timeout: duration
     *@return null
     *@author Ostan dsouza
     */
    public  void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *This method helps app run in background
     *@param time: duration
     *@return null
     *@author Ostan dsouza
     */
    public  void minimizeApplicationAndRestore(int time){
        sleep(3000);
        (driver instanceof AndroidDriver? (AndroidDriver)driver :(IOSDriver)driver).runAppInBackground(Duration.ofSeconds(time));
        sleep(3000);
    }

    /**
     *This method is return android SDK path based on platform OS
     *@return android sdk path for relevant platform
     *@author Ostan dsouza
     */
    public  String checkOS(){
        if (SystemUtils.IS_OS_WINDOWS) {
            return windowsPath;
        } else if (SystemUtils.IS_OS_MAC) {
            return null;
        } else if (SystemUtils.IS_OS_LINUX) {
            return linuxPath;
        } else{
            return null;
        }
    }

    /**
     *This method is used to get android version of the current device
     *@return android version of the device connected
     *@author Ostan dsouza
     */
    public  String getAndroidVersion(){
        String path=checkOS();
        try{
            java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(path+"adb shell getprop ro.build.version.release").getInputStream()).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }catch (IOException e){
            return "";
        }
    }

    /**
     *This method is used to switch context between different views
     *@param ctx: Target context
     *@return Instance of the switched context
     *@author Ostan dsouza
     */
    public WebDriver switchContext(String ctx) {
        if (driver instanceof AndroidDriver)
            return ((AndroidDriver) driver).context(ctx);
        else
            return ((IOSDriver) driver).context(ctx);
    }

}
