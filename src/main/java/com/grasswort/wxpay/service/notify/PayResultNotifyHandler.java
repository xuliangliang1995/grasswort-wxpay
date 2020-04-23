package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.PayNotifyHandleResult;
import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.dto.PayNotifyResponseBody;
import com.grasswort.wxpay.service.plugins.PluginXmlSignatureVerify;
import com.grasswort.wxpay.util.JAXBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xuliangliang
 * @Classname PayResultNotifyHandler.java
 * @Description 支付结果通知处理
 * @Date 2020/4/10
 * @blame Java Team
 */
@Component
@Slf4j
public class PayResultNotifyHandler {

    private final PluginXmlSignatureVerify pluginXmlSignatureVerify;

    public PayResultNotifyHandler(PluginXmlSignatureVerify pluginXmlSignatureVerify) {
        this.pluginXmlSignatureVerify = pluginXmlSignatureVerify;
    }

    /**
     * 处理支付通知
     *
     * @param xml
     * @return
     */
    public PayNotifyHandleResult handlePayNotify(String xml) {
        // 1. 签名校验
        boolean signVerifySuccess = pluginXmlSignatureVerify.signatureVerify(xml);
        // 2. 请求体解析
        PayNotifyRequestBody notifyBody = JAXBUtil.unmarshal(xml, PayNotifyRequestBody.class);

        // 3. 构建响应体
        PayNotifyResponseBody resBody = signVerifySuccess ? SUCCESS_RES : SIGN_CHECK_FAIL;

        return new PayNotifyHandleResult(notifyBody, resBody);
    }


    private final PayNotifyResponseBody SUCCESS_RES = new PayNotifyResponseBody(WxPayConstants.SUCCESS, "OK");
    private final PayNotifyResponseBody SIGN_CHECK_FAIL = new PayNotifyResponseBody(WxPayConstants.FAIL, "签名校验失败");


}
