package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.dto.RefundRequestBody;
import com.grasswort.wxpay.service.dto.RefundResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname IRefundServiceTest.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
public class IRefundServiceTest extends BaseSpringTest {
    @Autowired
    private IRefundService refundService;

    private RefundRequestBody requestBody;

    @Before
    public void setUp() throws Exception {
        requestBody = RefundRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .nonceStr(RandomStringUtils.randomAlphabetic(32))
                .outTradeNo(SandBoxConstants.SandBoxCase.SuccessNotNotify.OUT_TRADE_NO)
                .notifyUrl("http://localhost/notiry")
                .outRefundNo(RandomStringUtils.randomAlphabetic(32))
                .refundFee(SandBoxConstants.SandBoxCase.SuccessNotNotify.FEE)
                .totalFee(SandBoxConstants.SandBoxCase.SuccessNotNotify.FEE)
                .refundFeeType("CNY")
                .build();
    }

    @After
    public void tearDown() throws Exception {
        requestBody = null;
    }

    @Test
    public void refund() {
        assertNotNull(refundService);
        RefundResponseBody responseBody = refundService.refund(requestBody);
        // 未找到对应沙箱用例
        assertEquals("微信支付退款申请通信失败", "FAIL", responseBody.getReturnCode());
    }
}