package com.grasswort.wxpay.util;

import java.util.Map;

/**
 * @author xuliangliang
 * @Classname ISignatureUtil.java
 * @Description 签名工具
 * @Date 2020/4/9
 * @blame Java Team
 */
public interface ISignatureUtil {
    /**
     * 签名
     * @param params
     * @param key
     * @return
     */
    String signature(Map<String, String> params, String key);

    /**
     * 获取签名类型
     * @return
     */
    String signType();
}
