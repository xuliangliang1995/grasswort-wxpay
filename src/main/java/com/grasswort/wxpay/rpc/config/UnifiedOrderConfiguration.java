package com.grasswort.wxpay.rpc.config;

import com.grasswort.wxpay.rpc.plugins.SignatureVerifyDecoder;
import com.grasswort.wxpay.rpc.plugins.SignatureEncoder;
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
public class UnifiedOrderConfiguration {

    @Bean
    public Encoder encoder() {
        return new SignatureEncoder();
    }

    @Bean
    public Decoder decoder() {
        return new SignatureVerifyDecoder();
    }
}
