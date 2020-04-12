package com.grasswort.wxpay.service.notify;

import com.alibaba.fastjson.JSONObject;
import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.exception.WxPayApiV2SignatureException;
import com.grasswort.wxpay.service.INotifyHandler;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.PayNotifyHandleResult;
import com.grasswort.wxpay.service.dto.PayNotifyResBody;
import com.grasswort.wxpay.service.dto.PayResultNotifyBody;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.XStreamUtil;
import com.grasswort.wxpay.util.impl.StaxonJsonXmlConverter;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xuliangliang
 * @Classname PayResultNotifyHandler.java
 * @Description 支付结果通知处理
 * @Date 2020/4/10
 * @blame Java Team
 */
@Service
public class PayResultNotifyHandler implements INotifyHandler {
    /**
     * 签名工具
     */
    private ISignatureUtil signatureUtil;
    /**
     * 微信商户配置
     */
    private WxMchProperties mchProperties;

    /**
     * 处理支付通知
     *
     * @param xml
     * @return
     */
    @Override
    public PayNotifyHandleResult handlePayNotify(String xml) {
        // 1. 签名校验
        boolean signVerifySuccess = signatureVerify(xml);
        // 2. 请求体解析
        PayResultNotifyBody notifyBody = XStreamUtil.fromXml(xml, PayResultNotifyBody.class);
        // 3. 构建响应体
        PayNotifyResBody resBody = signVerifySuccess ? SUCCESS_RES : SIGN_CHECK_FAIL;

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
    private final PayNotifyResBody SUCCESS_RES = new PayNotifyResBody(WxPayConstants.SUCCESS, "OK");
    private final PayNotifyResBody SIGN_CHECK_FAIL = new PayNotifyResBody(WxPayConstants.FAIL, "签名校验失败");
}
