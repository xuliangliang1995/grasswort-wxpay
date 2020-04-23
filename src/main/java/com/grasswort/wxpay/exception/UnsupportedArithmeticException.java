package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname UnsupportedArithmeticException.java
 * @Description 不支持的签名算法一场
 * @Date 2020/4/10
 * @blame Java Team
 */
public class UnsupportedArithmeticException extends RuntimeException {

    public UnsupportedArithmeticException(String message) {
        super("不支持的签名算法：" + message);
    }
}
