package com.grasswort.wxpay.service.constants;

/**
 * @author xuliangliang
 * @Classname SignatureArithmeticEnum.java
 * @Description 签名算法
 * @Date 2020/4/10
 * @blame Java Team
 */
public enum SignatureArithmeticEnum {
    MD5("MD5"), HmacSHA256("HMAC-SHA256");

    SignatureArithmeticEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
