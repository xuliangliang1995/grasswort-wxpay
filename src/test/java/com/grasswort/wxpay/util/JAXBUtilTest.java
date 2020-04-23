package com.grasswort.wxpay.util;

import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertEquals;

/**
 * @author xuliangliang
 * @Classname JAXBUtilTest.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
public class JAXBUtilTest {

    private String xml;

    @Before
    public void setUp() throws Exception {
        xml = TextReader.readText(new ClassPathResource("xml/pay_success_notify.xml"));
    }

    @After
    public void tearDown() throws Exception {
        xml = null;
    }

    @Test
    public void marshal() {
        PayNotifyRequestBody requestBody = JAXBUtil.unmarshal(xml, PayNotifyRequestBody.class);
        assertEquals("SUCCESS", requestBody.getReturnCode());
        assertEquals("wx2421b1c4370ec43b", requestBody.getAppid());
        assertEquals("CFT", requestBody.getBankType());
        assertEquals("10000100", requestBody.getMchId());
    }

    @Test
    public void unmarshal() {
    }
}