package com.grasswort.wxpay.service.config;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.service.plugins.SignatureEncoder;
import com.grasswort.wxpay.util.impl.HmacSHA256Signature;
import feign.Client;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.openfeign.support.DefaultGzipDecoder;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;

/**
 * @author xuliangliang
 * @Classname SSLContextSupportDownloadConfiguration.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
public class SSLContextSupportDownloadConfiguration {
    /**
     * 下载资金账单和拉取订单评价目前仅支持 HmacSHA256 签名算法
     * @param mchProperties
     * @return
     */
    @Bean
    public Encoder encoder(WxMchProperties mchProperties) {
        return new SignatureEncoder(new HmacSHA256Signature(), mchProperties);
    }

    @Bean
    public Decoder decoder() {
        return new DefaultGzipDecoder(new Decoder.Default());
    }

    @Bean
    @ConditionalOnBean(SSLContext.class)
    public Client client(SSLContext sslContext) {
        return new Client.Default(sslContext.getSocketFactory(), new NoopHostnameVerifier());
    }
}
