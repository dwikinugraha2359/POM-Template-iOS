package com.media2359.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.media2359.helper.HelperAppium;
import com.media2359.helper.HelperFunc;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.net.ServerSocket;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    public IOSDriver<IOSElement> driver;
    public WebDriverWait wait;
    HelperFunc helperFunc = new HelperFunc();
    HelperAppium helperAppium = new HelperAppium();

    @BeforeClass
    @Parameters({ "deviceName", "platformVersion", "platformName", "udid", "xcodeOrgId", "xcodeSigningId", "appiOS",
            "wdaLocalPort", "appPath", "bundleId" })
    public void setup(String deviceName, String platformVersion, String platformName, String udid, String xcodeOrgId,
            String xcodeSigningId, String appiOS, String wdaLocalPort, String appPath, String bundleId)
            throws MalformedURLException {
        // Prepare Appium session

        int port = 4725;
        if (!helperAppium.checkIfServerIsRunnning(port)) {
            System.out.println("Appium Server not running on Port – " + port);
            // Start Appium Server
            helperAppium.startServer(port);
        } else {
            System.out.println("Appium Server already running on Port – " + port);
            // Start Appium Server
            port = 4726;
            // check if port running
            // if running, stop the port
            if (!helperAppium.checkIfServerIsRunnning(port)) {
                System.out.println("Appium Server not running on Port – " + port);
                // Start Appium Server
                helperAppium.startServer(port);
            } else {
                // stop Appium Server
                helperAppium.stopServer();
                // Start Appium Server
                helperAppium.startServer(port);
            }
        }

        initDriveriOS(deviceName, platformVersion, platformName, udid, xcodeOrgId, xcodeSigningId, appiOS, wdaLocalPort,
                port, appPath, bundleId);

    }

    @AfterClass
    public void tearDown() {
        // Close session
        driver.quit();
        // stop Appium Server
        helperAppium.stopServer();
    }

    @BeforeMethod
    public void beforeMethodStart() throws IOException {
        // Clean download Directory on test result
        helperFunc.cleanDownloadDir();
    }

    @AfterMethod // AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) {
        // using ITestResult.FAILURE is equals to result.getStatus then it enter into if
        // condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                // Call method to capture screenshotF
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location
                // result.getName() will return name of test case so that screenshot name will
                // be same as test case name
                FileUtils.copyFile(src, new File("screenshoot/" + helperFunc.generateDateNHour() + "/"
                        + result.getName() + "-" + result.getEndMillis() + ".png"));
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    public static String userName = "prizka1";
    public static String accessKey = "maD2oMfQUa7EP81bxEF2";

    private void initDriveriOS(String deviceName, String platformVersion, String platformName, String udid,
            String xcodeOrgId, String xcodeSigningId, String appiOS, String wdaLocalPort, int appiumPort,
            String appPath, String bundleId) throws MalformedURLException {
        System.out.println("Inside initDriver iOS method");

        // ***** app path *****
        /*
         *
         * For using app url path: - change value appIos
         *
         * Remove Below
         */
        File f = new File("file");
        File fs = new File(f, appPath);
        appiOS = fs.getAbsolutePath();

        DesiredCapabilities cap = new DesiredCapabilities();
        // cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        // cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        // cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        // cap.setCapability(MobileCapabilityType.UDID, udid); // 2359 media phone
        // cap.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, xcodeOrgId);
        // cap.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, xcodeSigningId);

        // // cap.setCapability(IOSMobileCapabilityType.SHOW_XCODE_LOG, "true");
        // // cap.setCapability(IOSMobileCapabilityType.SHOW_IOS_LOG, "true");

        // capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        // // capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        // capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        // cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        // cap.setCapability("wdaStartupRetryInterval", "1000");
        // cap.setCapability("wdaLocalPort", wdaLocalPort);
        // cap.setCapability("useNewWDA", false);
        // cap.setCapability("waitForQuiescence", true);
        // cap.setCapability("shouldUseSingletonTestManager", false);
        // // cap.setCapability("clearSystemFiles", true);

        // // permission set "always"
        // // cap.setCapability("autoGrantPermissions", true);
        // // cap.setCapability("permissions", "{\"com.2359media.nhg\": {\"location\":
        // // \"always\"}}");

        // cap.setCapability(MobileCapabilityType.APP, appiOS);
        // // cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleId);

        cap.setCapability("os_version", "12");
        cap.setCapability("device", "iPhone 6S");
        cap.setCapability("app", "bs://e6e6a165815eddb0723b14830beff5851f66c793");

        // // Initialize driver
        // driver = new IOSDriver(new URL("http://127.0.0.1:" + appiumPort + "/wd/hub"),
        // cap);
        driver = new IOSDriver(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),
                cap);

        wait = new WebDriverWait(driver, 10);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}