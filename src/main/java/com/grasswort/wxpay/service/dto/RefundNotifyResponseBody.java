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
 * @Classname RefundNotifyResponseBody.java
 * @Description
 * @Date 2020/4/13
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = WxPayConstants.XML_ROOT_NODE_NAME)
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
}
