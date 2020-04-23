package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.dto.PayNotifyResponseBody;

/**
 * @author xuliangliang
 * @Classname IPayNotifyResolver.java
 * @Description 支付通知结果解析
 * @Date 2020/4/23
 * @blame Java Team
 */
public interface IPayNotifyResolver {
    /**
     * 解析支付结果通知内容
     * @param xml
     * @return
     */
    PayNotifyRequestBody resolvePayNotify(String xml);

    /**
     * 处理成功响应（响应给微信支付服务器的消息体，xml）
     * @return
     */
    default String successRes() {
        return PayNotifyResponseBody.successRes();
    };

    /**
     * 处理异常响应（响应给微信支付服务器的消息体，xml）
     * @param errorMsg
     * @return
     */
    default String errorRes(String errorMsg) {
        return PayNotifyResponseBody.errorRes(errorMsg);
    }
}
