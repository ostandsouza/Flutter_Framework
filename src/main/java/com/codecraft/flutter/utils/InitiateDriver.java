package com.codecraft.flutter.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.options.BaseOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.appium.java_client.proxy.Helpers.createProxy;

/**
 * Created by codecraft on 08/11/16.
 */
public class InitiateDriver {
    private RemoteWebDriver driver;

    private AppiumDriver appiumDriver ;
    private Map<String,String> getProperties;
    public static String appUrl;
    private String setup,server, bsUser, bsKey, browser, mbrowser,mUrl,url,runON,runONFromProperty,automationName,ctx,bsUser_value, bsKey_value;
    private String  deviceName,osVersion,deviceID;
    //Windows, OSX or any
    private String platform;
    private static String appiumServerURL;//ServerChanges
    Optional<String> simulatorFlag, simulatorFlagFromMaven, bsID; //Flag that retain the status of connected device vs simulator
    Logger logger = LogManager.getLogger(InitiateDriver.class);

    //Instance of classes
    private static InitiateDriver obj=null;
    DeviceConfig deviceConfigObj = DeviceConfig.getInstance();


    private InitiateDriver(){};

    //Single instance of class to control the execution
    public static InitiateDriver getInstance()
    {
        if(obj==null)
            obj = new InitiateDriver();
        return obj;

    }


    public void createDriver()
    {
        logger.debug("Setting up driver and other mandatory parameters.....");
        getProperties= PropertyReader.getInstance().getPropValues("config.properties");


        setup = getProperties.get("SETUP");
        logger.info("Setup(local/Browserstack) : " + setup);

        platform= System.getProperty("os.name");
        logger.info("Local Machine OS :" +platform);

        //runON- Android_APP/iOS_APP/Mobile_Site/Website ..
        runONFromProperty=System.getenv("Platform") == null ?getProperties.get("RUN_ON"):System.getenv("Platform");
        ctx=System.getenv("Context") == null ?getProperties.get("CONTEXT"):System.getenv("Context");
        //runON- Android/iOS/Website ..
        runON= runONFromProperty.split("_")[0];
        logger.info("runON : " + runON);
        logger.info("Context == " + ctx);
        logger.info("Setup(local/Browserstack) : " + setup.equalsIgnoreCase("browserstack"));
        //automationName
        automationName= ctx.equalsIgnoreCase("FLUTTER")?"FLUTTER":runON.equalsIgnoreCase("android")?"UiAutomator2":"XCUITest";

        if(setup.equalsIgnoreCase("browserstack"))
            setBrowserStackCredentials();
        else
            setConfigValuesForLocalAppTesting();


        appiumServerURL= setURL(setup);
        logger.debug("Server URL :" +appiumServerURL);


        logger.info("Configuration values.....{");
        getProperties.forEach((k, v) -> {
            logger.info( k + " : "+ v);

        });
        logger.info("}");

        logger.info("****Configuration values of  app testing****");
        logger.info("AppURL(path to app)"+appUrl);
        logger.info("DeviceID "+deviceID);
        logger.info("OS version "+osVersion);
        logger.info("DeviceName "+deviceName);
        logger.info("AutomationName "+automationName);

        //creating driverinstance
        initiateDriver();


    }

    /**
     * Function assign values to variable with setup=local and runON= android/ioS (mobile app testing)
     * Supports for app and mobile browser testing
     */
    private void setConfigValuesForLocalAppTesting()
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executed");
        if(!runON.equalsIgnoreCase("WEBSITE")) {

            simulatorFlagFromMaven=Optional.ofNullable(System.getProperty("environment"));

            logger.info("Inclusion of environment tag to execute simulator in Maven command : " +simulatorFlagFromMaven);


            simulatorFlagFromMaven.ifPresentOrElse(
                    (value)->{
                        logger.info("Virtual device activated from maven command");
                        simulatorFlagFromMaven.ifPresent(this::getAndSetDeviceConfigFromFile); },
                    ()->{
                        logger.info("Virtual device activated from TestNG.xml");
                        simulatorFlag = Optional.ofNullable(
                                Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("environment"));
                        logger.info("Inclusion of parameter tag to execute simulator in TestNG.xml : " +simulatorFlag);
                        simulatorFlag.ifPresent(this::getAndSetDeviceConfigFromFile);

                    });


            appUrl = getAppPath(setup);
            //Check for the <parameter> tag at test level in TestNG.xml


            if (!simulatorFlag.isPresent()) {
                //Setting up configuration to test on connected device.
                deviceID = deviceConfigObj.getDeviceID(runON);
                osVersion = deviceConfigObj.getPlatformVersion(runON);
                deviceName = deviceConfigObj.getDeviceName(runON);
            }
        }else {
            setConfigValuesForWebsiteTesting();
            logger.debug("Driver instance created....");
            logger.info("Opening URL " + mUrl);

            driver.get(mUrl);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

    }


