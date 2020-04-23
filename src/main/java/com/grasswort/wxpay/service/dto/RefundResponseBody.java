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
 * @Classname RefundResponseBody.java
 * @Description 退款响应体
 * @Date 2020/4/10
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML_ROOT_NODE_NAME)
public class RefundResponseBody {
    /**
     * 返回状态码 SUCCESS/FAIL
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息 return_msg
     * 返回信息，如非空，为错误原因。例如：签名失败 参数格式校验错误
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * 业务结果 SUCCESS/FAIL
     * SUCCESS退款申请接收成功，结果通过退款查询接口查询
     * FAIL 提交业务失败
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 错误代码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误代码描述。
     * 例如： 系统超时	结果信息描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    /**
     * 小程序ID
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
     * 商户退款单号
     */
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 微信退款单号
     */
    @XmlElement(name = "refund_id")
    private String refundId;
    /**
     * 退款金额
     */
    @XmlElement(name = "refund_fee")
    private Integer refundFee;
    /**
     * 应结退款金额
     */
    @XmlElement(name = "settlement_refund_fee")
    private Integer settlementRefundFee;
    /**
     * 标价金额
     */
    @XmlElement(name = "total_fee")
    private Integer totalFee;
    /**
     * 应结订单金额
     */
    @XmlElement(name = "settlement_total_fee")
    private Integer settlementTotalFee;
    /**
     * 标价币种
     */
    @XmlElement(name = "fee_type")
    private String feeType;
    /**
     * 现金支付金额
     */
    @XmlElement(name = "cash_fee")
    private Integer cashFee;
    /**
     * 现金支付币种
     */
    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;
    /**
     * 现金退款金额
     */
    @XmlElement(name = "cash_refund_fee")
    private String cashRefundFee;
    /**
     * 代金券类型
     * CASH--充值代金券
     * NO_CASH---非充值代金券
     */
    @XmlElement(name = "coupon_type_$n")
    private String couponType$n;
    /**
     * 代金券退款总金额
     */
    @XmlElement(name = "coupon_refund_fee")
    private Integer couponRefundFee;
    /**
     * 单个代金券退款金额
     */
    @XmlElement(name = "coupon_refund_fee_$n")
    private Integer couponRefundFee$n;
    /**
     * 退款代金券使用数量
     */
    @XmlElement(name = "coupon_refund_count")
    private Integer couponRefundCount;
    /**
     * 退款代金券ID
     */
    @XmlElement(name = "coupon_refund_id_$n")
    private String couponRefundId$n;
}
