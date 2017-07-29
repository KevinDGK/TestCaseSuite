package cn.dgk.test.testcase.invoke;

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

/**
 * Created by Kevin on 2017/7/27.
 */

public class InvokeBaseTestCase {

    protected static AndroidDriver<AndroidElement> driver;
    protected static String outTradeNo = "";

    protected String biz = "";
    protected String testCase = "";

    /**
     * 测试开始
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        LogUtil.i("beforeClass");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "0123abcd");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
        // 启动方式一：安装apk并打开
//        File appDir = new File("apk");
//        File app = new File(appDir, "cashier3.apk");
//        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        // 启动方式二：直接打开已有应用
        capabilities.setCapability("appPackage", "com.yxc.simulation.member");
        capabilities.setCapability("appActivity", ".MainActivity");

        driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        WaitUtil.init(driver);
    }

    @Before
    public void setUp() throws Exception {
        LogUtil.i("========== " + testCase + " setUp ==========");
    }

    @After
    public void tearDown() throws Exception {

        // 获取结果
        String tv_result = driver.findElementById("tv_result").getText();
        LogUtil.i(tv_result);

        // 截图保存
        ImageUtil.screenShot(biz, testCase, outTradeNo, driver);

        // 返回到Demo的主界面
        driver.findElementById("btn_back").click();

        LogUtil.i("========== " + testCase + " tearDown ==========");

        // 当一个case结束后，稍微等待一段时间，避免订单还未上报结束
        WaitUtil.waitForTestStop();
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
}