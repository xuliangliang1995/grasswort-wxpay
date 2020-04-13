package com.grasswort.wxpay.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuliangliang
 * @Classname RefundNotifyHandleResult.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundNotifyHandleResult {
    /**
     * 微信支付退款结果通知消息体
     */
    private RefundNotifyRequestBody notifyBody;
    /**
     * 响应体
     */
    private RefundNotifyResponseBody resBody;
}
