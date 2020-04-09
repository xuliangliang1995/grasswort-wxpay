package com.grasswort.wxpay;

import com.grasswort.wxpay.config.WxMchProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(WxMchProperties.class)
public class WxpayApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WxpayApplication.class, args);
        WxMchProperties mchProperties = ctx.getBean(WxMchProperties.class);
        System.out.println(mchProperties);
    }

//    @Bean
//    public ApplicationRunner run(WxMchProperties mchProperties) {
//        return (ApplicationArguments args) -> {
//            System.out.println(mchProperties);
//        };
//    }
}
