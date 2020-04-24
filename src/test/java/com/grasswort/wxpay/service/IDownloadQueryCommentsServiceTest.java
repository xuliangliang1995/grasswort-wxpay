package com.grasswort.wxpay.service;

import com.grasswort.wxpay.BaseSpringTest;
import com.grasswort.wxpay.SandBoxConstants;
import com.grasswort.wxpay.service.dto.BatchQueryCommentRequestBody;
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
 * @Classname IDownloadQueryCommentsServiceTest.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
public class IDownloadQueryCommentsServiceTest extends BaseSpringTest {
    @Autowired
    private IDownloadQueryCommentsService downloadQueryCommentsService;

    private BatchQueryCommentRequestBody requestBody;

    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        requestBody = BatchQueryCommentRequestBody.builder()
                .appid(SandBoxConstants.APP_ID)
                .nonceStr(RandomStringUtils.randomAlphabetic(32))
                .offset(1)
                .limit(100)
                .beginTime(sdf.format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)))
                .endTime(sdf.format(new Date()))
                .build();
    }

    @After
    public void tearDown() throws Exception {
        sdf = null;
        requestBody = null;
    }

    @Test
    @Ignore
    public void downloadQueryComments() {
        assertNotNull(downloadQueryCommentsService);
        assertNotNull(downloadQueryCommentsService.downloadQueryComments(requestBody));
    }
}