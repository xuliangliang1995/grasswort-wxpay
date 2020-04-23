package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname WxPayCertException.java
 * @Description
 * @Date 2020/4/10
 * @blame Java Team
 */
public class WxPayCertException extends RuntimeException {
    public WxPayCertException(String message) {
        super(message);
    }

    public WxPayCertException() {
    }
}
