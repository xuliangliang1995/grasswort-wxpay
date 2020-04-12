package com.grasswort.wxpay.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuliangliang
 * @Classname PayNotifyHandleResult.java
 * @Description
 * @Date 2020/4/12
 * @blame Java Team
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayNotifyHandleResult {
    /**
     * 微信支付结果通知数据
     */
    private PayResultNotifyBody notifyBody;
    /**
     * 响应给微信的数据
     */
    private PayNotifyResBody resBody;

}
