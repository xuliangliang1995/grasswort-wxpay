package com.grasswort.wxpay.util.impl;

import com.grasswort.wxpay.exception.WxPayApiV2SignatureException;
import com.grasswort.wxpay.util.ISignatureUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author xuliangliang
 * @Classname AbstractSignUtil.java
 * @Description 签名工具抽象类
 * @Date 2020/4/9
 * @blame Java Team
 */
public abstract class AbstractSignatureUtil implements ISignatureUtil {
    // 签名排除参数
    private final List<String> EXCLUDE_SIGN_KEYS = Arrays.asList(new String[]{"sign", "paySign"});

    /**
     * 签名
     * @param params
     * @param key
     * @return
     */
    @Override
    public String signature(Map<String, Object> params, String key) {
        // 1. 参数排序
        String sortedParams = sortParamsByASCII(params);
        // 2. 拼接 "&key="
        String sortedParamsWithKey = sortedParams + "&key=" + key;
        // 4. 算法计算
        String result = arithmeticEncoder(sortedParamsWithKey);
        // 5. 转大写
        return result.toUpperCase();
    }

    /**
     * 算法加密，由子类实现
     * @param text
     * @return
     */
    protected abstract String arithmeticEncoder(String text);

    /**
     * 参数 ASCII码 排序
     * 1. 字典序
     * 2. 空字段不参与排序
     * 3. 参数名区分大小写
     * 4. sign 不参与签名
     * 5. 微信响应新增字段参与签名（注意）
     * @param params
     * @return
     */
    private String sortParamsByASCII(Map<String, Object> params) {
        String[] keyArray = params.keySet().toArray(new String[params.keySet().size()]);
        // 字典排序
        Arrays.sort(keyArray);
        String sortedParams = Stream.of(keyArray)
                // 排除 sign
                .filter(key -> ! EXCLUDE_SIGN_KEYS.contains(key))
                .map(key -> {
                    // 拼接 key=value 格式，如果 value 为空，返回空白
                    String value = params.get(key) !=  null ? params.get(key).toString() : null;
                    return (value != null && value.length() > 0)
                            ? (key + "=" + value)
                            : "";
                })
                // 过滤掉空白字符串
                .filter(str -> str != null && str.length() > 0)
                .reduce((a, b) -> a + "&" + b)
                .orElse("");
        if (sortedParams != null && sortedParams.length() > 0) {
            return sortedParams;
        }
        throw new WxPayApiV2SignatureException("签名 ASCII 阶段出现异常，排序结果为空白字符串");
    }
}
