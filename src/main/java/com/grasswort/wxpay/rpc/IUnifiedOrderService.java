package com.grasswort.wxpay.rpc;

import com.grasswort.wxpay.rpc.config.UnifiedOrderConfiguration;
import com.grasswort.wxpay.rpc.constants.WxPayConstants;
import com.grasswort.wxpay.rpc.dto.UnifiedOrderRequestBody;
import com.grasswort.wxpay.rpc.dto.UnifiedOrderResponseBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xuliangliang
 * @Classname IUnifiedOrderService.java
 * @Description 微信支付统一下单服务
 * @Date 2020/4/5
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.UNIFIED_ORDER,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = UnifiedOrderConfiguration.class)
public interface IUnifiedOrderService {

    /**
     * 微信支付统一下单
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.UNIFIED_ORDER_PATH)
    UnifiedOrderResponseBody unifiedOrder(UnifiedOrderRequestBody requestBody);

}
