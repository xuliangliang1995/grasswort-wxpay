package com.grasswort.wxpay.service.constants;

/**
 * @author xuliangliang
 * @Classname TradeStateEnum.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
public enum TradeStateEnum {
    SUCCESS("SUCCESS", "支付成功"),
    REFUND("REFUND", "转入退款"),
    NOTPAY("NOTPAY", "未支付"),
    CLOSED("CLOSED", "已关闭"),
    REVOKED("REVOKED", "已撤销（刷卡支付）"),
    USERPAYING("USERPAYING", "用户支付中"),
    PAYERROR("PAYERROR", "支付失败(其他原因，如银行返回失败)");

    TradeStateEnum(String value, String note) {
        this.value = value;
    }
    private String value;


    public String getValue() {
        return value;
    }
}
