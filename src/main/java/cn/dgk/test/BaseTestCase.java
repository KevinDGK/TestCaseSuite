package cn.dgk.test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kevin on 2017/7/27.
 */
public class BaseTestCase {
    private static AndroidDriver<AndroidElement> driver;

    private List<String> ourTradeNoList;
    private String outTradeNo;
    private Logger logger = Logger.getLogger(BaseTestCase.class);;

    /**
     * 测试开始
     */
    @BeforeClass
    public static void beforeClass() throws Exception {

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
    }

    @Before
    public void setUp() throws Exception {
//        Activity activity = new Activity("com.wangpos.by.cashier3", ".splash.SplashActivity");
//        driver.startActivity(activity);

        ourTradeNoList = new ArrayList<String>();
        outTradeNo = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * 测试结束
     */
    @AfterClass
    public static void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void consume() {
        logger.info("consume");

        sleep(5);
        assertEquals(driver.currentActivity(), ".MainActivity");

        driver.findElementById("tv_consume").click();

        AndroidElement et_out_trade_no = driver.findElementById("et_out_trade_no");
        et_out_trade_no.sendKeys(outTradeNo);

        AndroidElement et_print_note = driver.findElementById("et_print_note");
        et_print_note.click();
        et_print_note.setValue("新店开业，一律" + new Random().nextInt(10) + "折扣");

        AndroidElement btn_consume = driver.findElementById("btn_consume");
        btn_consume.click();

        sleep(100);
    }

    /**
     * 睡眠
     */
    public void sleep(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
