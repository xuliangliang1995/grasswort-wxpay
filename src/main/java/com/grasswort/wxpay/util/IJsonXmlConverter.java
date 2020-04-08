package com.grasswort.wxpay.util;

/**
 * @author xuliangliang
 * @Classname IJsonXmlConverter.java
 * @Description
 * @Date 2020/4/7
 * @blame Java Team
 */
public interface IJsonXmlConverter {
    /**
     * json 转 xml
     * @param json
     * @return
     */
    String json2xml(String json);

    /**
     * xml 转 json
     * @param xml
     * @return
     */
    String xml2json(String xml);
}
