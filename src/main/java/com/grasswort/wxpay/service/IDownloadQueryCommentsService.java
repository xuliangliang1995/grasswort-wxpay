package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.SSLContextSupportDownloadConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.BatchQueryCommentRequestBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xuliangliang
 * @Classname IDownloadQueryCommentsService.java
 * @Description 下载订单评价
 * @Date 2020/4/24
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.BATCH_QUERY_COMMENT,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = SSLContextSupportDownloadConfiguration.class)
public interface IDownloadQueryCommentsService {

    /**
     * 下载订单评价
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.BATCH_QUERY_COMMENT)
    String downloadQueryComments(@RequestBody BatchQueryCommentRequestBody requestBody);
}
