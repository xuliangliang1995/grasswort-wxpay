package com.grasswort.wxpay.util.impl;

import com.grasswort.wxpay.exception.WxPaySignatureException;
import com.grasswort.wxpay.util.ISignatureUtil;
import org.apache.commons.lang3.StringUtils;

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
    /**
     * 签名排除参数
     */
    private final List<String> EXCLUDE_SIGN_KEYS = Arrays.asList("sign", "paySign");

    /**
     * 签名
     * @param params
     * @param key
     * @return
     */
    @Override
    public final String signature(Map<String, String> params, String key) {
        // 1. 字典排序
        String sortedParams = sortParamsByASCII(params);
        // 2. 拼接 "&key="
        String sortedParamsWithKey = sortedParams + "&key=" + key;
        // 3. 算法计算
        String result = arithmeticEncoder(sortedParamsWithKey);
        // 4. 转大写
        return result.toUpperCase();
    }

    /**
     * 签名类型
     * @return
     */
    @Override
    public abstract String signType();

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
    private final String sortParamsByASCII(Map<String, String> params) {
        String[] keyArray = params.keySet().toArray(new String[params.keySet().size()]);
        // 字典排序
        Arrays.sort(keyArray);
        return Stream.of(keyArray)
                // 排除 sign
                .filter(key -> ! EXCLUDE_SIGN_KEYS.contains(key))
                .map(key -> {
                    // 拼接 key=value 格式，如果 value 为空，返回空白
                    String value = params.get(key) !=  null ? params.get(key).toString() : null;
                    return (value != null && value.length() > 0)
                            ? (key + "=" + value)
                            : null;
                })
                // 过滤掉空白字符串
                .filter(str -> str != null)
                .reduce((a, b) -> a + "&" + b)
                .filter(str -> StringUtils.isNotBlank(str))
                .orElseThrow(() -> new WxPaySignatureException("签名算法 ASCII 排序阶段出现异常"));
    }
}
