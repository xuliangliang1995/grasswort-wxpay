package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.config.DownloadConfiguration;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.DownloadBillRequestBody;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xuliangliang
 * @Classname IDownloadBillService.java
 * @Description 下载交易账单
 * @Date 2020/4/24
 * @blame Java Team
 */
@FeignClient(name = WxPayConstants.WX_PAY_SERVER_NAME, contextId = WxPayConstants.ContextId.DOWNLOAD_BILL,
        url = WxPayConstants.WX_PAY_SERVICE_URL, configuration = DownloadConfiguration.class)
public interface IDownloadBillService {

    /**
     * 下载交易账单
     * @param requestBody
     * @return
     */
    @Headers(WxPayConstants.CONTENT_TYPE_APPLICATION_XML)
    @PostMapping(WxPayConstants.Path.DOWNLOAD_BILL)
    String downloadBill(@RequestBody DownloadBillRequestBody requestBody);
}
