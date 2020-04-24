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
 * @Classname CloseOrderResponseBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class CloseOrderResponseBody {
    /**
     * SUCCESS/FAIL
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息，如非空，为错误原因
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * appid
     */
    private String appid;
    @XmlElement(name = "mch_id")
    /**
     * 微信商户号
     */
    private int mchId;
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
     * 	SUCCESS/FAIL
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 对于业务执行的详细描述
     */
    @XmlElement(name = "result_msg")
    private String resultMsg;
    /**
     * 错误代码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误代码描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;


}
