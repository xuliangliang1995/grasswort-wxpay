package com.grasswort.wxpay.util;

import com.grasswort.wxpay.exception.IllegalXmlException;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * @author xuliangliang
 * @Classname Xml2DocUtil.java
 * @Description xml 2 Document
 * @Date 2020/4/23
 * @blame Java Team
 */
@Slf4j
public class Xml2DocUtil {
    /**
     * xml 字符串转 Document
     * @param xml
     * @return
     */
    public static Document xml2Document(String xml) {
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IllegalXmlException(xml);
        }
    }


}
