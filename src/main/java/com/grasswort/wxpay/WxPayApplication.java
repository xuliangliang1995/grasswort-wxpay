package com.grasswort.wxpay;

import com.grasswort.wxpay.config.WxMchProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class WxPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxPayApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(WxMchProperties wxMchProperties) {
        return args -> {
            log.info("\n【商户ID】：{}\n【API密钥】：{}\n【签名方式】：{}\n【证书路径】：{}\n【证书密码】：{}",
                    wxMchProperties.getMchId(), "*****", wxMchProperties.getSignType(),
                    StringUtils.isNotEmpty(wxMchProperties.getCertPath()) ? "*****" : "空",
                    StringUtils.isNotEmpty(wxMchProperties.getCertPwd()) ? "*****" : "空"
                    );
            if (StringUtils.isEmpty(wxMchProperties.getCertPath())) {
                log.error("\n【注意】微信支付证书路径没有配置，退款等资金回滚操作将不可用。");
            }
        };
    }

}
