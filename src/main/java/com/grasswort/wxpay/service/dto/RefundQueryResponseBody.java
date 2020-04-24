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
 * @Classname RefundQueryResponseBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class RefundQueryResponseBody {
    /**
     * 返回状态码	SUCCESS/FAIL
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * 业务结果
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 错误码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    /**
     * 微信公众平台 appid
     */
    private String appid;
    /**
     * 商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;
    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 订单总退款次数
     */
    @XmlElement(name = "total_refund_count")
    private String totalRefundCount;
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
     * 订单金额
     */
    @XmlElement(name = "total_fee")
    private String totalFee;
    /**
     * 应结订单金额
     */
    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;
    /**
     * 货币种类
     */
    @XmlElement(name = "fee_type")
    private String feeType;
    /**
     * 现金支付金额
     */
    @XmlElement(name = "cash_fee")
    private String cashFee;
    /**
     * 退款笔数
     */
    @XmlElement(name = "refund_count")
    private String refundCount;
    /**
     * 商户退款单号
     */
    @XmlElement(name = "out_refund_no_$n")
    private String outRefundNo$n;
    /**
     * 微信退款单号
     */
    @XmlElement(name = "refund_id_$n")
    private String refundId$n;
    /**
     * 退款渠道
     */
    @XmlElement(name = "refund_channel_$n")
    private String refundChannel$n;
    /**
     * 申请退款金额
     */
    @XmlElement(name = "refund_fee_$n")
    private String refundFee$n;
    /**
     * 退款金额
     */
    @XmlElement(name = "settlement_refund_fee_$n")
    private String settlementRefundFee$n;
    /**
     * 代金券类型
     */
    @XmlElement(name = "coupon_type_$n_$m")
    private String couponType$n$m;
    /**
     * 总代金券退款金额
     */
    @XmlElement(name = "coupon_refund_fee_$n")
    private String couponRefundFee$n;
    /**
     * 退款代金券使用数量
     */
    @XmlElement(name = "coupon_refund_count_$n")
    private String couponRefundCount$n;
    /**
     * 退款代金券ID
     */
    @XmlElement(name = "coupon_refund_id_$n_$m")
    private String couponRefundId$n$m;
    /**
     * 单个代金券退款金额
     */
    @XmlElement(name = "coupon_refund_fee_$n_$m")
    private String couponRefundFee$n$m;
    /**
     * 退款状态
     */
    @XmlElement(name = "refund_status_$n")
    private String refundStatus$n;
    /**
     * 退款资金来源
     */
    @XmlElement(name = "refund_account_$n")
    private String refundAccount$n;
    /**
     * 退款入账账户
     */
    @XmlElement(name = "refund_recv_accout_$n")
    private String refundRecvAccout$n;
    /**
     * 退款成功时间
     */
    @XmlElement(name = "refund_success_time_$n")
    private String refundSuccessTime$n;
}
