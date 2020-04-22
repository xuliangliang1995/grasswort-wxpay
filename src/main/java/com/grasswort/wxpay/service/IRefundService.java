package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.SSLContextSupportSignatureConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.RefundRequestBody;
import com.grasswort.wxpay.service.dto.RefundResponseBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xuliangliang
 * @Classname IRefundService.java
 * @Description 微信退款服务
 * @Date 2020/4/10
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.REFUND,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = SSLContextSupportSignatureConfiguration.class)
public interface IRefundService {
    /**
     * 微信退款服务
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.REFUND_PATH)
    RefundResponseBody refund(@RequestBody RefundRequestBody requestBody);
}
