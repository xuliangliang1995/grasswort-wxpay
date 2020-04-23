package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.dto.RefundQueryRequestBody;
import com.grasswort.wxpay.service.dto.RefundQueryResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname IRefundQueryServiceTest.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
public class IRefundQueryServiceTest extends BaseSpringTest {
    @Autowired
    private IRefundQueryService refundQueryService;

    private RefundQueryRequestBody requestBody;

    @Before
    public void setUp() throws Exception {
        requestBody = RefundQueryRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .nonceStr(RandomStringUtils.randomAlphabetic(32))
                .outTradeNo(SandBoxConstants.SandBoxCase.SuccessNotNotify.OUT_TRADE_NO)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        requestBody = null;
    }

    @Test
    public void refundQuery() {
        assertNotNull(refundQueryService);
        RefundQueryResponseBody responseBody = refundQueryService.refundQuery(requestBody);
        // 未找到退款查询对应沙箱用例
        assertEquals("微信支付退款查询通信失败", "FAIL", responseBody.getReturnCode());
    }
}