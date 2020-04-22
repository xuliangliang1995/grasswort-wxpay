package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * @author xuliangliang
 * @Classname RefundQueryRequestBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@Builder
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class RefundQueryRequestBody {
    /**
     * appid
     */
    private String appid;
    /**
     * 随机字符串 <= 32位
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 商户退款单号
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;
    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 退款单号
     */
    @XStreamAlias("refund_id")
    private String refundId;
    /**
     * 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * 偏移量
     */
    private Integer offset;
}
