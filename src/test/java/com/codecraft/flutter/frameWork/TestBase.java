package com.codecraft.flutter.frameWork;

import com.browserstack.local.Local;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.InitiateDriver;
import com.codecraft.flutter.utils.PropertyReader;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by codecraft on 08/11/16.
 */
public class TestBase extends DriverFactory {

    String env;
    public  HashMap<String,String> configProperties;
    public  HashMap<String,String> credentialsProperties;
    public  HashMap<String,String> getProperties;
    private static final Logger logger = LogManager.getLogger(TestBase.class.getName());
    public  String windowsPath=System.getProperty("user.dir")+"\\src\\main\\resources\\"; //For Windows Properties path
    public  String linux_MacPath=System.getProperty("user.dir")+"/src/main/resources/";  //For Linux Properties path
    String[] arrayOfValues = new String[]{"tests","main","classes","instances"};//Expected values to run parallel execution
    public  HashMap<String, String> zephyrProperties ; //Only for Zephyr integration
    public  HashMap<String, String> testrailProperties ; //Only for Testrail integration
    public static Local bsLocal = new Local();


    public TestBase()
    {
        logger.debug("Method : " +Thread.currentThread().getStackTrace()[1].getClassName() );
        configProperties = PropertyReader.getInstance().getPropValues("config.properties");
        credentialsProperties = PropertyReader.getInstance().getPropValues("credentials.properties");
        env= System.getenv("env")== null? configProperties.get("ENV") :System.getenv("env");

        //Keep below lines only if project needs zephyr integration otherwise please comment
//        zephyrProperties= PropertyReader.getInstance().getPropValues("zephyr.properties");
//        ZephyrModel.getInstance().setZephyrProperties(zephyrProperties);

        //Keep below lines only if project needs testrail integration otherwise please comment
//        testrailProperties= PropertyReader.getInstance().getPropValues("testrail.properties");
//        TestRailModel.getInstance().setTestRailProperties(testrailProperties);
    }


    //use BeforeSuite to start session at begining
    @BeforeSuite(alwaysRun = true)
    public void init() {
        try {
            logger.debug("Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
            // creates an instance of Local
            logger.info("LOCAL_TESTING " + configProperties.get("LOCAL_TESTING"));
            if (Boolean.parseBoolean(configProperties.get("LOCAL_TESTING"))) {
                // replace <browserstack-accesskey> with your key. You can also set an environment variable - "BROWSERSTACK_ACCESS_KEY".
                System.out.println("Enabling Local Testing");
                HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
                String bsKey = System.getProperty("bsKey_value");
                if(bsKey == null){
                    bsKey = "YrW4cdcwhQsxxSLWscj2";
                }
                bsLocalArgs.put("key", bsKey);
                //# starts the Local instance with the required arguments
                System.out.println("bsLocalArgs testing...");
                bsLocal.start(bsLocalArgs);
                bsLocalArgs.put("forcelocal", "true");
                // check if BrowserStack local instance is running
                System.out.println(bsLocal.isRunning());
                System.out.println("bsLocalArgs testing completed...");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //use BeforeTest to rerun session after every suites
    @BeforeTest(alwaysRun = true )
    public void setup(ITestContext context) {

        logger.debug("Method : " +Thread.currentThread().getStackTrace()[1].getMethodName() );
        //Fetch the value of parallel attribute in xml
        String valueOfParallel =context.getCurrentXmlTest().getSuite().getParallel().toString();
        logger.info( "Parallel/Sequential : " +valueOfParallel);
        AtomicBoolean loopController = new AtomicBoolean(true);
        ISuite suite = context.getSuite();
        if(Arrays.asList(arrayOfValues).contains(valueOfParallel))
            suite.setAttribute("ParallelExecution", "Parallel");

        //parallelExecutionFlag will return none if suite do not contain "parallel" attribute
        // If mentioned, it must return the value
        String parallelFlag = (String) java.util.Optional.ofNullable(suite.getAttribute("ParallelExecution"))
                .orElse("Concurrent");
        logger.info("Execution Flag is : " + parallelFlag);

        //Concurrent- one time execution is permitted
        // Parallel - n number of times.
        if (loopController.get() )
        {
            logger.info("Creating an instance for driver...");
            try {

                InitiateDriver.getInstance().createDriver();
                setDriverForFramework(InitiateDriver.getInstance().getAppiumDriver());
//                setDriverForFramework(EventFiringWebDriverFactory.getEventFiringWebDriver(getDriverForFramework(), EventListener.getInstance()));

                //Flag that gets enabled to control the execution of beforeTest in parallel and concurrent fashion.
                loopController.set(parallelFlag.equalsIgnoreCase("Parallel")?true: false);

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Failure in beforeSuite ",e .getMessage());
                throw new RuntimeException("Driver setup has failed");
            }
        }

    }

    @AfterTest(alwaysRun = true)
    public void teardownDriverAfterTest(ITestContext context) {
        logger.debug("Method : " +Thread.currentThread().getStackTrace()[1].getMethodName() );
        String exeuctionFlag = String.valueOf(context.getSuite().getAttribute("ParallelExecution"));
        if(exeuctionFlag.equalsIgnoreCase("Parallel"))
        {
            getDriverForFramework().quit();
        }

    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAfterSuite(ITestContext context) {
        logger.debug("Method : " +Thread.currentThread().getStackTrace()[1].getMethodName() );
        try
        {
            String executionFlag = String.valueOf(context.getSuite().getAttribute("ParallelExecution"));
            if(!executionFlag.equalsIgnoreCase("Parallel")) {
                getDriverForFramework().quit();
            }
        } catch (Exception e) {
            logger.error("Failure in afterSuite ",e .getMessage());
//            throw new RuntimeException("Driver was already killed");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void disableBrowserStackLocalTesting() throws Exception{
        if(Boolean.parseBoolean(configProperties.get("LOCAL_TESTING"))){
            System.out.println("Disabling Local Testing");
            bsLocal.stop();
        }
    }


    private String encodeString(String text){
        String theString="";
        try{
            byte[] byteText = text.getBytes("ISO-8859-1");
            //To get original string from byte.
            theString =  new String(byteText , "UTF-8");
        }
        catch (UnsupportedEncodingException e){
            logger.error(e);
        }
        return theString;
    }

    public  String checkOS(){
        if (SystemUtils.IS_OS_WINDOWS) {
            return windowsPath;
        } else if (SystemUtils.IS_OS_MAC) {
            return linux_MacPath;
        } else if (SystemUtils.IS_OS_LINUX) {
            return linux_MacPath;
        } else{
            return null;
        }
    }


}
