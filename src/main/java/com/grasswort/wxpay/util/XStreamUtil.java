package com.grasswort.wxpay.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;

import java.io.Writer;

/**
 * @author xuliangliang
 * @Classname XStreamUtil.java
 * @Description
 * @Date 2020/4/9
 * @blame Java Team
 */
public class XStreamUtil {
    /**
     * 对象转 xml
     * @param object
     * @return
     */
    public static final String toXml(Object object) {
        return XStreamSingletonHolder.XSTREAM.toXML(object);
    }

    /**
     * xml 转对象
     * @param xml
     * @param clazz
     * @return
     */
    public static final <T> T fromXml(String xml, Class<T> clazz) {
        try {
            return (T) XStreamSingletonHolder.XSTREAM.fromXML(xml, clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final class XStreamSingletonHolder {
        static final XStream XSTREAM;
        static {
            final NameCoder nameCoder = new XmlFriendlyNameCoder("_-", "_");
            XSTREAM = new XStream(new Xpp3DomDriver() {
                @Override
                public HierarchicalStreamWriter createWriter(Writer out) {
                    final String CDATA_PREFIX = "<![CDATA[", CDATA_SUFFIX = "]]>";
                    return new PrettyPrintWriter(out, nameCoder) {
                        @Override
                        protected void writeText(QuickWriter writer, String text) {
                            if (text.startsWith(CDATA_PREFIX) && text.endsWith(CDATA_SUFFIX)) {
                                writer.write(text);
                            } else {
                                writer.write(CDATA_PREFIX + text + CDATA_SUFFIX);
                            }
                        }

                    };
                }
            });
            XSTREAM.autodetectAnnotations(true);
        }

    }
}
