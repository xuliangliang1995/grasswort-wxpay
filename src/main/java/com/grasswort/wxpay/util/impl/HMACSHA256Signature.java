package com.grasswort.wxpay.util.impl;

import com.grasswort.wxpay.exception.WxPayApiV2SignatureException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * @author xuliangliang
 * @Classname HMACSHA256Signature.java
 * @Description HMAC-SHA256 签名工具
 * @Date 2020/4/9
 * @blame Java Team
 */
public class HMACSHA256Signature extends AbstractSignatureUtil {
    /**
     * 算法加密，由子类实现
     *
     * @param text
     * @return
     */
    @Override
    protected String arithmeticEncoder(String text) {
        try {
            return this.sha256HMACEncode(text);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new WxPayApiV2SignatureException("执行 HMAC-SHA256 签名算法时出现异常");
    }

    private String sha256HMACEncode(String str) throws NoSuchAlgorithmException, InvalidKeyException {
        final String KEY_MARKER = "&key=", Arithmetic = "HmacSHA256";
        String key = Optional.ofNullable(str)
                .filter(s -> null != s && s.length() > 0 && s.contains(KEY_MARKER))
                .map(s -> s.substring(s.indexOf(KEY_MARKER) + KEY_MARKER.length()))
                .orElseThrow(() -> new WxPayApiV2SignatureException("执行 HMAC-SHA256 签名时字符串中未检测到 key"));

        Mac sha256HMAC = Mac.getInstance(Arithmetic);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), Arithmetic);
        sha256HMAC.init(secretKeySpec);
        return Hex.encodeHexString(sha256HMAC.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }


}
