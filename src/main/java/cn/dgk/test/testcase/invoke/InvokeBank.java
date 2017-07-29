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
public class InvokeBank extends InvokeBaseTestCase {

    @Test
    public void test01BankConsume() {

        // 随机生成外部订单号
        outTradeNo = DateUtil.getCurrentDate();

        testCase = "BankConsume";
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
        // 选择支付方式-银行卡
        driver.findElementById("rb_bank").click();
        // 输入支付金额
        driver.findElementById("et_total_fee").sendKeys("1");
        // 隐藏软键盘
        driver.hideKeyboard();
        // 点击消费
        driver.findElementById("btn_consume").click();

        /* ---------- 进入收银界面 - In ---------- */
        // 等待收银银行卡界面
        WaitUtil.waitForActivity(".bankcard.BankCardActivity");
        // 点击密码键盘确定按钮，小额免密
        // TODO: 2017/7/29 该位置点击报错啦!
        WaitUtil.waitForElement("cn.weipass.service:id/figure12").click();
        // 输入手势密码
        driver.swipe(200, 900, 500, 1100, 1000);
        // 确定手势密码
        driver.findElementById("com.wangpos.by.cashier3:id/tv_confirm").click();
        // 点击返回按钮
        WaitUtil.waitForElement("com.wangpos.by.cashier3:id/bt_finish").click();
        /* ---------- 从收银界面退出 - Out ---------- */

        // 上滑查看底部的结果
        driver.swipe(360, 1000, 360, 300, 1000);
    }

    @Test
    public void test02BankRefund() {
        testCase = "BankRefund";
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
    public void test03BankQuery() {
        testCase = "BankQuery";
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
    public void test04BankPrint() {
        testCase = "BankPrint";
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