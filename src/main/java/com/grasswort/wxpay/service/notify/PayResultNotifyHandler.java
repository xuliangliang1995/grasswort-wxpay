package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.PayNotifyHandleResult;
import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.dto.PayNotifyResponseBody;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.XStreamUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Document document = xml2Document(xml);
        List<Element> elementList = document.getRootElement().elements();
        Map<String, String> params = elementList.stream().collect(Collectors.toMap(Element::getName, Element::getStringValue));
        String signature = signatureUtil.signature(params, mchProperties.getKey());
        return Objects.equals(params.get(SIGN_KEY), signature);
    }

    private final String SIGN_KEY = "sign";
    private final PayNotifyResponseBody SUCCESS_RES = new PayNotifyResponseBody(WxPayConstants.SUCCESS, "OK");
    private final PayNotifyResponseBody SIGN_CHECK_FAIL = new PayNotifyResponseBody(WxPayConstants.FAIL, "签名校验失败");

    /**
     * xml 转 document
     * @param xml
     * @return
     */
    private Document xml2Document(String xml) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
            log.debug("非法 xml：{}", xml);
        }
        return document;
    }
}
