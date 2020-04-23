package com.grasswort.wxpay.service.plugins;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.JAXBUtil;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.tree.DefaultText;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xuliangliang
 * @Classname SignatureEncoder.java
 * @Description 支持签名的编码器
 * @Date 2020/4/7
 * @blame Java Team
 */
@Slf4j
public class SignatureEncoder implements Encoder {

    @Autowired private ISignatureUtil signatureUtil;

    @Autowired private WxMchProperties mchProperties;

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        // 1. 对象转 xml
        String xml = JAXBUtil.marshal(o);

        // 2. xml 转 map (JSONObject 也是 map)
        Document document = xml2Document(xml);
        Element rootElement = document.getRootElement();

        // 3. 追加商户相关参数并转换成 map
        rootElement.addElement(SIGN_TYPE).add(new DefaultText(mchProperties.getSignType()));;
        rootElement.addElement(MCH_ID).add(new DefaultText(mchProperties.getMchId()));
        List<Element> elementList = rootElement.elements();

        Map<String, String> params = new HashMap<>();
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            String key = element.getName();
            String value = element.getStringValue();
            if (StringUtils.isNotBlank(value)) {
                params.put(key, value);
            } else {
                rootElement.remove(element);
            }
        }

        // 4. 签名
        String signature = signatureUtil.signature(params, mchProperties.getKey());
        rootElement.addElement(SIGN_KEY).add(new DOMCDATA(signature));

        // 5. document 再转 xml
        String signatureXml = document.asXML();
        log.debug("签名后结果：{}", signatureXml);
        requestTemplate.body(signatureXml);
    }

    private final String SIGN_TYPE = "sign_type";
    private final String MCH_ID = "mch_id";
    private final String SIGN_KEY = "sign";

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
        }
        return document;
    }

}
