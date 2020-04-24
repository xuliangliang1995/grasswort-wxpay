package com.grasswort.wxpay.service.plugins;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.JAXBUtil;
import com.grasswort.wxpay.util.Xml2DocUtil;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
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

    public SignatureEncoder(ISignatureUtil signatureUtil, WxMchProperties mchProperties) {
        this.signatureUtil = signatureUtil;
        this.mchProperties = mchProperties;
    }

    private final ISignatureUtil signatureUtil;

    private final WxMchProperties mchProperties;

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        // 1. 对象转 xml
        String xml = JAXBUtil.marshal(o);

        // 2. Document 追加 商户ID 以及 签名算法
        Document document = Xml2DocUtil.xml2Document(xml);
        document.getRootElement().addElement(SIGN_TYPE).add(new DefaultText(signatureUtil.signType()));;
        document.getRootElement().addElement(MCH_ID).add(new DefaultText(mchProperties.getMchId()));

        // 3. 签名
        Map<String, String> params = removeEmptyValueAnd2Map(document);
        String signature = signatureUtil.signature(params, mchProperties.getKey());
        document.getRootElement().addElement(SIGN_KEY).add(new DOMCDATA(signature));

        // 4. document 再转 xml
        String signatureXml = document.asXML();
        log.debug("签名后结果：{}", signatureXml);
        requestTemplate.body(signatureXml);
    }

    private final String SIGN_TYPE = "sign_type";
    private final String MCH_ID = "mch_id";
    private final String SIGN_KEY = "sign";

    /**
     * document 转 map
     * @param document
     * @return
     */
    private Map<String, String> removeEmptyValueAnd2Map(Document document) {
        Element rootElement = document.getRootElement();
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
        return params;
    }


}
