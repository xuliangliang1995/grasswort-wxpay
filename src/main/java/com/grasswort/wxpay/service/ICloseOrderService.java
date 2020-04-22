package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.SignatureConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.CloseOrderRequestBody;
import com.grasswort.wxpay.service.dto.CloseOrderResponseBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xuliangliang
 * @Classname ICloseOrderService.java
 * @Description 关闭订单服务
 * @Date 2020/4/22
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.CLOSE_ORDER,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = SignatureConfiguration.class)
public interface ICloseOrderService {

    /**
     * 关闭订单
     * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.CLOSE_ORDER_PATH)
    CloseOrderResponseBody closeOrder(CloseOrderRequestBody requestBody);
}
