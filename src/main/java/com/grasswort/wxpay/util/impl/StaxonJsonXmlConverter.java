package com.grasswort.wxpay.util.impl;

import com.grasswort.wxpay.util.IJsonXmlConverter;
import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author xuliangliang
 * @Classname StaxonJsonXmlConverter.java
 * @Description
 * @Date 2020/4/7
 * @blame Java Team
 */
public enum StaxonJsonXmlConverter implements IJsonXmlConverter {
    /**
     * 枚举实现单例
     */
    INSTANCE;

    /**
     * json 转 xml
     * @param json
     * @return
     */
    @Override
    public String json2xml(String json) {
        StringReader input = new StringReader(json);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false)
                .repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config)
                    .createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance()
                    .createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    /**
     * xml 转 json
     * @param xml
     * @return
     */
    @Override
    public String xml2json(String xml) {
        StringReader input = new StringReader(xml);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true)
                .autoPrimitive(true).prettyPrint(true).build();
        try {
            XMLEventReader reader = XMLInputFactory.newInstance()
                    .createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config)
                    .createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }
}
