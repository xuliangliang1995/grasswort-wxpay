package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.service.IRefundNotifyResolver;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.RefundNotifyReqInfo;
import com.grasswort.wxpay.service.dto.RefundNotifyRequestBody;
import com.grasswort.wxpay.service.plugins.PluginReqInfoDecoder;
import com.grasswort.wxpay.util.JAXBUtil;
import com.grasswort.wxpay.util.Xml2DocUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xuliangliang
 * @Classname RefundNotifyResolver.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Slf4j
@Component
public class RefundNotifyResolver implements IRefundNotifyResolver {

    private final PluginReqInfoDecoder reqInfoDecoder;

    public RefundNotifyResolver(PluginReqInfoDecoder reqInfoDecoder) {
        this.reqInfoDecoder = reqInfoDecoder;
    }

    @Override
    public RefundNotifyRequestBody resolveRefundNotify(String xml) {
        // 1. 判断是否通信成功
        Document document = Xml2DocUtil.xml2Document(xml);
        List<Element> elementList = document.getRootElement().elements();
        Map<String, String> params = elementList.stream().collect(Collectors.toMap(Element::getName, Element::getStringValue, (v1, v2) -> v2));
        boolean communicateSuccess = WxPayConstants.SUCCESS.equals(params.get(RETURN_CODE));

        RefundNotifyRequestBody requestBody = JAXBUtil.unmarshal(document.asXML(), RefundNotifyRequestBody.class);
        if (communicateSuccess) {
            try {
                // 2. 解析 req_info
                String reqInfo = reqInfoDecoder.decodeReqInfo2XmlDocument(params.get(REQ_INFO_KEY));
                RefundNotifyReqInfo decodedReqInfo = JAXBUtil.unmarshal(reqInfo, RefundNotifyReqInfo.class);
                requestBody.setDecodedReqInfo(decodedReqInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 3. json 转 xml 后，再次进行解析
        return requestBody;
    }

    private final String RETURN_CODE = "return_code";
    private final String REQ_INFO_KEY = "req_info";

}
