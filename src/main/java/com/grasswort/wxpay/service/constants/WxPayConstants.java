package com.grasswort.wxpay.service.constants;

import com.grasswort.wxpay.config.WxMchProperties;

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
    public static final String WX_PAY_SERVICE_URL = WxMchProperties.SERVICE_URL_EXPRESSION;
    /**
     * application/xml
     */
    public static final String CONTENT_TYPE_APPLICATION_XML = "Content-Type: application/xml";
    /**
     * xml 根节点 name
     */
    public static final String XML_ROOT_NODE_NAME = "xml";

    /**
     * 服务地址
     */
    public static final class ServiceUrl {
        /**
         * 正式
         */
        public static final String OFFICIAL_SERVICE_URL = "https://api.mch.weixin.qq.com";
        /**
         * 沙箱
         */
        public static final String SANDBOX_SERVICE_URL = "https://api.mch.weixin.qq.com/sandboxnew";
    }

    /**
     * 服务路由
     */
    public static final class Path {
        /**
         * 统一下单
         */
        public static final String UNIFIED_ORDER_PATH = "/pay/unifiedorder";
        /**
         * 退款
         */
        public static final String REFUND_PATH = "/secapi/pay/refund";
        /**
         * 订单查询路径
         */
        public static final String ORDER_QUERY_PATH = "/pay/orderquery";
        /**
         * 注意！！！沙箱环境
         * 获取沙箱环境密钥
         */
        public static final String SANDBOX_GET_SIGN_KEY = "/pay/getsignkey";
    }

    /**
     * contextId
     * 当 @FeignClient name 相同时，用 contextId 来区分不同的 client
     */
    public static final class ContextId {
        /**
         * 沙箱密钥
         */
        public static final String SAND_BOX_SIGN_KEY = "sandboxSignKey";
        /**
         * 统一下单 contextId
         */
        public static final String UNIFIED_ORDER = "unifiedOrderService";
        /**
         * 退款 contextId
         */
        public static final String REFUND = "refundService";
        /**
         * 订单支付状态查询
         */
        public static final String ORDER_QUERY = "orderQuery";
    }

    /**
     * 成功
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 失败
     */
    public static final String FAIL = "FAIL";
}
