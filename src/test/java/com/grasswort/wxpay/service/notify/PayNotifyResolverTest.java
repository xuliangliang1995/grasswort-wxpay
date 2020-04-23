package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.plugins.PluginXmlSignatureVerify;
import com.grasswort.wxpay.util.TextReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname PayNotifyResolverTest.java
 * @Description
 * @Date 2020/4/23
 * @blame Java Team
 */
@RunWith(MockitoJUnitRunner.class)
public class PayNotifyResolverTest extends BaseSpringTest {

    @Mock private PluginXmlSignatureVerify pluginXmlSignatureVerify;

    @InjectMocks private PayNotifyResolver notifyHandler;

    private String notifyXml;


    @Before
    public void setUp() throws Exception {
        // 任何签名校验都通过
        when(pluginXmlSignatureVerify.signatureVerify(anyString())).thenReturn(true);
        notifyXml = TextReader.readText(new ClassPathResource("xml\\pay_success_notify.xml"));
    }

    @After
    public void tearDown() throws Exception {
        notifyXml = null;
    }

    @Test
    public void handlePayNotify() {
        PayNotifyRequestBody requestBody = notifyHandler.resolvePayNotify(notifyXml);
        assertNotNull(requestBody);
        assertEquals("SUCCESS", requestBody.getReturnCode());
        assertEquals("wx2421b1c4370ec43b", requestBody.getAppid());
        assertEquals("CFT", requestBody.getBankType());
        assertEquals("10000100", requestBody.getMchId());
    }
}