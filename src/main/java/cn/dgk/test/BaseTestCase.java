package cn.dgk.test;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.assertEquals;

/**
 * Created by Kevin on 2017/7/27.
 */
public class BaseTestCase {
    private static AppiumDriverLocalService service;
    private static AndroidDriver<AndroidElement> driver;

    /**
     * initialization.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
//        service = AppiumDriverLocalService.buildDefaultService();
//        service.start();
//
//        if (service == null || !service.isRunning()) {
//            throw new AppiumServerHasNotBeenStartedLocallyException(
//                    "An appium server node is not started!");
//        }

        File appDir = new File("apk");
        File app = new File(appDir, "cashier3.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "e49e90e");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.4");

        // 方式一：安装apk
//        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        // 方式二：直接打开应用的界面
        capabilities.setCapability("appPackage", "com.wangpos.by.cashier3");
        capabilities.setCapability("appActivity", ".splash.SplashActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @org.junit.Before
    public void setUp() throws Exception {
//        Activity activity = new Activity("com.wangpos.by.cashier3", ".splash.SplashActivity");
//        driver.startActivity(activity);
    }

    @Test
    public void startActivityInThisAppTestCase() {

        try {
            System.out.println("================= 1");
            Thread.sleep(5000);
            System.out.println("================= 2");
            assertEquals(driver.currentActivity(), ".main.MainActivity");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * finishing.
     */
    @AfterClass
    public static void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
