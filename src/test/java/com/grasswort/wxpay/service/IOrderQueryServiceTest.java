package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.constants.TradeStateEnum;
import com.grasswort.wxpay.service.dto.OrderQueryRequestBody;
import com.grasswort.wxpay.service.dto.OrderQueryResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname IOrderQueryServiceTest.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
public class IOrderQueryServiceTest extends BaseSpringTest {
    @Autowired
    private IOrderQueryService orderQueryService;

    private OrderQueryRequestBody requestBody;

    @Before
    public void setUp() throws Exception {
        requestBody = OrderQueryRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .outTradeNo(SandBoxConstants.SandBoxCase.SuccessNotNotify.OUT_TRADE_NO)
                .nonceStr(RandomStringUtils.randomAlphabetic(32))
                .build();
    }

    @After
    public void tearDown() throws Exception {
        requestBody = null;
    }

    @Test
    public void orderQuery() {
        assertNotNull("微信支付订单查询服务 IOrderQueryService 注入失败", orderQueryService);
        OrderQueryResponseBody responseBody = orderQueryService.orderQuery(requestBody);
        assertEquals("微信支付订单查询通信失败", "SUCCESS", responseBody.getReturnCode());
    }
}