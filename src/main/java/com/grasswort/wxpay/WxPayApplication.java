package com.grasswort.wxpay;

import com.grasswort.wxpay.config.WxMchProperties;
import com.grasswort.wxpay.sandbox.ISandBoxSignKeyService;
import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.util.impl.MD5Signature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.dom.DOMDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class WxPayApplication {

    @Value(WxPayConstants.WX_PAY_SERVICE_URL)
    private String serviceUrl;

    public static void main(String[] args) {
        SpringApplication.run(WxPayApplication.class, args);
    }

    /**
     * 打印微信配置信息
     * @param wxMchProperties
     * @return
     */
    @Bean
    public ApplicationRunner runner1(WxMchProperties wxMchProperties) {
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

    /**
     * 判断是否沙箱环境，如果是沙箱环境，请求获取沙箱密钥
     * @param wxMchProperties
     * @param sandBoxSignKeyService
     * @return
     */
    @Bean
    public ApplicationRunner runner2(WxMchProperties wxMchProperties, ISandBoxSignKeyService sandBoxSignKeyService) {
        return args -> {
            if (Objects.equals(serviceUrl, WxPayConstants.ServiceUrl.SANDBOX_SERVICE_URL)) {
                log.warn("\n【注意】，当前使用的是【沙箱】环境，微信服务地址为：{}", serviceUrl);

                // 获取沙箱密钥
                Map<String, String> params = new HashMap<>();
                params.put("mch_id", wxMchProperties.getMchId());
                params.put("nonce_str", RandomStringUtils.randomAlphabetic(32));
                params.put("sign", new MD5Signature().signature(params, wxMchProperties.getKey()));
                Document document = new DOMDocument();
                Element root = document.addElement("xml");
                params.keySet().stream().forEach(key -> root.addElement(key).add(new DOMCDATA(params.get(key))));

                String result = sandBoxSignKeyService.getSandBoxSignKey(document.asXML());
                Document resultDoc = DocumentHelper.parseText(result);
                List<Element> resultParams = resultDoc.getRootElement().elements();
                log.warn("\n【沙箱密钥请求结果】：{}", resultParams.stream()
                        .filter(e -> "sandbox_signkey".equals(e.getName()))
                        .findFirst()
                        .map(Element::getStringValue)
                        .orElse("获取失败")
                );
            }
        };
    }

}