    /****
     * Function sets the device configuraiton from "Device_Config.json"
     *
     * @param environment - It fetch the value provided in parameter tag in TestNG.xml
     */
    private void getAndSetDeviceConfigFromFile(String environment)
    {
        logger.debug("Method : " +Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.debug("Environment specified for device : " +environment);

        try {


            String deviceOS = System.getenv("os") == null ? "" : System.getenv("os");
            String deviceInfo = System.getenv("device") == null ? environment : System.getenv("device") + "(" + deviceOS + ")";

            Map<String, String> envCapabilities = ReadJson.getInstance().parsingJSONForAParentNode("device_conf.json", "environments", deviceInfo);

            osVersion = envCapabilities.get("os");
            deviceName = envCapabilities.get("device");
            deviceID = envCapabilities.get("deviceid"); //For BS, device ID is a string with length==0

        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }


    /**
     * Function assign values to variable with setup=local and runON= website (mobile app testing)
     */
    private void setConfigValuesForWebsiteTesting()
    {
        //This also need attention at browser stack level.
        browser=System.getProperty("browser") == null ? getProperties.get("BROWSER") : System.getProperty("browser");
        // URL passed for mobile web app testing
        mUrl =System.getProperty("mUrl") == null ? getProperties.get("mURL") : System.getProperty("mUrl");
        //URL passed to remoteWebDriver
        url = System.getProperty("url") == null ? getProperties.get("SELENIUMSERVERURL") : System.getProperty("url");
    }

    /**
     * Function assign values to variable with setup=browserstack
     */
    private void setBrowserStackCredentials()
    {
        logger.debug("Method : " +Thread.currentThread().getStackTrace()[1].getMethodName());


        logger.debug("Setting up BrowserStack credentials.... ");
        logger.info("******************* Configuration values for BrowserStack *******************");
        appUrl=  System.getenv("BrowserStack_CustomID")==null? getAppPath(setup):System.getenv("BrowserStack_CustomID");
        server = ReadJson.getInstance().getValueFromJsonKey("device_conf.json", "server");
        bsUser = ReadJson.getInstance().getValueFromJsonKey("device_conf.json", "user");
        bsKey = ReadJson.getInstance().getValueFromJsonKey("device_conf.json", "key");

        logger.info("BS app URL "+appUrl);
        logger.info("BS -Server "+server);
        logger.debug("Device key preferred by user- "+System.getenv("Device"));

        //fetching device configuration from device_config.json

        simulatorFlagFromMaven=Optional.ofNullable(System.getProperty("environment"));

        logger.info("Inclusion of environment tag to execute simulator in Maven command [BS] : " +simulatorFlagFromMaven);


        simulatorFlagFromMaven.ifPresentOrElse(
                (value)->{
                    logger.info("Virtual device activated from maven command [BS] ");
                    simulatorFlagFromMaven.ifPresent(this::getAndSetDeviceConfigFromFile); },
                ()->{
                    logger.info("Virtual device activated from TestNG.xml");
                    simulatorFlag = Optional.ofNullable(
                            Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("environment"));
                    logger.info("Inclusion of parameter tag to execute simulator in TestNG.xml : " +simulatorFlag);
                    simulatorFlag.ifPresent(this::getAndSetDeviceConfigFromFile);

                });



    }

    /**
     * Function return the url that is passed into AndroidDriver(new URL(<here></here>))
     * @param setup
     * @return
     */
    private String setURL(String setup)
    {
        logger.debug("Setting up url for the mode - " +setup);
        switch (setup.toLowerCase()) {
            case "local":
                return getProperties.get("SeverAddress");//URL to be assigned programmatically once when Appium server code is integrated. #ServerChange

            case  "browserstack":
                //bs_User, bs_Key taken from maven command, if not by default it will pick from device.json.
                bsUser_value= System.getProperty("bsUser_value");
                bsKey_value= System.getProperty("bsKey_value");
                if(bsUser_value == null || bsKey_value == null){
                    logger.info("As BS user is null, running with default BS-user "+bsUser);
                    logger.info("As BS key is null, running with default BS-key "+bsKey);
                    return "https://"+bsUser+":"+bsKey+"@"+server+"/wd/hub";
                }
                return "https://"+bsUser_value+":"+bsKey_value+"@"+server+"/wd/hub";
            default:
                return "Condition failed";

        }
    }

    /***
     * Function return path to ipa or apk file to execute in local machine or bs id.
     * This is passed into MobileCapability.app
     * @return
     */
    private String getAppPath(String setUp)
    {
        logger.debug("Setting up url for the mode - " +setup);
        try {
            switch (setup.toLowerCase()) {
                case "local":
                    locateAppInLocal();
                    //Path to apk/ipa is stored with key name as 'apk' and ipa'
                    return getProperties.get(
                            runON.equalsIgnoreCase("android") ? "apk" : (simulatorFlag.isPresent() ? "app" : "ipa"));
                case "browserstack":
                    Map<String, String> capabitlies = ReadJson.getInstance().parsingJSONForAParentNode("device_conf.json", "capabilities", null);
                    //return runON.equalsIgnoreCase("android") ? capabitlies.get("AndroidID") : capabitlies.get("iOSID"); //"bs://70c20d9dedd7a97f610579d9c2c616938d1a9f7f" or custom iD
                    //bsId- taken from maven command.
                    //If not passed then it picks from the device_config.json
                    bsID= Optional.of(Optional.ofNullable(System.getProperty("bsId")).orElse(runON.equalsIgnoreCase("android") ? capabitlies.get("AndroidID") : capabitlies.get("iOSID")));
                    logger.info("BrowserStack ID: " + bsID);
                    return bsID.get();
                default:
                    return null;
            }
        }catch (Exception e)
        {
            throw new RuntimeException("App path unidentified");

        }
    }


    private void initiateDriver()
    {
        logger.debug("Initiating driver with runON value as -" + runON);

        try {
            if(runON.equalsIgnoreCase("WEBSITE"))
            {
//              TODO: 24-02-2023 to 30-02-2023 (As below implementation is ment onlhy for web application automation. This will be taken up later)
                //With runOn identified as Website, now browser value is picked up (chrome/firefox)
//                WebDriverManager.chromedriver().setup();
//                ChromeOptions optionsChrome = new ChromeOptions();
//                optionsChrome.setAcceptInsecureCerts(true);
//                driver = new ChromeDriver(optionsChrome);
//                driver.manage().window().maximize();
//                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            }else if(runON.equalsIgnoreCase("ANDROID"))
            {
                logger.info("Android driver intialization starts... ");
                logger.info("AppiumServerURL == "+appiumServerURL);
                appiumDriver = createProxy(
                        AndroidDriver.class,
                        new Object[] {new URL(appiumServerURL), getCapabilities(runONFromProperty)},
                        new Class[] {URL.class, Capabilities.class},
                        Collections.singletonList(new AppiumListener())
                );
                appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }else if(runON.equalsIgnoreCase("IOS"))
            {
//                appiumDriver = createProxy(
//                        IOSDriver.class,
//                        new Object[] {new URL(appiumServerURL), getCapabilities(runONFromProperty)},
//                        new Class[] {URL.class, Capabilities.class},
//                        Collections.singletonList(new AppiumListener())
//                );
                appiumDriver = new IOSDriver(new URL(appiumServerURL),getCapabilities(runONFromProperty));
                appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            logger.error("Exception thrown while creating driver instance. "+e.getMessage());

        }
    }

    /****
     * function will locate and add .ipa/.app files.
     * one can place in custom path and mentioned the` path in AppPath key available in config.properties
     * if "AppPath" is config.properties is empty then it will try locating files in workspace.
     *
     *
     */

    private void locateAppInLocal()
    {
        logger.debug("identifying path to locate app...... ");

        //if AppPath in config.properties is null or empty then work space directory gets assigned.
        String pathToFile = Optional.ofNullable(getProperties.get("AppPath")).orElse(System.getProperty("user.dir"));
        logger.info("Identified path to pick app/ipa file - "+pathToFile);

        try
        {
            File fileObj = new File(pathToFile);
            if(fileObj.isDirectory() && fileObj.exists()) {
                if(fileObj.list().length==0) {
                    logger.fatal("fatal error: Unable to locate app path ");
                }
                getProperties.putAll(Arrays.asList(fileObj.list()).stream().filter(file -> getFileExtension(file).equalsIgnoreCase("apk")
                                || getFileExtension(file).equalsIgnoreCase("ipa") || getFileExtension(file).equalsIgnoreCase("app")).distinct().limit(3)
                        .collect(Collectors.toMap(x -> getFileExtension(x), x -> pathToFile+"/"+x)));

            }
        }catch(Exception e)
        {
            //fatal exception. Terminate the process
            logger.fatal("Exception occurred while trying to locate app -  " + e.getMessage());

        }
    }
    //function to obtain the extension of file eg:.xlsx, .ipa., .apk
    private String getFileExtension(String fileName)
    {
        logger.debug("identifying file extension ....");
        if(fileName.lastIndexOf(".")!=-1 )
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else
            return "";
    }

    //unused
    public RemoteWebDriver getDriver()
    {
        if(driver==null)
            throw new RuntimeException("Driver has not been Instantiated");

        return driver;
    }

    public AppiumDriver getAppiumDriver() {
        if(appiumDriver==null)
            throw new RuntimeException("Driver has not been instantiated");
        logger.info("get by Thread "+Thread.currentThread().getId()+" - "+ appiumDriver);
        return appiumDriver;
    }


    private BaseOptions getCapabilities(String runONValue)
    {
        BaseOptions options = null;
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executed ");
        try
        {
            if (runONValue.equalsIgnoreCase("WEBSITE")) {
                logger.debug("Setting up desired capabilities for website......");
                switch (browser.toLowerCase()) {
                    // TODO: 23-02-2023 - 30-03-2023
                /*
                case "firefox":
                    capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("firefox");
                    capabilities.setPlatform(Platform.ANY);

                case "chrome":
                    //ChromeDriverManager.getInstance().setup();
                    //System.setProperty("webgetDriver().chrome.getDriver()","/home/" + System.getProperty("user.name") + "/Documents/chromegetDriver()");
                    capabilities = new DesiredCapabilities();
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-component-update")); //To Play DRM Content
                    capabilities.setBrowserName("chrome");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    capabilities.setPlatform(Platform.ANY);
                case "ie":
                    //InternetExplorerDriverManager.getInstance().setup();
                    //System.setProperty("webgetDriver().ie.getDriver()", "../../../../resources/IEDriverServer.exe");
                    capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName("internet explorer");
                    capabilities.setCapability("ie.ensureCleanSession", true);
                    capabilities.setPlatform(Platform.WINDOWS);
            };
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            */

                }
            }
            //Mobile_Site
            else if (runONValue.contains("_SITE"))
            {

                switch (runONValue.toLowerCase())
                {
                    case "android_site":
                        options = new UiAutomator2Options();
                        options.setCapability(MobileCapabilityType.BROWSER_NAME, mbrowser);
                    case "ios_site":
                        options = new XCUITestOptions();
                        options.setCapability(MobileCapabilityType.BROWSER_NAME, mbrowser);//likey duplicate

                };
                options.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
                options.setCapability(MobileCapabilityType.PLATFORM_NAME,runON);
                options.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            }
            else if (runONValue.contains("_APP"))
            {

                logger.debug("Setting up capabilities for app " + runON);
                //String version=Base.getAndroidVersion().trim();
                switch(runONValue.toLowerCase())
                {
                    case "android_app":
                        //
                        options = new UiAutomator2Options();
                        options.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getProperties.get("AndroidAppPackage"));
                        options.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getProperties.get("AndroidAppActivity"));
                        options.setCapability("autoGrantPermissions", true);
                        break;

                    case "ios_app":
                        options = new XCUITestOptions();
                        options.setCapability("locationServicesAuthorized", true);
                        options.setCapability("autoAcceptAlerts", true);
                        options.setCapability("waitForAppScript", "$.delay(8000);$.acceptAlert()");
                        options.setCapability("autoAcceptAlerts",true);
                        options.setCapability("bundleID", getProperties.get("BundleId"));
                        //capabilities.setCapability(IOSMobileCapabilityType.WDA_CONNECTION_TIMEOUT   ,"480000");
                        options.setCapability("xcodeOrgId", PropertyReader.getInstance().getPropValues("credentials.properties","xcodeOrgId"));
                        options.setCapability("xcodeSigningId", PropertyReader.getInstance().getPropValues("credentials.properties","xcodeSigningId"));
                        options.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_AUTHORIZED, true);//need for Local run


                };
                options.setCapability("udid",deviceID );
                options.setCapability(MobileCapabilityType.APP, appUrl);
                options.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                options.setCapability(MobileCapabilityType.PLATFORM_NAME, runON);
                options.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
                options.setCapability("name", getProperties.get("TestName"));
                options.setCapability("browserstack.debug", "true");
                options.setCapability("buildName", "Mercury_QA");

                options.setCapability("browserstack.local", "true");
                //options.setCapability("forcelocal", "true");
                options.setCapability("browserstack.networkLogs", "true");
                options.setCapability("browserstack.appium_version", "2.0.0");
                options.setCapability(MobileCapabilityType.AUTOMATION_NAME,automationName);
                options.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
                //clears app data(cache)
                options.setCapability(MobileCapabilityType.EVENT_TIMINGS,true);
                options.setCapability("noReset", false);

            }
        }catch (Exception e)
        {
            e.printStackTrace();
            logger.fatal("Exeception occured in setting up capabilities" +e.getMessage());
        }

        return options;
    }

}
