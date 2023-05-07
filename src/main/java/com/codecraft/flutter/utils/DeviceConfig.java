package com.codecraft.flutter.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public class DeviceConfig {

    private static final Logger logger = LogManager.getLogger(DeviceConfig.class);
    private static DeviceConfig deviceConfigObj;

    private DeviceConfig() { };

    public static DeviceConfig getInstance() {
        if (deviceConfigObj == null)
            deviceConfigObj = new DeviceConfig();
        return deviceConfigObj;

    }

    /**
     * common function that will invoke getAndroidUDID or iOSUDID based on runOn value
     *
     * @param runON (android/iOS)
     * @return
     */
    public String getDeviceID(String runON) {
        logger.debug("fetching UDID/deviceID with runON value as " + runON);
        return runON.toLowerCase().equalsIgnoreCase("android") ? getAndroidUDID() : getiOSUDID();
    }

    /**
     * common function that will invoke getAndroidOSVersion or getIOSPlatformVersion based on runOn value
     *
     * @param runON (android/iOS)
     * @return
     */
    public String getPlatformVersion(String runON)
    {
        logger.debug("fetching platformVersion with runON value as " + runON);
        return runON.toLowerCase().equalsIgnoreCase("android") ? getAndroidOsversion() : getiOSPlatformVersion();

    }

    /**
     * common function that will invoke getAndroidDeviceName or getiOSDeviceName based on runOn value
     *
     * @param runON (android/iOS)
     * @return
     */
    public String getDeviceName(String runON)
    {
        logger.debug("fetching deviceName/ModelNumber with runON value as " + runON);
        return runON.toLowerCase().equalsIgnoreCase("android") ? getAndroidDeviceName() : getiOSDeviceName();

    }

    /****
     * Function will fetch UDID of android device[one] connected via USB. Calling methods can check for length of UDID
     *
     * use case 1: More than one android device- not tested.
     * Use case 2: no device connected- Null returned.
     *
     * Enhancement:
     * Enhance to pick the index value dynamically.
     * Scenario when more than one device is connected, it may not work.
     *
     */
    private String getAndroidUDID() {

        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executed ");
        try {
            Optional<String> adbCommandOutput = Optional.ofNullable(ReusuableUtility.getInstance().executeCommand("adb devices"));
            //lastIndex considered as "adb devices -l" , contain command out  "devices" in default description.
            long checkNumberOfConnectedDevice = Arrays.stream(adbCommandOutput.get().split(" ")).map(String::trim).filter(x -> x.lastIndexOf("device")>2).count();
            logger.debug("Actual number of connected devices " + (int) checkNumberOfConnectedDevice);
            logger.debug("device id :"+adbCommandOutput.get().substring(adbCommandOutput.get().indexOf("attached")+"attached".length()));
            if (checkNumberOfConnectedDevice == 1)
                //command output 'list of devices attached'
                return (adbCommandOutput.get().substring(adbCommandOutput.get().indexOf("attached")+"attached".length(), adbCommandOutput.get().lastIndexOf("device")-1)).trim();//24,40
                //return (adbCommandOutput.get().substring(24,40));
            logger.fatal("Either no device is connected or number of connected device is more than 1. Ensure to connect one device to a system via usb");
            return "Null";
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal("No device connected..." + e.getMessage());
            return "Null";
        }
    }



    //Function to return the deviceName
    private String getAndroidDeviceName()
    {
        try {
            logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
            Optional<String> modelNumber= Optional.empty();
            Optional<String> adbCommandOp = Optional.ofNullable(ReusuableUtility.getInstance().executeCommand("adb devices -l"));
            int firstIndex = adbCommandOp.get().lastIndexOf("device:");
            int lastIndex = adbCommandOp.get().lastIndexOf("transport");
            modelNumber=Optional.ofNullable(adbCommandOp.get().substring(firstIndex + 7, lastIndex).trim());
            return modelNumber.orElse("Null");
        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error("Model number identification failed " +e.getMessage());
            return "Null";
        }

    }

    private String getAndroidOsversion()
    {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        try
        {
            Optional<String> adbCommandOp= Optional.ofNullable(ReusuableUtility.getInstance().executeCommand("adb shell getprop ro.build.version.release"));
            logger.debug("OS Version value returned  " +adbCommandOp.get());
            return adbCommandOp.orElse("Null");
        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error("OS version identification failed " +e.getMessage());
            return "Null";

        }
    }


    private String getiOSUDID()
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        try {
            Optional<String> adbCommandOutput = Optional.ofNullable(ReusuableUtility.getInstance().executeCommand("idevice_id -l"));
            logger.debug("Length of UDID " + adbCommandOutput.get().length());
            //if device not connected,string with length 0 returned and not null
            if(adbCommandOutput.get().length()==0)
                return "Null";
            //Length of UDID is always 5
            if (adbCommandOutput.get().length() > 10)
                return adbCommandOutput.get();

            logger.fatal("Error encountered in identifying the UDID of iosDevice ");
            return "Null";
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal("No device connected..." + e.getMessage());
            return "Null";
        }
    }


    /***
     * Dependency:
     * Ideviceinstaller,libimobiledevice, usbmuxd and access(777) to var/db/lockdown is mandatory.
     * @return
     */
    private String getiOSDeviceName()
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        try
        {
            Optional<String> ioSDeviceName= Optional.ofNullable(ReusuableUtility.getInstance().executeCommand("idevicename"));
            return ioSDeviceName.orElse("Null");

//            String[] cmd = {
//                    "/bin/sh",
//                    "-c",
//                    "instruments -s devices | grep "+getiOSUDID()
//            };
//            String name = "";
//            Pattern pattern = Pattern.compile("([^(]*)");
//            Matcher matcher = pattern.matcher(ioSDeviceName.get());
//            if (matcher.find())
//            {
//                name = matcher.group(1);
//            }
//            return name;

        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error("iOS device name identification failed " +e.getMessage());
            return "Null";
        }
    }



    private String getiOSPlatformVersion()
    {
//        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executed");
        try
        {
            String[] cmd = {
                    "/bin/sh",
                    "-c",
                    "instruments -s devices | grep "+getiOSUDID()
            };
            Optional<String> getCommandOp= Optional.ofNullable(ReusuableUtility.getInstance().executeCommand(cmd));
//            String version = "";
//            Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
//            Matcher matcher = pattern.matcher(getCommandOp.get());
//            if (matcher.find())
//            {
//                version = matcher.group(1);
//            }
//            return version;
            logger.info("Index " + getCommandOp.get().indexOf("["+getiOSUDID()+"]"));
            int firstIndexValue = deviceConfigObj.getiOSDeviceName().length()+2;
            int lastIndexValue =getCommandOp.get().indexOf(deviceConfigObj.getiOSUDID())-3;
            return  getCommandOp.get().substring(firstIndexValue,lastIndexValue);


        }catch(Exception e)
        {
            e.printStackTrace();
//            logger.debug("Ios platform identification failed " + e.getMessage());
            return "NULL";
        }
    }
}


