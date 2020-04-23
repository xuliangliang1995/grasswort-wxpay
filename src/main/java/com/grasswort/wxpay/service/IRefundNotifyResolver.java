package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.dto.RefundNotifyRequestBody;
import com.grasswort.wxpay.service.dto.RefundNotifyResponseBody;

/**
 * @author xuliangliang
 * @Classname IRefundNotifyResolver.java
 * @Description 退款通知解析
 * @Date 2020/4/23
 * @blame Java Team
 */
public interface IRefundNotifyResolver {
    /**
     * 解析退款通知结果内容
     * @param xml
     * @return
     */
    RefundNotifyRequestBody resolveRefundNotify(String xml);

    /**
     * 处理成功响应（响应给微信支付服务器的消息体，xml）
     * @return
     */
    default String successRes() {
        return RefundNotifyResponseBody.successRes();
    };

    /**
     * 处理异常响应（响应给微信支付服务器的消息体，xml）
     * @param errorMsg
     * @return
     */
    default String errorRes(String errorMsg) {
        return RefundNotifyResponseBody.errorRes(errorMsg);
    }
}
