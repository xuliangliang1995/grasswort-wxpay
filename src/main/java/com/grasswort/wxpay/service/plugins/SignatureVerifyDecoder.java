package com.grasswort.wxpay.service.plugins;

import com.grasswort.wxpay.exception.WxPaySignatureVerifyException;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.util.JAXBUtil;
import com.grasswort.wxpay.util.Xml2DocUtil;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
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

    @Autowired private PluginXmlSignatureVerify xmlSignatureVerify;

    private final String UTF8 = "UTF-8";
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
            Document document = Xml2DocUtil.xml2Document(xml);
            List<Element> elementList = document.getRootElement().elements();
            Map<String, String> params = elementList.stream().collect(Collectors.toMap(Element::getName, Element::getStringValue));

            if (WxPayConstants.SUCCESS.equals(params.get(RETURN_CODE))) {
                // 3。如果 SUCCESS，进行签名校验
                if (! xmlSignatureVerify.signatureVerify(xml)) {
                    throw new WxPaySignatureVerifyException();
                }
            }

            // 4. xml 转 java 对象
            return JAXBUtil.unmarshal(xml, (Class) type);
        }
    }



}
