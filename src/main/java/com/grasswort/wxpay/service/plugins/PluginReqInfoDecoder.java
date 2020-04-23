package com.grasswort.wxpay.service.plugins;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.exception.WxRefundNotifyDecodeException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuliangliang
 * @Classname PluginReqInfoDecoder.java
 * @Description 退款结果通知 reqInfo 解析
 * @Date 2020/4/23
 * @blame Java Team
 */
@Component
public class PluginReqInfoDecoder {

    private WxMchProperties mchProperties;

    public PluginReqInfoDecoder(WxMchProperties mchProperties) {
        this.mchProperties = mchProperties;
    }

    /**
     * 微信退款结果 返回字段 req_info
     * @param reqInfo
     * @return
     */
    public Document decodeReqInfo2XmlDocument(String reqInfo) {
        String xml = decodeReqInfo2Xml(reqInfo);
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        throw new WxRefundNotifyDecodeException();
    }


    /**
     * 解析 req_info
     * @param reqInfoString
     * @return
     */
    private String decodeReqInfo2Xml(String reqInfoString) {
        final String keyMd5String = DigestUtils.md5Hex(mchProperties.getKey()).toLowerCase();
        SecretKeySpec key = new SecretKeySpec(keyMd5String.getBytes(StandardCharsets.UTF_8), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.decodeBase64(reqInfoString)));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        throw new WxRefundNotifyDecodeException();
    }
}
