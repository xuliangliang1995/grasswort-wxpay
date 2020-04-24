package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.SignatureConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.OrderQueryRequestBody;
import com.grasswort.wxpay.service.dto.OrderQueryResponseBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xuliangliang
 * @Classname IOrderQueryService.java
 * @Description 订单查询服务
 * @Date 2020/4/22
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.ORDER_QUERY,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = SignatureConfiguration.class)
public interface IOrderQueryService {
    /**
     * 订单查询
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.ORDER_QUERY_PATH)
    OrderQueryResponseBody orderQuery(@RequestBody OrderQueryRequestBody requestBody);
}
