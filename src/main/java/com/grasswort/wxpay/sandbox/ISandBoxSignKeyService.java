package com.grasswort.wxpay.sandbox;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xuliangliang
 * @Classname ISandBoxSignKeyService.java
 * @Description 沙箱环境获取 API 密钥 key
 * @Date 2020/4/5
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.SAND_BOX_SIGN_KEY,
        url = WxPayConstants.ServiceUrl.SANDBOX_SERVICE_URL)
public interface ISandBoxSignKeyService {


    /**
     * 微信支付统一下单
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.SANDBOX_GET_SIGN_KEY)
    String getSandBoxSignKey(String requestBody);

}
