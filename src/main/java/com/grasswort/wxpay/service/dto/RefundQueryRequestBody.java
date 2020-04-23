package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuliangliang
 * @Classname RefundQueryRequestBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML_ROOT_NODE_NAME)
public class RefundQueryRequestBody {
    /**
     * appid
     */
    private String appid;
    /**
     * 随机字符串 <= 32位
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 商户退款单号
     */
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 退款单号
     */
    @XmlElement(name = "refund_id")
    private String refundId;
    /**
     * 微信订单号
     */
    @XmlElement(name = "transaction_id")
    private String transactionId;
    /**
     * 偏移量
     */
    private Integer offset;
}
