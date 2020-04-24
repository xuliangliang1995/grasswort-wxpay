package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.SSLContextSupportDownloadConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.DownloadFundFlowRequestBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xuliangliang
 * @Classname IDownloadFundFlowService.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.DOWNLOAD_FUND_FLOW,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = SSLContextSupportDownloadConfiguration.class)
public interface IDownloadFundFlowService {
    /**
     * 下载资金账单
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.DOWNLOAD_FUND_FLOW)
    String downloadFundFlow(@RequestBody DownloadFundFlowRequestBody requestBody);
}
