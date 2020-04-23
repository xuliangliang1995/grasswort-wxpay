package com.grasswort.wxpay.exception;

/**
 * @author xuliangliang
 * @Classname IllegalXmlException.java
 * @Description 非法 xml 异常
 * @Date 2020/4/23
 * @blame Java Team
 */
public class IllegalXmlException extends RuntimeException {

    public IllegalXmlException() {
        super("invalid xml exception");
    }

    public IllegalXmlException(String xml) {
        super("invalid xml exception ： \n".concat(xml));
    }
}
