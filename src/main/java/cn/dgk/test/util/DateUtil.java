package cn.dgk.test.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Kevin on 2017/7/29.
 */
public class DateUtil {

    /**
     * 返回当前时间
     * - 格式为"yyyyMMddHHmmss"
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyyMMddHHmmss",Locale.getDefault()).format(System.currentTimeMillis());
    }
}
