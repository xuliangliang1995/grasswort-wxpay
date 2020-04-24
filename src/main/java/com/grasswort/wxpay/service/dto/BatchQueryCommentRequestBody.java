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
 * @Classname BatchQueryCommentRequestBody.java
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
public class BatchQueryCommentRequestBody {
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    /**
     * 位移
     * 指定从某条记录的下一条开始返回记录。接口调用成功时，会返回本次查询最后一条数据的offset。商户需要翻页时，
     * 应该把本次调用返回的offset 作为下次调用的入参。注意offset是评论数据在微信支付后台保存的索引，未必是连续的
     */
    private int offset;
    /**
     * 条数
     *  一次拉取的条数, 最大值是200，默认是200
     */
    private int limit;
    /**
     * 开始时间
     * 按用户评论时间批量拉取的起始时间，格式为yyyyMMddHHmmss
     */
    @XmlElement(name = "begin_time")
    private String beginTime;
    /**
     * 结束时间
     * 按用户评论时间批量拉取的结束时间，格式为yyyyMMddHHmmss
     */
    @XmlElement(name = "end_time")
    private String endTime;

}
