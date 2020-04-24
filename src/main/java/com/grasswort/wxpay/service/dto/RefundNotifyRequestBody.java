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
 * @Classname RefundNotifyRequestBody.java
 * @Description 退款通知请求体
 * @Date 2020/4/13
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class RefundNotifyRequestBody {
    /**
     * 返回状态码
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息
     * 当return_code为FAIL时返回信息为错误原因 ，例如 签名失败 参数格式校验错误
     *
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * 公众账号ID
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
     * 加密消息体
     */
    @XmlElement(name = "req_info")
    private String reqInfo;
    /**
     * 加密信息请用商户秘钥进行解密，详见解密方式
     */
    private RefundNotifyReqInfo decodedReqInfo;

}
