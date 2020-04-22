package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author xuliangliang
 * @Classname RefundQueryResponseBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class RefundQueryResponseBody {
    /**
     * 返回状态码	SUCCESS/FAIL
     */
    @XStreamAlias("return_code")
    private String returnCode;
    /**
     * 返回信息
     */
    @XStreamAlias("return_msg")
    private String returnMsg;
    /**
     * 业务结果
     */
    @XStreamAlias("result_code")
    private String resultCode;
    /**
     * 错误码
     */
    @XStreamAlias("err_code")
    private String errCode;
    /**
     * 错误描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;
    /**
     * 微信公众平台 appid
     */
    private String appid;
    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;
    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 订单总退款次数
     */
    @XStreamAlias("total_refund_count")
    private String totalRefundCount;
    /**
     * 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 订单金额
     */
    @XStreamAlias("total_fee")
    private String totalFee;
    /**
     * 应结订单金额
     */
    @XStreamAlias("settlement_total_fee")
    private String settlementTotalFee;
    /**
     * 货币种类
     */
    @XStreamAlias("fee_type")
    private String feeType;
    /**
     * 现金支付金额
     */
    @XStreamAlias("cash_fee")
    private String cashFee;
    /**
     * 退款笔数
     */
    @XStreamAlias("refund_count")
    private String refundCount;
    /**
     * 商户退款单号
     */
    @XStreamAlias("out_refund_no_$n")
    private String outRefundNo$n;
    /**
     * 微信退款单号
     */
    @XStreamAlias("refund_id_$n")
    private String refundId$n;
    /**
     * 退款渠道
     */
    @XStreamAlias("refund_channel_$n")
    private String refundChannel$n;
    /**
     * 申请退款金额
     */
    @XStreamAlias("refund_fee_$n")
    private String refundFee$n;
    /**
     * 退款金额
     */
    @XStreamAlias("settlement_refund_fee_$n")
    private String settlementRefundFee$n;
    /**
     * 代金券类型
     */
    @XStreamAlias("coupon_type_$n_$m")
    private String couponType$n$m;
    /**
     * 总代金券退款金额
     */
    @XStreamAlias("coupon_refund_fee_$n")
    private String couponRefundFee$n;
    /**
     * 退款代金券使用数量
     */
    @XStreamAlias("coupon_refund_count_$n")
    private String couponRefundCount$n;
    /**
     * 退款代金券ID
     */
    @XStreamAlias("coupon_refund_id_$n_$m")
    private String couponRefundId$n$m;
    /**
     * 单个代金券退款金额
     */
    @XStreamAlias("coupon_refund_fee_$n_$m")
    private String couponRefundFee$n$m;
    /**
     * 退款状态
     */
    @XStreamAlias("refund_status_$n")
    private String refundStatus$n;
    /**
     * 退款资金来源
     */
    @XStreamAlias("refund_account_$n")
    private String refundAccount$n;
    /**
     * 退款入账账户
     */
    @XStreamAlias("refund_recv_accout_$n")
    private String refundRecvAccout$n;
    /**
     * 退款成功时间
     */
    @XStreamAlias("refund_success_time_$n")
    private String refundSuccessTime$n;
}
