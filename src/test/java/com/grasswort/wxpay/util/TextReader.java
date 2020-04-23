package com.grasswort.wxpay.util;

import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xuliangliang
 * @Classname TextReader.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
public class TextReader {

    /**
     * 读取文本
     * @param resource
     * @return
     */
    public static String readText(Resource resource) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = resource.getInputStream();
            baos = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
