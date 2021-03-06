package com.grasswort.wxpay.service.config;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.service.plugins.SignatureVerifyDecoder;
import com.grasswort.wxpay.service.plugins.SignatureEncoder;
import com.grasswort.wxpay.util.ISignatureUtil;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;

/**
 * @author xuliangliang
 * @Classname UnifiedOrderConfiguration.java
 * @Description
 * @Date 2020/4/5
 * @blame Java Team
 */
public class SignatureConfiguration {

    @Bean
    public Encoder encoder(ISignatureUtil signatureUtil, WxMchProperties mchProperties) {
        return new SignatureEncoder(signatureUtil, mchProperties);
    }

    @Bean
    public Decoder decoder() {
        return new SignatureVerifyDecoder();
    }
}
