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
 * @Classname UnifiedOrderResponseBody.java
 * @Description 统一下单响应体
 * @Date 2020/4/5
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML_ROOT_NODE_NAME)
public class UnifiedOrderResponseBody {
    /**
     * 返回状态码
     * SUCCESS/FAI
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息，如非空，为错误原因
     * 签名失败
     * 参数格式校验错误
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;
    /**
     * 业务结果 SUCCESS/FAIL
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    /**
     * 错误码
     */
    @XmlElement(name = "err_code")
    private String errCode;
    /**
     * 错误码描述
     */
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    /**
     * 微信公众平台 appid
     */
    private String appid;
    /**
     * 商户ID
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
     * 交易类型
     */
    @XmlElement(name = "trade_type")
    private String tradeType;
    /**
     * 预支付交易会话标识
     */
    @XmlElement(name = "prepay_id")
    private String prepayId;
    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    @XmlElement(name = "code_url")
    private String codeUrl;
}
