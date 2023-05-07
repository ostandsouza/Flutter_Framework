This Project aims to Automate Mobile Application[Android/iOS]

### Setting up development machine


Download the binaries listed and set path. Tested versions
are listed in POM.xml


```sh
Android SDK
JDK - java8/ Recommended 14 
Intellij IDEA
Maven
TestNG Dependency
Selenium JAR
Appium client
NPM
Node
Appium Desktop
Xcode
HomeBrew
ios-deploy
authorize-ios
iDeviceInstaller/LibiMobileDeivces 
AndroidStudio
Flutter SDK
Developer Source Code
To validate the installation of the installation of the Appium on local system, install the latest version of appium-doctor tool and execute the command to see the correctness of . npm install appium-doctor -g To verify that all of Appium's dependencies, use this command 'appium-doctor' to validate the system correctness

```


### Software versions

Listed below are the versions considered.

| Software | Commands | 
| ------ | ------ |
| adb | [adb --version][1.0.41] |
| Git | [git --version][2.14.1] |
| Allure | [allure --version][2.13.5] |
| Java | [java -version][1.8.0_251] |
| Appium Client | | [1.20.2-4]
| Maven version | [mvn --version][3.6.3] |


### Infra dependencies

Following items are expected to start with framework


* Java_Home
* IDE ( intellij)
* USB debugging and developer options enabled in iOS and Android( native app testing)
* Enable/disable autofill keyboard in android and iOS(eg:Settings -> Language and Input -> Autofill -> None)
* Disable "Security input" in android device.
* Enable "Install via usb " in the android device (developer option)
* Connectivity to internet is mandatory for successful execution. 

### Guide to start with framework

Guide to start with framework. 

* Click on config.properties and set your values. Instructions are available.
* Download all maven dependencies and compile with no errors identified.
* Click on credentials.properties and provide your credentials[Optional].
* Test folder will accommodate business logic validation
* Create your testng.xml . Path to xml is accepted via maven command. 
* Pages will accommodate the element validation.
* Page object repository should contain the locator strategy.
* Device type[ android or iOS] is decided based on the profile passed.
* To automate Safari instead of your own application, leave the app capability empty and instead set the browserName capability to Safari.
* Update the path of TestNG.xml in <defaultSuiteFile> tag present in properties section.
  [eg:<defaultSuiteFiles>src/test/java/com/web/template/WebTemplateDummy.xml</defaultSuiteFiles>].
* Pass the xml to replace the default one via maven command[refer command section]
* Parallel execution is supported only in browserstack.
* Recommended way to maintain dependency management is having a child POM (maven inheritance).
* It is recommended to pass xml at run time for a project with multiple xml maintained. Refer readme.md file 
to learn about command.
 Always prefer to execute the test from maven command[ refer command section].
* Verify the result by utilizing  TestNG default report , TestNG customized report and allure 
report as well[target folder].

## Portability of framework

* Effectively utilization of dependency management is by creating child pom.xml [maven inheritance]
  or pom.xml specific to project[ multiple pom.xml for a project].
* Multi Module design concept is suggested by team . 
* Framework is portable to maintain multiple xml's [sanity,regression and smoke].Respective xml
  can be invoked at run time via maven command.
* Usage of maven command to execute single or group of tests is recommended. Insisting on avoid disabling 
  block of code in TestNG.xml. Refer command section to identify maven command.
* Framework is portable to report soft and hard assertions .
* Retry count is set to zero by default in retry-analyzer.java
* Jenkinsfile is specific to project . Customization is required based on your project.
* Include paralell="Tests" for parallel or remove it for concurrent execution in TestNG xml.
* Inclusion of parameter tag at "<test>" level in TestNG xml can execute the test in simulators with setup value as "local" and ".app" file added.
  [or] you can pass "mvn clean test -Denvironment=samsung_s9 "  will execute in simulator[local]. 
* mvn clean test -P bsAndroid -Denvironment=samsung_s9 will trigger the specified device in BS.Executing xml is 
recommended for multiple device configuration.
* One can pass BSID or customer ID to "AndroidID" or "iOS ID " available in device_config.json
* Recommended to maintain separate xml for browserStack, Simulator and Local
* Framework do not support execution in website.


### How to prepare test data file

TestData preparation is a one time activity. Success depends on the accuracy of test data.
Base code of the framework do not have a space to prepare testdata. Integration is possible based on customer requirement.

### Key notes to designer

- Usage of driver.hideKeyboard() is mandatory if default device keyboard is Unicode.
- Prefer softAssertions scenario when you want to report any of the sub step as a failure , execute the remaining steps and then mark the main test as test failed.

 ### known issues
 
 Refer this URL to understand on known issues.
 https://gitlab.codecrafttech.com/mercury/mercury-automation/-/issues


### Executing file from maven:

