package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.exception.WxPaySignatureVerifyException;
import com.grasswort.wxpay.service.IPayNotifyResolver;
import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.plugins.PluginXmlSignatureVerify;
import com.grasswort.wxpay.util.JAXBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xuliangliang
 * @Classname PayNotifyResolver.java
 * @Description 支付结果通知解析
 * @Date 2020/4/10
 * @blame Java Team
 */
@Slf4j
@Component
public class PayNotifyResolver implements IPayNotifyResolver {

    private final PluginXmlSignatureVerify pluginXmlSignatureVerify;

    public PayNotifyResolver(PluginXmlSignatureVerify pluginXmlSignatureVerify) {
        this.pluginXmlSignatureVerify = pluginXmlSignatureVerify;
    }

    /**
     * 处理支付通知
     * @param xml
     * @return
     */
    @Override
    public PayNotifyRequestBody resolvePayNotify(String xml) {
        // 1. 签名校验
        boolean signVerifySuccess = pluginXmlSignatureVerify.signatureVerify(xml);

        if (signVerifySuccess) {
            // 2. 请求体解析
            return JAXBUtil.unmarshal(xml, PayNotifyRequestBody.class);
        }

        throw new WxPaySignatureVerifyException();
    }

}
