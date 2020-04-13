package com.grasswort.wxpay.service.notify;

import com.alibaba.fastjson.JSONObject;
import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.exception.WxRefundNotifyDecodeException;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.RefundNotifyHandleResult;
import com.grasswort.wxpay.service.dto.RefundNotifyRequestBody;
import com.grasswort.wxpay.service.dto.RefundNotifyResponseBody;
import com.grasswort.wxpay.util.XStreamUtil;
import com.grasswort.wxpay.util.impl.StaxonJsonXmlConverter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Classname RefundResultNotifyHandler.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Component
public class RefundResultNotifyHandler {
    /**
     * 微信商户配置
     */
    @Autowired private WxMchProperties mchProperties;


    public RefundNotifyHandleResult handleRefundNotify(String xml) {
        RefundNotifyHandleResult result = new RefundNotifyHandleResult();
        // 1. 判断是否通信成功
        String json = StaxonJsonXmlConverter.INSTANCE.xml2json(xml);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject params = jsonObject.getJSONObject(WxPayConstants.XML_ROOT_NODE_NAME);
        boolean communicateSuccess = WxPayConstants.SUCCESS.equals(params.getString(RETURN_CODE));

        if (communicateSuccess) {
            try {
                // 2. 解析 req_info
                String reqInfoXml = decodeReqInfo2Xml(params.getString(REQ_INFO_KEY));
                String reqInfoJson = StaxonJsonXmlConverter.INSTANCE.xml2json(reqInfoXml);
                params.put(REQ_INFO_KEY, JSONObject.parseObject(reqInfoJson).getJSONObject(WxPayConstants.XML_ROOT_NODE_NAME));
                result.setResBody(SUCCESS_RES_BODY);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResBody(new RefundNotifyResponseBody(WxPayConstants.FAIL, "req_info 解析失败"));
            }
        } else {
            result.setResBody(new RefundNotifyResponseBody(WxPayConstants.FAIL, "通信失败"));
        }

        // 3. json 转 xml 后，再次进行解析
        String decodeXml = StaxonJsonXmlConverter.INSTANCE.json2xml(jsonObject.toJSONString());
        RefundNotifyRequestBody requestBody = XStreamUtil.fromXml(decodeXml, RefundNotifyRequestBody.class);
        result.setNotifyBody(requestBody);
        return result;
    }

    private final String RETURN_CODE = "return_code";
    private final String REQ_INFO_KEY = "req_info";
    private final RefundNotifyResponseBody SUCCESS_RES_BODY = new RefundNotifyResponseBody(WxPayConstants.SUCCESS, "OK");

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
