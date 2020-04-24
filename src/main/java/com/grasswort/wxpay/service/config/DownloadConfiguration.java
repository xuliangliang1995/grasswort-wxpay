package com.grasswort.wxpay.service.config;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.service.plugins.SignatureEncoder;
import com.grasswort.wxpay.util.ISignatureUtil;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.support.DefaultGzipDecoder;
import org.springframework.context.annotation.Bean;

/**
 * @author xuliangliang
 * @Classname PrefixSignatureConfiguration.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
public class DownloadConfiguration {

    @Bean
    public Encoder encoder(ISignatureUtil signatureUtil, WxMchProperties mchProperties) {
        return new SignatureEncoder(signatureUtil, mchProperties);
    }

    @Bean
    public Decoder decoder() {
        return new DefaultGzipDecoder(new Decoder.Default());
    }

}
