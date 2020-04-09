package com.grasswort.wxpay.rpc.encoder;

import com.grasswort.wxpay.util.ISignatureUtil;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

/**
 * @author xuliangliang
 * @Classname SignatureEncoder.java
 * @Description
 * @Date 2020/4/7
 * @blame Java Team
 */
public class SignatureEncoder implements Encoder {
    @Autowired
    private ISignatureUtil signatureUtil;

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {

    }
}
