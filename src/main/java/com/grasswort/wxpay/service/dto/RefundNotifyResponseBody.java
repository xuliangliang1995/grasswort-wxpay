package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.grasswort.wxpay.util.JAXBUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuliangliang
 * @Classname RefundNotifyResponseBody.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML)
public class RefundNotifyResponseBody {
    /**
     * 返回状态码
     * SUCCESS/FAIL
     * SUCCESS表示商户接收通知成功并校验成功
     */
    @XmlElement(name = "return_code")
    private String returnCode;
    /**
     * 返回信息，如非空，为错误原因
     */
    @XmlElement(name = "return_msg")
    private String returnMsg;

    /**
     * 处理成功回应给微信的 xml 字符串
     * @return
     */
    public static String successRes() {
        return SUCCESS_RES;
    }

    /**
     * 处理失败返回给微信的消息体（处理失败不回应也无妨）
     * @return
     */
    public static String errorRes(String errMsg) {
        return JAXBUtil.marshal(new RefundNotifyResponseBody("FAIL", errMsg));
    }

    private static final String SUCCESS_RES = JAXBUtil.marshal(new RefundNotifyResponseBody(WxPayConstants.SUCCESS, "OK"));
}
