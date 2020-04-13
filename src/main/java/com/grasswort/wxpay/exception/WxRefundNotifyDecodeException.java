package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname WxRefundNotifyDecodeException.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
public class WxRefundNotifyDecodeException extends RuntimeException {

    public WxRefundNotifyDecodeException() {
        super("微信退款通知 req_info 解析异常");
    }
}
