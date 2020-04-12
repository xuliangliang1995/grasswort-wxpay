package com.grasswort.wxpay.service;

import com.grasswort.wxpay.service.dto.PayNotifyHandleResult;

/**
 * @author xuliangliang
 * @Classname INotifyHandler.java
 * @Description 微信通知处理
 * @Date 2020/4/10
 * @blame Java Team
 */
public interface INotifyHandler {
    /**
     * 处理支付通知
     * @param xml
     * @return
     */
    PayNotifyHandleResult handlePayNotify(String xml);
}
