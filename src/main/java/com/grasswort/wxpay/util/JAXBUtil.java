package com.grasswort.wxpay.util;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuliangliang
 * @Classname JAXBUtil.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
public class JAXBUtil {

    private final static Map<Class, JAXBContext> CONTEXT_SET = new ConcurrentHashMap<>(32);

    /**
     * java 对象转 xml
     * @param object
     * @return
     */
    public static String marshal(Object object) {
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBUtil.createMarshaller(object.getClass()).marshal(object, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new MarshallerException(e.getMessage());
        }
    }

    /**
     * xml 转 java 对象
     * @param xml
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T unmarshal(String xml, Class<T> clazz) {
        try {
            return (T) JAXBUtil.createUnmarshaller(clazz).unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new UnmarshallerException(e.getMessage());
        }
    }

    /**
     * Reader 转 java 对象
     * @param reader
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T unmarshal(Reader reader, Class<T> clazz) {
        return JAXBUtil.unmarshal(new InputSource(reader), clazz);
    }

    /**
     * InputStream 转 java 对象
     * @param inputStream
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T unmarshal(InputStream inputStream, Class<T> clazz) {
        return JAXBUtil.unmarshal(new InputSource(inputStream), clazz);
    }
    /**
     * reader 转 java 对象
     * @param inputSource
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> T unmarshal(InputSource inputSource, Class<T> clazz) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            Source source = new SAXSource(saxParserFactory.newSAXParser().getXMLReader(), inputSource);
            return (T) JAXBUtil.createUnmarshaller(clazz).unmarshal(source);
        } catch (SAXNotRecognizedException e) {
            e.printStackTrace();
            throw new MarshallerException(e.getMessage());
        } catch (SAXNotSupportedException e) {
            e.printStackTrace();
            throw new MarshallerException(e.getMessage());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new MarshallerException(e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
            throw new MarshallerException(e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new MarshallerException(e.getMessage());
        }
    }

    private static Marshaller createMarshaller(Class clazz) throws JAXBException {
        return JAXBUtil.getJAXBContext(clazz).createMarshaller();
    }

    private static Unmarshaller createUnmarshaller(Class clazz) throws JAXBException {
        return JAXBUtil.getJAXBContext(clazz).createUnmarshaller();
    }

    private static JAXBContext getJAXBContext(Class clazz) throws JAXBException {
        if (CONTEXT_SET.containsKey(clazz)) {
            return CONTEXT_SET.get(clazz);
        }
        CONTEXT_SET.put(clazz, JAXBContext.newInstance(clazz));
        return CONTEXT_SET.get(clazz);
    }

    public static class MarshallerException extends RuntimeException{
        public MarshallerException(String message) {
            super(message);
        }
    }
    public static class UnmarshallerException extends RuntimeException {
        public UnmarshallerException(String message) {
            super(message);
        }
    }

}
