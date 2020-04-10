package com.grasswort.wxpay.config;

import com.grasswort.wxpay.exception.CertException;
import com.grasswort.wxpay.exception.UnSupportSignatureArithmeticException;
import com.grasswort.wxpay.rpc.constants.SignatureArithmeticEnum;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.impl.HMACSHA256Signature;
import com.grasswort.wxpay.util.impl.MD5Signature;
import lombok.Data;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

/**
 * @author xuliangliang
 * @Classname WxMchProperties.java
 * @Description 微信商户配置属性
 * @Date 2020/4/9
 * @blame Java Team
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mch")
@PropertySource(value = "classpath:mch.properties", encoding = "UTF-8")
public class WxMchProperties {
    /**
     * 商户 id
     */
    @Value("${mch.mchId}")
    private String mchId;
    /**
     * 商户 API 密钥
     */
    @Value("${mch.key}")
    private String key;
    /**
     * 签名算法
     */
    @Value("${mch.signType:MD5}")
    private String signType;
    /**
     * 证书路径
     */
    @Value("${mch.certPath:}")
    private String certPath;
    /**
     * 证书密码（默认是 mch_id）
     */
    @Value("${mch.certPwd:}")
    private String certPwd;


    /**
     * 签名工具
     * @return
     */
    @Bean
    public ISignatureUtil signatureUtil() {
        SignatureArithmeticEnum signatureArithmeticEnum = Arrays.stream(SignatureArithmeticEnum.values())
                .filter(arithmetic -> arithmetic.getValue().equals(signType))
                .findFirst().orElseThrow(() -> new UnSupportSignatureArithmeticException(signType));

        switch (signatureArithmeticEnum) {
            case HMAC_SHA256:
                return new HMACSHA256Signature();
            default:
                return new MD5Signature();
        }
    }


    /**
     * 微信退款等资金回退接口需要的证书
     * @param mchProperties
     * @return
     */
    @Bean
    @ConditionalOnPropertiesExists({"mch.certPath", "mch.certPwd"})
    public SSLContext sslSocket(WxMchProperties mchProperties) {
        String certPath = mchProperties.getCertPath();
        String certPwd = mchProperties.getCertPwd();
        FileInputStream inputStream = null;
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            // 读取本机存放的PKCS12证书文件
            inputStream = new FileInputStream(certPath);
            // 指定PKCS12的密码(商户ID)
            clientStore.load(inputStream, certPwd.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(clientStore, certPwd.toCharArray()).build();
            return sslcontext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new CertException("微信支付证书不存在：" + certPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
