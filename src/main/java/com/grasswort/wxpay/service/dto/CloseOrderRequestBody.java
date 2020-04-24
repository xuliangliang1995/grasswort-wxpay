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
 * @Classname CloseOrderRequestBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class CloseOrderRequestBody {
    /**
     * 微信公众平台(或微信开放平台) appid
     */
    private String appid;
    /**
     * 随机字符串，<= 32 位
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
}
