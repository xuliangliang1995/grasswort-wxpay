package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuliangliang
 * @Classname PayNotifyResBody.java
 * @Description 支付通知结果响应体
 * @Date 2020/4/10
 * @blame Java Team
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class PayNotifyResponseBody {
    /**
     * SUCCESS/FAIL
     * SUCCESS表示商户接收通知成功并校验成功
     */
    @XStreamAlias("return_code")
    private String returnCode;
    /**
     * 返回信息，如非空，为错误原因：
     * 签名失败
     * 参数格式校验错误
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

}
