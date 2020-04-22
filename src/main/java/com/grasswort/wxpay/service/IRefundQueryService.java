package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.SignatureConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.RefundQueryRequestBody;
import com.grasswort.wxpay.service.dto.RefundQueryResponseBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xuliangliang
 * @Classname IRefundQueryService.java
 * @Description 退款查询
 * @Date 2020/4/22
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.REFUND_QUERY,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = SignatureConfiguration.class)
public interface IRefundQueryService {

    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.REFUND_QUERY_PATH)
    RefundQueryResponseBody refundQuery(RefundQueryRequestBody requestBody);
}
