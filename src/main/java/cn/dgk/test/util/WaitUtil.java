package cn.dgk.test.util;

import com.google.common.base.Function;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.functions.AppiumFunction;
import org.aspectj.apache.bcel.generic.Tag;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin on 2017/7/29.
 */
public class WaitUtil {

    /**
     * 等待机制，可以查看
     * {@link FluentWait#until(java.util.function.Function)}
     * 在FluentWait的until的方法中可以看到一个死循环，一直在调用传入的Function的apply方法，
     * 一直到apply()方法返回值不是Boolean类型并且不会null，或者为Boolean类型但是为true。
     */
    public static Wait<AndroidDriver<AndroidElement>> wait;

    public static void init(AndroidDriver driver) {
        wait = new FluentWait<AndroidDriver<AndroidElement>>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NotFoundException.class);
    }

    /**
     * 睡眠等待
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 单个测试开始
     */
    public static void waitForTestStart() {
        sleep(5);
    }

    /**
     * 单个测试结束
     */
    public static void waitForTestStop() {
        sleep(5);
    }

    /**
     * 等待界面
     */
    public static void waitForActivity(final String activityName) {
        if (StringUtils.isEmpty(activityName)) return;
        LogUtil.i("waitForActivity:" + activityName);
        wait.until(new Function<AndroidDriver, Object>() {
            public Object apply(AndroidDriver driver) {
                // 直到返回true
                return activityName.equals(driver.currentActivity());
            }
        });
    }

    /**
     * 等待控件
     */
    public static AndroidElement waitForElement(final String id) {
        if (StringUtils.isEmpty(id)) return null;
        LogUtil.i("waitForElement:" + id);
        return wait.until(new Function<AndroidDriver<AndroidElement>, AndroidElement>() {
            public AndroidElement apply(AndroidDriver<AndroidElement> driver) {
                // 直到返回的不为null
                return driver.findElementById(id);
            }
        });
    }

    /**
     * 等待结果
     */
    public static void waitForResult(final String id) {
        if (StringUtils.isEmpty(id)) return;
        LogUtil.i("waitForResult:" + id);
        wait.until(new Function<AndroidDriver, Object>() {
            public Object apply(AndroidDriver driver) {
                // 直到返回true
                return !StringUtils.isEmpty(driver.findElementById(id).getText());
            }
        });
    }
}