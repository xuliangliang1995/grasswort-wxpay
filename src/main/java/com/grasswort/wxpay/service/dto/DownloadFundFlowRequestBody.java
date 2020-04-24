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
 * @Classname DownloadFundFlowRequestBody.java
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
public class DownloadFundFlowRequestBody {
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 资金账单日期
     */
    @XmlElement(name = "bill_date")
    private String billDate;
    /**
     * 资金账户类型
     * Basic  基本账户; Operation 运营账户; Fees 手续费账户
     */
    @XmlElement(name = "account_type")
    private String accountType;
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
