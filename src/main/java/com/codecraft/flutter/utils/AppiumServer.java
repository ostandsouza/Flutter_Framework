package com.codecraft.flutter.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Logger;

public class AppiumServer {

    Logger log = Logger.getLogger(AppiumServer.class.getName());
    AppiumDriverLocalService server;
    private static AppiumServer obj=null;
    HashMap<String, String> environment = new HashMap<>();
    /***
     * Use case1: Ability to start and stop  the server programmatically - - done.
     * Use Case 2: Ability to categorize based on platform( Windows or Mac)-Testing pending
     * Use Case 3: Dependency validation - Node.js, carthage, appium. js with logger - done . Enhancement required to handle negative inputs.
     * Use Case 4: Environment variable to handle the dependencies - done.
     * Use Case 5:  Integration
     *              - Env variables via jenkins(Node path and appium path)- in progress
     *Use case 6: Appium logs scrolling and capabilities- additional function to handle capabilities needed for appium server.
     *Use case 6: Documentation- pending
     *
     * PreConditions:
     * 1) Appium Desktop and Node.js installed.#platform specific
     * 2) Carthage installation, node.js and wd.js( carthage version , /usr/local/bin)
     * 3) Assumption granted: NPM installed along with node ( using NVM)
     */

    private AppiumServer(){};

    public static AppiumServer getInstance()
    {
        if(obj==null)
            obj = new AppiumServer();
        return obj;

    }

    /**
     * Enhancement: Start server when dependency checks are cleared.
     * 2) Relative path for logs.
     * 3) Determine the success of server based on status code. Else stop the progression.
     * @throws InterruptedException
     */
    public void init() throws InterruptedException {
        //Dependency check
        log.info("*********************");

        String platform = System.getProperty("os.name");
        log.info("Platform identified :" + platform);

        try {

            //dependency check.
            AppiumServer.getInstance().dependecyCheck();

            //AppiumServiceBuilder
            if (platform.contains("Mac")) {
                log.info("Starting appium server in mac.....");
                AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
                serviceBuilder.usingDriverExecutable(new File(environment.get("NodeJSPath")));
                serviceBuilder.withAppiumJS(new File(environment.get("AppiumJSPath")));
                //Reference to appium for default path.

                environment.put("PATH", "usr/local/bin" + System.getenv("PATH"));

                serviceBuilder.withEnvironment(environment);
                serviceBuilder.usingAnyFreePort();
                server = AppiumDriverLocalService.buildService(serviceBuilder);

                server = AppiumDriverLocalService.buildService( new AppiumServiceBuilder()
                        .usingDriverExecutable(new File(environment.get("NodeJSPath")))
                        .withAppiumJS(new File(environment.get("AppiumJSPath")))
                        .withEnvironment(environment)
                        .usingPort(AppiumServer.getInstance().getPort())
                        .withLogFile(new File("/Users/vijayanand/Documents/Repository/Misc/AppiumServer.log")));


                server.start();

                log.info("Server started with status code - ");

            } else if (platform.contains("Windows")) {

                //Need to test this in windows.
                server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingPort(4723));

            } else {
                log.info("Unsupported platform .Identified - " + platform);

            }
        }catch(Exception e )
        {
            log.info("Server start process failed.....");
            e.printStackTrace();

        }
    }

    /***
     * Enhancement:
     * Limit the possiblity to start the server if dependency check fails.
     *
     */
    public  void dependecyCheck()
    {
        //debug
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        //node.js verification
        Optional<String> nodeJsPath= Optional.ofNullable(AppiumServer.getInstance().getNodeJsPath());
        //carthage verification- package manager to manage iOS developement libraries.
        Optional<String> carthageVersion = Optional.ofNullable(AppiumServer.getInstance().isCarthageInstalled());
        Optional<String> appiumJSPath = Optional.ofNullable(AppiumServer.getInstance().isAppiumInstalled());

        //Check if appium installation - have to check if installed via appium gui

        //Adding to env variable.
        nodeJsPath.ifPresent(a->carthageVersion.ifPresent(b -> appiumJSPath.ifPresent(
                y->{
                    environment.put("NodeJSPath", nodeJsPath.get());
                    environment.put("AppiumJSPath", "/usr/local/lib/node_modules/appium/build/lib/appium.js");
                }
        )));
        environment.entrySet().stream().forEach(x->{
            log.info(""+ x.getKey() + "" + x.getValue());
        });

    }

    /***
     * Enhancement:"
     * 1. Execution based on platform
     * 2. Check for any active instance.
     * @return
     */
    public String getNodeJsPath()
    {
        Optional<String> result= Optional.empty();
        try
        {
            log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
            result= Optional.ofNullable(AppiumServer.getInstance().executeCommand("node -v"));
            log.info("Version identified" + result.get());
            if(result.isPresent() & result.get().contains("v12"))
                log.info("Finding path of node.js file");
            return result.map(x->{
                return AppiumServer.getInstance().executeCommand("which node");
            }).orElse("Null");
        }catch(Exception e )
        {
            e.printStackTrace();
            result= Optional.empty();
        }
        return result.orElse("Null");
    }

    public String isCarthageInstalled()
    {

        Optional<String> carthageInstallation= Optional.empty();
        try
        {
            log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
            log.info("Carthage Version identification in progress ");
            carthageInstallation= Optional.ofNullable(AppiumServer.getInstance().executeCommand("carthage version"));
            log.info("Carthage Version identified" + carthageInstallation.get());
        }catch(Exception e )
        {
            e.printStackTrace();
            carthageInstallation= Optional.empty();
        }
        return carthageInstallation.orElse("Null");

    }

    /***
     * Enhancement needed to handle the GUI installation and if command is throwing appropriate version.
     * @return
     */
    public String isAppiumInstalled()
    {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        Optional<String> appiumInstallation= Optional.empty();

        try
        {
            log.info("Verifying if appium installed.... ");
            appiumInstallation= Optional.ofNullable(AppiumServer.getInstance().executeCommand("appium -v"));

        }catch(Exception e)
        {
            e.printStackTrace();
            appiumInstallation = Optional.empty();
        }
        return appiumInstallation.orElse("Null");

    }

    public  void  addEnvVariable()
    {

    }

    public  void tearDown()
    {
        log.info("Stopping server...");
        server.stop();
    }

    /**
     * Identify existing appium server instance 
     * @return
     * @throws Exception
     */
    public int getPort() throws Exception
    {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        int port = socket.getLocalPort();
        socket.close();
        return port;
    }

    public String executeCommand(String command)
    {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        try {
            //ProcessBuilder is recommended if dependency on environment variable is high.
            log.info("Command received for execution " + command);
            Process process = Runtime.getRuntime().exec(command);
            log.info("Command executed. Parsing results....");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String parsedResult ="";
            while((parsedResult= reader.readLine())!=null) {
                sb.append(parsedResult);
                log.info("Parsed results " + parsedResult);

            }

            return sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Null";

    }


}
