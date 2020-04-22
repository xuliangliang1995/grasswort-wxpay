package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author xuliangliang
 * @Classname OrderQueryRequestBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@Builder
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class OrderQueryRequestBody {
    /**
     * 微信分配的微信公众平台（或微信开放平台）appid
     */
    @NotBlank
    @Length(max = 32)
    private String appid;

    /**
     * 随机字符串（<= 32 位）
     */
    @NotBlank
    @Length(max = 32)
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 微信的订单号，优先使用
     * 和 out_trade_no 二选一
     */
    @Length(max = 32)
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @Length(max = 32)
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

}
