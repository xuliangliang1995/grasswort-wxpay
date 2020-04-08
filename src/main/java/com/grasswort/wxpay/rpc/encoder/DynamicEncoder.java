package com.grasswort.wxpay.rpc.encoder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

/**
 * @author xuliangliang
 * @Classname DynamicEncoder.java
 * @Description
 * @Date 2020/4/7
 * @blame Java Team
 */
public class DynamicEncoder implements Encoder {

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {

    }
}
