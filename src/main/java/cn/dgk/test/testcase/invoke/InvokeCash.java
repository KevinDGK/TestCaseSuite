package cn.dgk.test.testcase.invoke;

import cn.dgk.test.util.DateUtil;
import cn.dgk.test.util.ImageUtil;
import cn.dgk.test.util.LogUtil;
import cn.dgk.test.util.WaitUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kevin on 2017/7/29.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InvokeCash extends InvokeBaseTestCase {

    @Test
    public void test01CashConsume() {

        // 随机生成外部订单号
        outTradeNo = DateUtil.getCurrentDate();

        testCase = "CashConsume";
        // 等待主界面
        WaitUtil.waitForActivity(".MainActivity");

        // 选择消费
        driver.findElementById("tv_consume").click();
        // 等待消费界面
        WaitUtil.waitForActivity(".biz.ConsumeActivity");
        // 输入外部订单号
        driver.findElementById("et_out_trade_no").sendKeys(outTradeNo);
        // 隐藏软键盘，注意，此时一定要隐藏软键盘，否则会导致软键盘盖住了下面的按钮，那么findElementById就会找不到
        driver.hideKeyboard();
        // 点击消费
        driver.findElementById("btn_consume").click();

        /* ---------- 进入收银界面 - In ---------- */
        // 等待收银现金界面
        WaitUtil.waitForActivity(".cash.CashActivity");
        // 等待确认按钮
        WaitUtil.waitForElement("com.wangpos.by.cashier3:id/bt_cash_button").click();
        // 点击返回按钮
        WaitUtil.waitForElement("com.wangpos.by.cashier3:id/bt_finish").click();
        /* ---------- 从收银界面退出 - Out ---------- */

        // 上滑查看底部的结果
        driver.swipe(360, 1000, 360, 300, 1000);
    }

    @Test
    public void test02CashRefund() {
        testCase = "CashRefund";
        // 判断当前界面是否是主界面
        assertEquals(".MainActivity", driver.currentActivity());

        // 选择退款
        driver.findElementById("tv_refund").click();
        // 等待退款界面
        WaitUtil.waitForActivity(".biz.RefundActivity");
        // 输入外部订单号
        driver.findElementById("et_out_trade_no").sendKeys(outTradeNo);
        // 隐藏软键盘，注意，此时一定要隐藏软键盘，否则会导致软键盘盖住了下面的按钮，那么findElementById就会找不到
        driver.hideKeyboard();
        // 点击退款
        driver.findElementById("btn_refund").click();

        WaitUtil.waitForActivity(".invoke.refund.InvokeRefundActivity");

        /* ---------- 进入收银界面 - In ---------- */
        // 开始退款
        // 等待返回按钮显示，并点击
        WaitUtil.waitForElement("com.wangpos.by.cashier3:id/bt_finish").click();
        /* ---------- 从收银界面退出 - Out ---------- */
    }

    @Test
    public void test03CashQuery() {
        testCase = "CashQuery";
        // 判断当前界面是否是主界面
        assertEquals(".MainActivity", driver.currentActivity());

        // 选择查询
        driver.findElementById("tv_query").click();
        // 等待查询界面
        WaitUtil.waitForActivity(".biz.CommonActivity");
        // 输入外部订单号
        driver.findElementById("et_out_trade_no").sendKeys(outTradeNo);
        // 隐藏软键盘，注意，此时一定要隐藏软键盘，否则会导致软键盘盖住了下面的按钮，那么findElementById就会找不到
        driver.hideKeyboard();
        // 点击查询
        driver.findElementById("btn_action").click();

        // 等待有结果返回
        WaitUtil.waitForResult("tv_result");
    }

    @Test
    public void test04CashPrint() {
        testCase = "CashPrint";
        // 判断当前界面是否是主界面
        assertEquals(".MainActivity", driver.currentActivity());

        // 选择重打印
        driver.findElementById("tv_print").click();
        // 等待重打印界面
        WaitUtil.waitForActivity(".biz.CommonActivity");
        // 输入外部订单号
        driver.findElementById("et_out_trade_no").sendKeys(outTradeNo);
        // 隐藏软键盘，注意，此时一定要隐藏软键盘，否则会导致软键盘盖住了下面的按钮，那么findElementById就会找不到
        driver.hideKeyboard();
        // 点击重打印
        driver.findElementById("btn_action").click();

        // 等待有结果返回
        WaitUtil.waitForResult("tv_result");
    }
}