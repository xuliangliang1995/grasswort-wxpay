package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * @author xuliangliang
 * @Classname CloseOrderRequestBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@Builder
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class CloseOrderRequestBody {
    /**
     * 微信公众平台(或微信开放平台) appid
     */
    private String appid;
    /**
     * 随机字符串，<= 32 位
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
}
