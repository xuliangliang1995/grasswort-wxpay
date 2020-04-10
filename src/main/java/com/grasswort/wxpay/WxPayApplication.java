package com.grasswort.wxpay;

import com.grasswort.wxpay.rpc.IUnifiedOrderService;
import com.grasswort.wxpay.rpc.dto.UnifiedOrderRequestBody;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class WxPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxPayApplication.class, args);
    }

    @Bean
    public ApplicationRunner testUnifiedOrderService(IUnifiedOrderService iUnifiedOrderService) {
        return args -> iUnifiedOrderService.unifiedOrder(
                            UnifiedOrderRequestBody.builder()
                            .appid("sljdkflsdflsdkf")
                            .build()
                    );
    }

}
