package com.grasswort.wxpay.service.plugins;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.Xml2DocUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xuliangliang
 * @Classname PluginXmlSignatureVerify.java
 * @Description Xml 签名校验
 * @Date 2020/4/23
 * @blame Java Team
 */
@Component
@Slf4j
public class PluginXmlSignatureVerify {

    private final String SIGN_KEY = "sign";

    private ISignatureUtil signatureUtil;

    private WxMchProperties mchProperties;

    public PluginXmlSignatureVerify(ISignatureUtil signatureUtil, WxMchProperties mchProperties) {
        this.signatureUtil = signatureUtil;
        this.mchProperties = mchProperties;
    }

    /**
     * 签名校验
     * @param xml
     * @return
     */
    public boolean signatureVerify(String xml) {
        Document document = Xml2DocUtil.xml2Document(xml);
        List<Element> elementList = document.getRootElement().elements();
        Map<String, String> params = elementList.stream().collect(Collectors.toMap(Element::getName, Element::getStringValue));
        String signature = signatureUtil.signature(params, mchProperties.getKey());
        return Objects.equals(params.get(SIGN_KEY), signature);
    }


}
