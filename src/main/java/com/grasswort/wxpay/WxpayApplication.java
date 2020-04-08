package com.grasswort.wxpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WxpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxpayApplication.class, args);
    }

}
