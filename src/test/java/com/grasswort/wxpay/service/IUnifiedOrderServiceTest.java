package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.dto.UnifiedOrderRequestBody;
import com.grasswort.wxpay.service.dto.UnifiedOrderResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname IUnifiedOrderServiceTest.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
public class IUnifiedOrderServiceTest extends BaseSpringTest {
    @Autowired
    private IUnifiedOrderService unifiedOrderService;

    private SimpleDateFormat sdf;

    private UnifiedOrderRequestBody requestBody;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        requestBody = UnifiedOrderRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .openid("oUpF8uMEb4qRXf22hE3X68TekukE")
                .attach("附加数据")
                .body("商品描述")
                .detail("商品详情")
                .deviceInfo("设备信息")
                .feeType("CNY")
                .totalFee(SandBoxConstants.SandBoxCase.SuccessNotNotify.FEE)
                .nonceStr(RandomStringUtils.randomAlphabetic(32))
                .notifyUrl("http://localhost/notify")
                .outTradeNo(SandBoxConstants.SandBoxCase.SuccessNotNotify.OUT_TRADE_NO)
                .productId("1")
                .spbillCreateIp("127.0.0.1")
                .timeExpire(sdf.format(new Date()))
                .timeStart(sdf.format(new Date(new Date().getTime() + 1000 * 60 + 10)))
                .tradeType("JSAPI")
                .build();
    }

    @After
    public void tearDown() throws Exception {
        sdf = null;
        requestBody = null;
    }

    @Test
    public void unifiedOrder() {
        assertNotNull("微信统一下单服务 Bean 注入失败", unifiedOrderService);
        UnifiedOrderResponseBody responseBody = unifiedOrderService.unifiedOrder(requestBody);
        assertEquals("微信统一下单返回异常：" + responseBody.getReturnMsg(), "SUCCESS", responseBody.getReturnCode());
        assertTrue("微信统一下单接口未能获取到 prepay_id", StringUtils.isNotEmpty(responseBody.getPrepayId()));
    }
}