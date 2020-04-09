package com.grasswort.wxpay.util.impl;

import org.springframework.util.DigestUtils;

/**
 * @author xuliangliang
 * @Classname MD5Signature.java
 * @Description 微信 ApiV2 MD5 签名工具
 * @Date 2020/4/9
 * @blame Java Team
 */
public final class MD5Signature extends AbstractSignatureUtil {
    /**
     * 算法加密，由子类实现
     *
     * @param text
     * @return
     */
    @Override
    protected String arithmeticEncoder(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }
}
