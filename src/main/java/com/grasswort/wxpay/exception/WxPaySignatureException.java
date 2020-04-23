package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname WxPaySignatureException.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
public class WxPaySignatureException extends RuntimeException {

    public WxPaySignatureException(String message) {
        super(message);
    }

    public WxPaySignatureException() {
        super("签名异常");
    }
}
