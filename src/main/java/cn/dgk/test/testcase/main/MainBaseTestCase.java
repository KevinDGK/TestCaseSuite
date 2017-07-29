package cn.dgk.test.testcase.main;

import cn.dgk.test.util.DateUtil;
import cn.dgk.test.util.ImageUtil;
import cn.dgk.test.util.LogUtil;
import cn.dgk.test.util.WaitUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kevin on 2017/7/27.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainBaseTestCase {
    private static AndroidDriver<AndroidElement> driver;

    private static String outTradeNo = DateUtil.getCurrentDate();

    /**
     * 测试开始
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        LogUtil.i("beforeClass");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // 设备的序列号, 命令:adb devices
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "0123abcd");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        // 设备的版本号, 命令:adb shell cat /system/build.prop | findstr ro.build.version
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);

        // 启动方式一：安装apk并打开
//        File appDir = new File("apk");
//        File app = new File(appDir, "cashier3.apk");
//        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        // 启动方式二：直接打开已有应用
        // 被测应用的包名和类名，命令：adb logcat | findstr ActivityManager
        capabilities.setCapability("appPackage", "com.wangpos.by.cashier3");
        capabilities.setCapability("appActivity", ".splash.SplashActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        WaitUtil.init(driver);
    }

    @Before
    public void setUp() throws Exception {
        LogUtil.i("setUp");
    }

    @After
    public void tearDown() throws Exception {
        LogUtil.i("tearDown");
    }

    /**
     * 测试结束
     */
    @AfterClass
    public static void afterClass() {
        LogUtil.i("afterClass");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test01ConsumeCash() {
        String biz = "consumeCash";
        LogUtil.i("========== consumeCash start ==========");



        // Case启动等待
        WaitUtil.waitForTestStart();
        // 等待主界面
        WaitUtil.waitForActivity(".MainActivity");



        // Case停止等待
        WaitUtil.waitForTestStop();
        LogUtil.i("========== consumeCash stop ==========");
    }

    @Test
    public void test02RefundCash() {
        String biz = "RefundCash";
        LogUtil.i("========== RefundCash start ==========");


        // Case启动等待
        WaitUtil.waitForTestStart();

        // Case停止等待
        WaitUtil.waitForTestStop();
        LogUtil.i("========== RefundCash stop ==========");
    }
}