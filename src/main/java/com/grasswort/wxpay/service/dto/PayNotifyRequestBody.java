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
 * @Classname PayResultNotifyBody.java
 * @Description 支付结果通知消息体
 * @Date 2020/4/10
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class PayNotifyRequestBody {
    /**
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 通信标识对应的失败原因
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
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
     * 设备号
     */
    @XmlElement(name = "device_info")
    private String deviceInfo;
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
     * 签名类型
     */
    @XmlElement(name = "sign_type")
    private String signType;
    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 错误代码
     * 错误返回的信息描述
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误返回的信息描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    @XmlElement(name = "is_subscribe")
    private String isSubscribe;
    /**
     * 交易类型(JSAPI、NATIVE、APP)
     */
    @XmlElement(name = "trade_type")
    private String tradeType;
    /**
     * CMC	银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    @XmlElement(name = "bank_type")
    private String bankType;
    /**
     * 订单总金额
     */
    @XmlElement(name = "total_fee")
    private String totalFee;
    /**
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;
    /**
     * CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @XmlElement(name = "fee_type")
    private String feeType;
    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     */
    @XmlElement(name = "cash_fee")
    private String cashFee;
    /**
     * 现金支付货币类型 CNY
     */
    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;
    /**
     * 总代金券金额
     */
    @XmlElement(name = "coupon_fee")
    private String couponFee;
    /**
     * 代金券使用数量
     */
    @XmlElement(name = "coupon_count")
    private String couponCount;
    /**
     * 代金券类型
     * CASH--充值代金券
     * NO_CASH---非充值代金券
     */
    @XmlElement(name = "coupon_type_$n")
    private String coupon_Type$n;
    /**
     * 代金券ID
     */
    @XmlElement(name = "coupon_id_$n")
    private String couponId$n;
    /**
     * 单个代金券支付金额
     */
    @XmlElement(name = "coupon_fee_$n")
    private String couponFee$n;
    /**
     * 微信支付订单号
     */
    @XmlElement(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 商家数据包（原样返回）
     */
    private String attach;
    /**
     * 支付完成时间
     */
    @XmlElement(name = "time_end")
    private String timeEnd;
}
