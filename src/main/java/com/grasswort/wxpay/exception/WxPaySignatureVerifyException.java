package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname WxPaySignatureVerifyException.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
public class WxPaySignatureVerifyException extends RuntimeException{

    public WxPaySignatureVerifyException(String message) {
        super(message);
    }

    public WxPaySignatureVerifyException() {
        super("签名校验异常");
    }
}
