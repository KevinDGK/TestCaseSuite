package cn.dgk.test.util;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * Created by Kevin on 2017/7/29.
 */
public class LogUtil {

    private static Logger logger = Logger.getLogger(LogUtil.class);
    private static boolean isDebug = true;

    public static void i(Object msg) {
        if (isDebug) {
            logger.info(msg);
        }
    }

    public static void e(Object msg) {
        if (isDebug) {
            logger.error(msg);
        }
    }
}
