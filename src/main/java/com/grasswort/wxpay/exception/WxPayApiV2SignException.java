package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname WxPayApiV2SignException.java
 * @Description
 * @Date 2020/4/9
 * @blame Java Team
 */
public class WxPayApiV2SignException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WxPayApiV2SignException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public WxPayApiV2SignException() {
        super("签名异常");
    }
}
