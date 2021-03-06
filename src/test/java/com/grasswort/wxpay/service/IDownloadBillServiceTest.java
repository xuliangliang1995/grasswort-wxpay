package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.dto.DownloadBillRequestBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author xuliangliang
 * @Classname IDownloadBillServiceTest.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
public class IDownloadBillServiceTest extends BaseSpringTest {

    @Autowired
    private IDownloadBillService downloadBillService;

    private DownloadBillRequestBody requestBody;

    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat("yyyyMMdd");
        requestBody = DownloadBillRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .billDate(sdf.format(new Date()))
                .billType("ALL")
                .nonceStr(RandomStringUtils.randomAlphabetic(32))
                .build();
    }

    @After
    public void tearDown() throws Exception {
        sdf = null;
        requestBody = null;
    }

    @Test
    @Ignore
    public void downloadBill() {
        assertNotNull(downloadBillService.downloadBill(requestBody));
    }
}