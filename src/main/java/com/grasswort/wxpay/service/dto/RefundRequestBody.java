package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xuliangliang
 * @Classname RefundRequestBody.java
 * @Description 退款请求体
 * @Date 2020/4/10
 * @blame Java Team
 */
@Data
@Builder
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class RefundRequestBody {
    /**
     * 小程序ID
     */
    @NotBlank
    @Length(max = 32)
    private String appid;
    /**
     * 随机字符串
     */
    @NotBlank
    @Length(max = 32)
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 微信订单号
     */
    @Length(max = 32)
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @Length(max = 32)
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 商户退款单号
     * 同一退款单号多次请求只退一笔。
     */
    @NotBlank
    @Length(max = 64)
    @XStreamAlias("out_refund_no")
    private String outRefundNo;
    /**
     * 订单总金额，单位为分，只能为整数，详见支付金额
     */
    @NotNull
    @Min(1)
    @XStreamAlias("total_fee")
    private Integer totalFee;
    /**
     * 退款金额
     */
    @NotNull
    @Min(1)
    @XStreamAlias("refund_fee")
    private Integer refundFee;
    /**
     * 货币种类. 默认 CNY
     */
    @Length(max = 8)
    @XStreamAlias("refund_fee_type")
    private String refundFeeType;
    /**
     * 退款原因
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     */
    @Length(max = 80)
    @XStreamAlias("refund_desc")
    private String refundDesc;
    /**
     * 退款资金来源
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     * 仅针对老资金流商户使用
     */
    @Length(max = 30)
    @XStreamAlias("refund_account")
    private String refundAccount;
    /**
     * 退款结果通知 URL
     * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效
     */
    @Length(max = 256)
    @XStreamAlias("notify_url")
    private String notifyUrl;
}
