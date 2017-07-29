package cn.dgk.test.util;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

/**
 * 图片工具类
 * Created by Kevin on 2017/7/29.
 */
public class ImageUtil {

    /**
     * 屏幕截图
     */
    public static void screenShot(AndroidDriver driver) {
        screenShot("", "", "", driver);
    }

    /**
     * 屏幕截图
     * @param biz   业务名称
     * @param testCase  用例名称
     * @param tag   标记，比如可以是订单号/流水号
     * @param driver
     */
    public static void screenShot(String biz, String testCase, String tag, AndroidDriver driver) {
        try {
            // 截图
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            // 截图保存目标文件
            String bizTemp = TextUtils.isEmpty(biz) ? "biz" : biz;
            String testCaseTemp = TextUtils.isEmpty(testCase) ? "testcase" : testCase;
            String tagTemp = TextUtils.isEmpty(tag) ? "" : ("-" + tag);
            File destFile = new File("result/screenshot/" + bizTemp+ "/" + testCaseTemp + tagTemp + ".png");
            // 将截图复制到目标目录文件中
            FileUtils.copyFile(screenshot, destFile, true);
            // 删除掉原文件
            screenshot.delete();
            LogUtil.i("截图保存：" + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}