package com.grasswort.wxpay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xuliangliang
 * @Classname WxMchProperties.java
 * @Description 微信商户配置属性
 * @Date 2020/4/9
 * @blame Java Team
 */
@Data
@ConfigurationProperties(prefix = "mch")
@Component
@PropertySource("classpath:mch.yml")
public class WxMchProperties {
    /**
     * 商户 id
     */
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
}
