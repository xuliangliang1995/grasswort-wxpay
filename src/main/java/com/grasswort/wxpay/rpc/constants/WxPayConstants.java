package com.grasswort.wxpay.rpc.constants;

/**
 * @author xuliangliang
 * @Classname WxPayConstants.java
 * @Description 微信支付常量
 * @Date 2020/4/5
 * @blame Java Team
 */
public class WxPayConstants {
    /**
     * 微信服务名称（自定义）
     */
    public static final String WX_PAY_SERVER_NAME = "wxpay-server";
    /**
     * 微信支付远程服务地址
     */
    public static final String WX_PAY_SERVICE_URL = "https://api.mch.weixin.qq.com";
    /**
     * application/xml
     */
    public static final String CONTENT_TYPE_APPLICATION_XML = "Content-Type: application/xml";

    /**
     * 服务路由
     */
    public static final class Path {
        /**
         * 统一下单
         */
        public static final String UNIFIED_ORDER_PATH = "/pay/unifiedorder";
    }

    /**
     * contextId
     * 当 @FeignClient name 相同时，用 contextId 来区分不同的 client
     */
    public static final class ContextId {
        /**
         * 统一下单 contextId
         */
        public static final String UNIFIED_ORDER = "unifiedOrderService";
    }
}
