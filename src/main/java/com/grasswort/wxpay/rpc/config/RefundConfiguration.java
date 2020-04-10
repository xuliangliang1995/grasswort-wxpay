package com.grasswort.wxpay.rpc.config;

import com.grasswort.wxpay.rpc.plugins.SignatureEncoder;
import com.grasswort.wxpay.rpc.plugins.SignatureVerifyDecoder;
import feign.Client;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;

/**
 * @author xuliangliang
 * @Classname RefundConfiguration.java
 * @Description
 * @Date 2020/4/10
 * @blame Java Team
 */
public class RefundConfiguration {

    @Bean
    public Encoder encoder() {
        return new SignatureEncoder();
    }

    @Bean
    public Decoder decoder() {
        return new SignatureVerifyDecoder();
    }

    @Bean
    @ConditionalOnBean(SSLContext.class)
    public Client client(SSLContext sslContext) {
        return new Client.Default(sslContext.getSocketFactory(), new NoopHostnameVerifier());
    }
}
