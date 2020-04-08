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
     * 服务路由
     */
    public static final class Path {
        /**
         * 统一下单
         */
        public static final String UNIFIED_ORDER_PATH = "/pay/unifiedorder";
    }
}