```sh
Maven command  to
{ execute the xml specified in '<defaultSuiteFiles>'
  device as 'android' 
  type as 'local'
}
$ mvn clean test

[or]

Maven command  to
{ replace(substitue) the xml specified in '<defaultSuiteFiles>'
  device as 'android' 
  type as 'local'
}
$ mvn clean test -DsuiteFile=src/test/java/com/web/template/WebTemplateDummy2.xml

[or]

Maven command  to
{ execute the xml specified in '<defaultSuiteFiles>'
  device as 'iOS' 
    type as 'local'
}
mvn clean test -P iOS

//-DsuiteFile activites xml other than default one.
//-P activates profile [browser] other than default one [chrome].

[or] 

Scenario when you prefer to run a single Test, here is the command "mvn -Dtest=<fullyqualifiedclassname> Test"

mvn clean -Dtest=com.web.template.RegisterTest.RegistrationTest test
     // pasted command can be extended to run multiple test or methods
     // mvn clean -Dtest=Test1,Test2 test , mvn clean -Dtest=Test1#methodName+methodName2
     // test and more.

[or]
Scenario when you prefer to run specified device configuration in BrowserStack.

mvn clean test -P bsAndroid -Denvironment=samsung_s9

mvn clean test -P bsAndroid -Dtest=com.mobile.mercury.LoginTests.LoginTests -Denvironment=samsung_s9

[or]

Scenario when you prefer to run specified device configuration in BrowserStack with BSID passed.


mvn clean test -P bsAndroid -Dtest=com.mobile.mercury.LoginTests.LoginTests -Denvironment=samsung_s9 -DbsId=""



```

### Generating allure report:
```sh
$ allure generate allure-results --clean -o allure-report
or
$ allure serve allure-results
```

### Installing dependencies:

```sh
1. Install Appium using the following command:
$ npm install -g appium@next
2. Install carthage using the following command:
$ brew install carthage
3. Install CocoaPods
$ brew install cocoapods
4. Open your project in xCode
5.  In Terminal navigate to your project folder and run pod install to have all dependencies:
$ pod install
6. Make sure appium is running 
$ node .
7. authorize iOS
$ npm install -g authorize-ios
8. iOS-deploy
$ brew install ios-deploy
9. iDevice installer
$ brew install ideviceinstaller
10. IOS WebKit Debug proxy
$ brew install ios-webkit-debug-proxy

```

### Appium 2.0 changes:

```sh
1. Default server base path:
$ appium --base-path=/wd/hub
2. Installing drivers during setup
$ appium driver install uiautomator2 
3. Drivers installation path
$. cd $APPIUM_HOME/node_modules/appium-xcuitest-driver/node_modules/appium-webdriveragent
4. Driver updates
$ appium driver list --updates
5. Capabilities
$ BaseOptions options = new UiAutomator2Options();
6. Protocol changes
$ JSONWP -> W3C WebDriver Protocol

```

### To Edit bash profile:
```sh
$ vim ~/.bash_profile
```

### Adb command to find app package and activity:
```sh
$ adb shell dumpsys window | grep -E 'mCurrentFocus'

```

### Maven run:
```sh
mvn test -P iOS
mvn test -P iOS -DContext.value=XCUITest
mvn test -P android
mvn test -P android -DSetup.value=BrowserStack
```

### BrowserStack Upload commands:
```sh
curl -u "bs_id:bs_key" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/Users/Downloads/Payload.ipa" \                                              
-F "custom_id=IOSApp"

{"app_url":"bs://d6e6813dbda148537e64ee906a55e22ef2cb9ffa","custom_id":"MercuryFlutterIOSApp","shareable_id":"prasanjitkar_hG9efA/MercuryFlutterIOSApp"}
```
```sh
curl -u "bs_id:bs_key" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/Users/android_build/app-qa-debug.apk" \
-F "custom_id=AndroidApp"

{"app_url":"bs://014808292ee819c225c1038faf488b56436cfb58","custom_id":"MercuryFlutterAndroidApp","shareable_id":"prasanjitkar_hG9efA/MercuryFlutterAndroidApp"}
```
### Source code setup:
```sh
1. Android studio install flutter and dart plugins
2. SDK tools -> install Cmake, buikd tools, NDK
3. Configure perference -> Flutter, Dart
4. Run Configuration -> configure dart entrypoint file.
5. Instrument the entrypoint file -> Flutter extentions
```

### Source code build commands:
#### build APK
```sh
flutter build apk -t lib/environments/main_qa5.dart --profile --flavor qa
```

#### build iOS
```sh
flutter build ios -t lib/environments/main_qa5.dart --profile --flavor qa
```

#### Maven Command
mvn clean test -P android -Denvironment="SamsungGalaxyS22BS_12V" -DSetup_value="BrowserStack"
mvn clean test -P iOS -Denvironment="IPhone14BS_16V" -DSetup_value="BrowserStack"

### From Allure bin path Result:
allure serve /target/allure-results
### OR from project main path
/.allure/allure-2.8.1/bin/allure serve target/allure-results