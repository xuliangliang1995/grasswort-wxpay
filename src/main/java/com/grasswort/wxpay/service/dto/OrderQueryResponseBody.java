package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author xuliangliang
 * @Classname OrderQueryResponseBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class OrderQueryResponseBody {
    /**
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
     */
    @XStreamAlias("return_code")
    private String returnCode;
    /**
     * 返回信息，如非空，为错误原因
     */
    @XStreamAlias("return_msg")
    private String returnMsg;
    /**
     * 微信分配的微信公众平台（或微信开放平台）appid
     */
    private String appid;
    /**
     * 微信支付分配的商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;
    /**
     * 随机字符串，不长于32位。
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * SUCCESS/FAIL
     */
    @XStreamAlias("result_code")
    private String resultCode;
    /**
     * 错误码
     */
    @XStreamAlias("err_code")
    private String errCode;
    /**
     * 结果信息描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;
    /**
     * 微信支付分配的终端设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;
    /**
     * 用户在商户 appid下的唯一标识
     */
    private String openid;
    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    @XStreamAlias("is_subscribe")
    private String IsSubscribe;
    /**
     * 交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY
     */
    @XStreamAlias("trade_type")
    private String tradeType;
    /**
     * 交易状态
     * @see com.grasswort.wxpay.service.constants.TradeStateEnum
     */
    @XStreamAlias("trade_state")
    private String tradeState;
    /**
     * 银行类型，采用字符串类型的银行标识
     */
    @XStreamAlias("bank_type")
    private String bankType;
    /**
     * 订单总金额，单位为分
     */
    @XStreamAlias("total_fee")
    private String totalFee;
    /**
     * 应结订单金额
     * 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
     */
    @XStreamAlias("settlement_total_fee")
    private String settlementTotalFee;
    /**
     * 货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;
    /**
     * 现金支付金额
     */
    @XStreamAlias("cash_fee")
    private String cashFee;
    /**
     * 现金支付币种
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;
    /**
     * 代金券金额
     */
    @XStreamAlias("coupon_fee")
    private String couponFee;
    /**
     * 代金券使用数量
     */
    @XStreamAlias("coupon_count")
    private String couponCount;
    /**
     * 代金券类型
     * CASH--充值代金券
     * NO_CASH---非充值优惠券
     */
    @XStreamAlias("coupon_type_$n")
    private String couponType$n	;
    /**
     * 代金券ID
     */
    @XStreamAlias("coupon_id_$n")
    private String couponId$n;
    /**
     * 单个代金券支付金额
     */
    @XStreamAlias("coupon_fee_$n")
    private String couponFee$n;
    /**
     * 微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 附加数据，原样返回
     */
    private String attach;
    @XStreamAlias("time_end")
    /**
     * 订单支付时间，格式为 yyyyMMddHHmmss
     */
    private String timeEnd;
    /**
     * 交易状态描述
     */
    @XStreamAlias("trade_state_desc")
    private String tradeStateDesc;

}
