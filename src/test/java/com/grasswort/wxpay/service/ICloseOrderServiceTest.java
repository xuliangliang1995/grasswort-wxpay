package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.dto.CloseOrderRequestBody;
import com.grasswort.wxpay.service.dto.CloseOrderResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname ICloseOrderServiceTest.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
public class ICloseOrderServiceTest extends BaseSpringTest {
    @Autowired
    private ICloseOrderService closeOrderService;

    private CloseOrderRequestBody requestBody;

    @Before
    public void setUp() throws Exception {
        requestBody = CloseOrderRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .nonceStr(RandomStringUtils.randomAlphanumeric(32))
                .outTradeNo(SandBoxConstants.SandBoxCase.SuccessNotNotify.OUT_TRADE_NO)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        requestBody = null;
    }

    @Test
    public void closeOrder() {
        assertNotNull(closeOrderService);
        CloseOrderResponseBody responseBody = closeOrderService.closeOrder(requestBody);
        assertEquals("微信支付关闭订单通信失败", "SUCCESS", responseBody.getReturnCode());
    }
}