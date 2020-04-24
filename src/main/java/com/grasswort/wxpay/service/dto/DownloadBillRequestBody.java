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
 * @Classname DownloadBillRequestBody.java
 * @Description
 * @Date 2020/4/24
 * @blame Java Team
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class DownloadBillRequestBody {
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 对账单日期 : 20200422
     */
    @XmlElement(name = "bill_date")
    private String billDate;
    /**
     * ALL（默认值），返回当日所有订单信息（不含充值退款订单）
     * SUCCESS，返回当日成功支付的订单（不含充值退款订单）
     * REFUND，返回当日退款订单（不含充值退款订单）
     * RECHARGE_REFUND，返回当日充值退款订单
     */
    @XmlElement(name = "bill_type")
    private String billType;
    /**
     * 随机字符串 <= 32
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 压缩账单
     * 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
     */
    @XmlElement(name = "tar_type")
    private String tarType;
}
