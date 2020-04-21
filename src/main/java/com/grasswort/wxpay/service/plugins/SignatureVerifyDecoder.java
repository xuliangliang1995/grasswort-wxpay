package com.grasswort.wxpay.service.plugins;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.exception.WxPayApiV2SignatureException;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.XStreamUtil;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xuliangliang
 * @Classname SignatureVerifyDecoder.java
 * @Description 签名校验解码器
 * @Date 2020/4/10
 * @blame Java Team
 */
@Slf4j
public class SignatureVerifyDecoder implements Decoder {

    @Autowired private ISignatureUtil signatureUtil;

    @Autowired private WxMchProperties mchProperties;

    private final String UTF8 = "UTF-8";
    private final String SIGN_KEY = "sign";
    private final String RETURN_CODE = "return_code";

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return Util.emptyValueOf(type);
        } else if (response.body() == null) {
            return null;
        } else if (! (type instanceof Class)) {
            throw new UnsupportedOperationException(type.getTypeName());
        } else {
            // 1. 获取响应体
            String xml = Util.toString(response.body().asReader(Charset.forName(UTF8)));
            log.debug("微信响应消息体：{}", xml);

            // 2. 判断 return_code 是否是 SUCCESS
            Document document = xml2Document(xml);
            List<Element> elementList = document.getRootElement().elements();
            Map<String, String> params = elementList.stream().collect(Collectors.toMap(Element::getName, Element::getStringValue));

            if (WxPayConstants.SUCCESS.equals(params.get(RETURN_CODE))) {
                // 3。如果 SUCCESS，进行签名校验
                String signature = signatureUtil.signature(params, mchProperties.getKey());
                if (! Objects.equals(signature, params.get(SIGN_KEY))) {
                    throw new WxPayApiV2SignatureException("微信响应消息签名校验失败");
                }
            }

            // 4. xml 转 java 对象
            return XStreamUtil.fromXml(xml, (Class) type);
        }
    }

    /**
     * xml 转 document
     * @param xml
     * @return
     */
    private Document xml2Document(String xml) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
            log.debug("非法 xml：{}", xml);
            throw new WxPayApiV2SignatureException("非法 xml :" + xml);
        }
        return document;
    }


}
