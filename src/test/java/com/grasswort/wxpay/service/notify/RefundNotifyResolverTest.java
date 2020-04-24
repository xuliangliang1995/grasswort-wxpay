package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.service.dto.RefundNotifyRequestBody;
import com.grasswort.wxpay.service.plugins.PluginReqInfoDecoder;
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
 * @Classname RefundNotifyResolverTest.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
@RunWith(MockitoJUnitRunner.class)
public class RefundNotifyResolverTest {

    @InjectMocks private RefundNotifyResolver notifyResolver;

    @Mock private PluginReqInfoDecoder reqInfoDecoder;

    private String notifyXml;

    private String decodedReqInfoXml;

    @Before
    public void setUp() throws Exception {
        notifyXml = TextReader.readText(new ClassPathResource("xml\\refund_success_notify.xml"));
        decodedReqInfoXml = TextReader.readText(new ClassPathResource("xml\\refund_success_notify_req_info.xml"));
        when(reqInfoDecoder.decodeReqInfo2XmlDocument(anyString())).thenReturn(decodedReqInfoXml);
    }

    @After
    public void tearDown() throws Exception {
        notifyXml = null;
        decodedReqInfoXml = null;
        reqInfoDecoder = null;
    }

    @Test
    public void resolveRefundNotify() {
        RefundNotifyRequestBody requestBody = notifyResolver.resolveRefundNotify(notifyXml);
        assertNotNull(requestBody);
        assertEquals("SUCCESS", requestBody.getReturnCode());
        assertNotNull(requestBody.getDecodedReqInfo());
        assertEquals("50000408942018111907145868882", requestBody.getDecodedReqInfo().getRefundId());
        System.out.println(requestBody);
    }
}