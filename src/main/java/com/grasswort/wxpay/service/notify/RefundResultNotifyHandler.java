package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.service.dto.RefundNotifyHandleResult;
import com.grasswort.wxpay.service.dto.RefundNotifyRequestBody;
import com.grasswort.wxpay.service.dto.RefundNotifyResponseBody;
import com.grasswort.wxpay.service.plugins.PluginReqInfoDecoder;
import com.grasswort.wxpay.util.JAXBUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMCDATA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xuliangliang
 * @Classname RefundResultNotifyHandler.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Component
@Slf4j
public class RefundResultNotifyHandler {

    private final PluginReqInfoDecoder reqInfoDecoder;

    public RefundResultNotifyHandler(PluginReqInfoDecoder reqInfoDecoder) {
        this.reqInfoDecoder = reqInfoDecoder;
    }

    public RefundNotifyHandleResult handleRefundNotify(String xml) {
        RefundNotifyHandleResult result = new RefundNotifyHandleResult();
        // 1. 判断是否通信成功
        Document document = xml2Document(xml);
        List<Element> elementList = document.getRootElement().elements();
        Map<String, String> params = elementList.stream().collect(Collectors.toMap(Element::getName, Element::getStringValue));
        boolean communicateSuccess = WxPayConstants.SUCCESS.equals(params.get(RETURN_CODE));

        if (communicateSuccess) {
            try {
                // 2. 解析 req_info
                Document reqInfoDoc = reqInfoDecoder.decodeReqInfo2XmlDocument(params.get(REQ_INFO_KEY));

                // 用解析后的 xml(去除根节点) 替换掉原本的加密字符串
                Element reqInfoE = document.addElement(REQ_INFO_KEY);
                List<Element> reqInfoEs = reqInfoDoc.getRootElement().elements();
                reqInfoEs.stream().forEach(e -> reqInfoE.addElement(e.getName()).add(new DOMCDATA(e.getStringValue())));

                result.setResBody(SUCCESS_RES_BODY);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResBody(new RefundNotifyResponseBody(WxPayConstants.FAIL, "req_info 解析失败"));
            }
        } else {
            result.setResBody(new RefundNotifyResponseBody(WxPayConstants.FAIL, "通信失败"));
        }

        // 3. json 转 xml 后，再次进行解析
        RefundNotifyRequestBody requestBody = JAXBUtil.unmarshal(document.asXML(), RefundNotifyRequestBody.class);
        result.setNotifyBody(requestBody);
        return result;
    }

    private final String RETURN_CODE = "return_code";
    private final String REQ_INFO_KEY = "req_info";
    private final RefundNotifyResponseBody SUCCESS_RES_BODY = new RefundNotifyResponseBody(WxPayConstants.SUCCESS, "OK");


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
