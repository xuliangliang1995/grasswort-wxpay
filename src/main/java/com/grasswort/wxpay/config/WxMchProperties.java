package com.grasswort.wxpay.config;

import com.grasswort.wxpay.rpc.constants.SignatureArithmeticEnum;
import com.grasswort.wxpay.exception.UnSupportSignatureArithmeticException;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.impl.HMACSHA256Signature;
import com.grasswort.wxpay.util.impl.MD5Signature;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

/**
 * @author xuliangliang
 * @Classname WxMchProperties.java
 * @Description 微信商户配置属性
 * @Date 2020/4/9
 * @blame Java Team
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mch")
@PropertySource(value = "classpath:mch.properties", encoding = "UTF-8")
public class WxMchProperties {
    /**
     * 商户 id
     */
    @Value("${mch.mchId}")
    private String mchId;
    /**
     * 商户 API 密钥
     */
    private String key;
    /**
     * 签名算法
     */
    private String signType;
    /**
     * 证书路径
     */
    private String certPath;
    /**
     * 证书密码（默认是 mch_id）
     */
    private String certPwd;

    /**
     * 签名工具
     * @return
     */
    @Bean
    public ISignatureUtil signatureUtil() {
        SignatureArithmeticEnum signatureArithmeticEnum = Arrays.stream(SignatureArithmeticEnum.values())
                .filter(arithmetic -> arithmetic.getValue().equals(signType))
                .findFirst().orElseThrow(() -> new UnSupportSignatureArithmeticException(signType));

        switch (signatureArithmeticEnum) {
            case HMAC_SHA256:
                return new HMACSHA256Signature();
            default:
                return new MD5Signature();
        }
    }
}
