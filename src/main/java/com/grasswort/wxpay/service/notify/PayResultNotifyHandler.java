package com.grasswort.wxpay.service.notify;

import com.alibaba.fastjson.JSONObject;
import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.PayNotifyHandleResult;
import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.dto.PayNotifyResponseBody;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.XStreamUtil;
import com.grasswort.wxpay.util.impl.StaxonJsonXmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author xuliangliang
 * @Classname PayResultNotifyHandler.java
 * @Description 支付结果通知处理
 * @Date 2020/4/10
 * @blame Java Team
 */
@Component
public class PayResultNotifyHandler {
    /**
     * 签名工具
     */
    @Autowired private ISignatureUtil signatureUtil;
    /**
     * 微信商户配置
     */
    @Autowired private WxMchProperties mchProperties;

    /**
     * 处理支付通知
     *
     * @param xml
     * @return
     */
    public PayNotifyHandleResult handlePayNotify(String xml) {
        // 1. 签名校验
        boolean signVerifySuccess = signatureVerify(xml);
        // 2. 请求体解析
        PayNotifyRequestBody notifyBody = XStreamUtil.fromXml(xml, PayNotifyRequestBody.class);
        // 3. 构建响应体
        PayNotifyResponseBody resBody = signVerifySuccess ? SUCCESS_RES : SIGN_CHECK_FAIL;

        return new PayNotifyHandleResult(notifyBody, resBody);
    }

    /**
     * 签名校验
     * @param xml
     * @return
     */
    private boolean signatureVerify(String xml) {
        String json = StaxonJsonXmlConverter.INSTANCE.xml2json(xml);
        JSONObject jsonObj = JSONObject.parseObject(json);
        String signature = signatureUtil.signature(jsonObj, mchProperties.getKey());
        return Objects.equals(jsonObj.getString(SIGN_KEY), signature);
    }

    private final String SIGN_KEY = "sign";
    private final PayNotifyResponseBody SUCCESS_RES = new PayNotifyResponseBody(WxPayConstants.SUCCESS, "OK");
    private final PayNotifyResponseBody SIGN_CHECK_FAIL = new PayNotifyResponseBody(WxPayConstants.FAIL, "签名校验失败");
}
