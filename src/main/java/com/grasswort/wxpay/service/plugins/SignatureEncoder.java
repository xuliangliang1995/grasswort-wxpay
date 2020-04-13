package com.grasswort.wxpay.service.plugins;

import com.alibaba.fastjson.JSONObject;
import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.XStreamUtil;
import com.grasswort.wxpay.util.impl.StaxonJsonXmlConverter;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * @author xuliangliang
 * @Classname SignatureEncoder.java
 * @Description 支持签名的编码器
 * @Date 2020/4/7
 * @blame Java Team
 */
@Slf4j
@Setter
public class SignatureEncoder implements Encoder {

    private ISignatureUtil signatureUtil;

    private WxMchProperties mchProperties;

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        // 1. 对象转 xml
        String xml = XStreamUtil.toXml(o);

        // 2. xml 转 map (JSONObject 也是 map)
        String json = StaxonJsonXmlConverter.INSTANCE.xml2json(xml);
        JSONObject jsonObj = JSONObject.parseObject(json);
        JSONObject params = jsonObj.getJSONObject(WxPayConstants.XML_ROOT_NODE_NAME);
        params.put(SIGN_TYPE, mchProperties.getSignType());
        params.put(MCH_ID, mchProperties.getMchId());

        // 3. 签名
        String signature = signatureUtil.signature(params, mchProperties.getKey());
        params.put(SIGN_KEY, signature);

        // 4. json 再转回 xml
        String signatureXml = StaxonJsonXmlConverter.INSTANCE.json2xml(jsonObj.toJSONString());
        log.debug("签名后结果：{}", signatureXml);
        requestTemplate.body(signatureXml);
    }

    private final String SIGN_TYPE = "sign_type";
    private final String MCH_ID = "mch_id";
    private final String SIGN_KEY = "sign";
}
