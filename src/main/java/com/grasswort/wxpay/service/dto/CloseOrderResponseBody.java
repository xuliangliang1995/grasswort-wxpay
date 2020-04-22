package com.grasswort.wxpay.service.dto;

import com.grasswort.wxpay.service.constants.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author xuliangliang
 * @Classname CloseOrderResponseBody.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
@Data
@XStreamAlias(WxPayConstants.XML_ROOT_NODE_NAME)
public class CloseOrderResponseBody {
    /**
     * SUCCESS/FAIL
     */
    @XStreamAlias("return_code")
    private String returnCode;
    /**
     * 返回信息，如非空，为错误原因
     */
    @XStreamAlias("return_msg")
    private String returnMsg;
    /**
     * appid
     */
    private String appid;
    @XStreamAlias("mch_id")
    /**
     * 微信商户号
     */
    private int mchId;
    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 	SUCCESS/FAIL
     */
    @XStreamAlias("result_code")
    private String resultCode;
    /**
     * 对于业务执行的详细描述
     */
    @XStreamAlias("result_msg")
    private String resultMsg;
    /**
     * 错误代码
     */
    @XStreamAlias("err_code")
    private String errCode;
    /**
     * 错误代码描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;


}
