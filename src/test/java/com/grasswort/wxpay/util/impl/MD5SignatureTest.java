package com.grasswort.wxpay.util.impl;

import com.grasswort.wxpay.util.ISignatureUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MD5SignatureTest {

    private Map<String, String> params;
    private ISignatureUtil signatureUtil;
    private String key;
    private String signature;

    @Before
    public void setUp() throws Exception {
        // 签名工具
        signatureUtil = new MD5Signature();
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
        signature = "9A0A8659F005D6984697E2CA0A9CF3B7";
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
        assertEquals("MD5 签名不正确", signature, signatureUtil.signature(params, key));
    }
}