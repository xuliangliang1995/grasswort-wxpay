package com.grasswort.wxpay.util.impl;

import com.grasswort.wxpay.util.ISignatureUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HmacSHA256SignatureTest {

    private Map<String, String> params;
    private ISignatureUtil signatureUtil;
    private String key;
    private String signature;

    @Before
    public void setUp() throws Exception {
        // 签名工具
        signatureUtil = new HmacSHA256Signature();
        // 微信官方文档示例
        params = new HashMap<>();
        params.put("appid", "wxd930ea5d5a258f4f");
        params.put("mch_id", "10000100");
        params.put("device_info", "1000");
        params.put("body", "test");
        params.put("nonce_str", "ibuaiVcKdpRxkhJA");
        // API 密钥
        key = "192006250b4c09247ec02edce69f6a2d";
        // 签名结果
        signature = "6A9AE1657590FD6257D693A078E1C3E4BB6BA4DC30B23E0EE2496E54170DACD6";
    }

    @After
    public void tearDown() throws Exception {
        params = null;
        signatureUtil = null;
        key = null;
        signature = null;
    }

    @Test
    public void arithmeticEncoder() {
        assertEquals("HMAC-SHA256 签名不正确", signature, signatureUtil.signature(params, key));
    }
}