package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuliangliang
 * @Classname RefundNotifyReqInfo.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.ROOT)
public class RefundNotifyReqInfo {
    /**
     * 微信订单号
     */
    @XmlElement(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 微信退款单号
     */
    @XmlElement(name = "refund_id")
    private String refundId;
    /**
     * 商户退款单号
     */
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 订单金额
     */
    @XmlElement(name = "total_fee")
    private Integer totalFee;
    /**
     * 订单总金额，单位为分，只能为整数，详见支付金额
     * 当该订单有使用非充值券时，返回此字段。应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XmlElement(name = "settlement_total_fee")
    private Integer settlementTotalFee;
    /**
     * 申请退款金额
     * 退款总金额,单位为分
     */
    @XmlElement(name = "refund_fee")
    private Integer refundFee;
    /**
     * 退款金额
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XmlElement(name = "settlement_refund_fee")
    private Integer settlementRefundFee;
    /**
     * 退款状态
     * SUCCESS-退款成功
     * CHANGE-退款异常
     * REFUNDCLOSE—退款关闭
     */
    @XmlElement(name = "refund_status")
    private String refundStatus;
    /**
     * 退款成功时间
     */
    @XmlElement(name = "success_time")
    private String successTime;
    /**
     * 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
     */
    @XmlElement(name = "refund_recv_accout")
    private String refundRecvAccout;
    /**
     * 退款资金来源
     * REFUND_SOURCE_RECHARGE_FUNDS 可用余额退款/基本账户
     * REFUND_SOURCE_UNSETTLED_FUNDS 未结算资金退款
     */
    @XmlElement(name = "refund_account")
    private String refundAccount;
    /**
     * 退款发起来源
     * API API接口
     * VENDOR_PLATFORM 商户平台
     */
    @XmlElement(name = "refund_request_source")
    private String refundRequestSource;
}
