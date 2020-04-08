package com.grasswort.wxpay.rpc.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xuliangliang
 * @Classname UnifiedOrderRequestBody.java
 * @Description 统一下单请求体
 * @Date 2020/4/5
 * @blame Java Team
 */
@Data
@XStreamAlias("xml")
public class UnifiedOrderRequestBody {
    /**
     * 微信公众平台或者开放平台 appId
     */
    @Length(max = 32)
    @NotBlank
    private String appid;
    /**
     * 设备号（非必填）。自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @Length(max = 32)
    @XStreamAlias("device_info")
    private String deviceInfo;
    /**
     * 随机字符串
     */
    @Length(max = 32)
    @NotBlank
    @XStreamAlias("nonce_str")
    private String nonceStr;
    /**
     * 商品描述（相当于商品标题） 128 字以内
     */
    @Length(max = 128)
    @NotBlank
    private String body;
    /**
     * 商品详情
     */
    @Length(max = 6000)
    private String detail;
    /**
     * 附加数据
     */
    @Length(max = 127)
    private String attach;
    /**
     * 商户订单号(商户系统生成)
     */
    @NotBlank
    @Length(min = 32, max = 32)
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    /**
     * 标记币种
     */
    @NotBlank
    @Length(max = 16)
    @XStreamAlias("fee_type")
    private String feeType = "CNY";
    /**
     * 标价金额（分），对应币种最小单位
     */
    @NotNull
    @Size(min = 0)
    @XStreamAlias("total_fee")
    private Integer totalFee;
    /**
     * 调用微信支付 API 的机器 IP
     */
    @NotBlank
    @Length(max = 64)
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    /**
     * 交易起始时间。格式： yyyyMMddHHmmss
     */
    @Length(max = 14)
    @XStreamAlias("time_start")
    private String timeStart;
    /**
     * 交易失效时间
     */
    @Length(max = 14)
    @XStreamAlias("time_expire")
    private String timeExpire;
    /**
     * 订单优惠标记
     */
    @Length(max = 32)
    @XStreamAlias("goods_tag")
    private String goodsTag;
    /**
     * 通知地址
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @NotBlank
    @Length(max = 256)
    @XStreamAlias("notify_url")
    private String notifyUrl;
    /**
     * 交易类型
     */
    @Length(max = 16)
    @XStreamAlias("trade_type")
    private String tradeType;
    /**
     * 商品ID
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    @Length(max = 32)
    @XStreamAlias("product_id")
    private String productId;
    /**
     *  指定支付方式
     * 	上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    @Length(max = 32)
    @XStreamAlias("limit_pay")
    private String limitPay;
    /**
     * 用户标识
     * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。
     */
    @Length(max = 128)
    private String openid;
    /**
     * 电子发票入口开放标识
     * 	Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    @Length(max = 8)
    private String receipt;
    /**
     * 场景信息（json）
     */
    @Length(max = 256)
    @XStreamAlias("scene_info")
    private String sceneInfo;
}
