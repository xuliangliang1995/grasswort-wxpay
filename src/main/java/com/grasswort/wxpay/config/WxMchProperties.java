package com.grasswort.wxpay.config;

import com.grasswort.wxpay.exception.WxPayCertException;
import com.grasswort.wxpay.exception.UnsupportedArithmeticException;
import com.grasswort.wxpay.service.constants.SignatureArithmeticEnum;
import com.grasswort.wxpay.util.ISignatureUtil;
import com.grasswort.wxpay.util.impl.HMACSHA256Signature;
import com.grasswort.wxpay.util.impl.MD5Signature;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
public class WxMchProperties {

    public static final String SERVICE_URL_EXPRESSION = "${mch.serviceUrl}";
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
                .findFirst().orElseThrow(() -> new UnsupportedArithmeticException(signType));

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
        char[] password = mchProperties.getCertPwd().toCharArray();
        InputStream certStream = null;
        try {
            certStream = new FileInputStream(mchProperties.getCertPath());
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
            return sslContext;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        throw new WxPayCertException("证书配置存在问题，请检查。");
    }

}
