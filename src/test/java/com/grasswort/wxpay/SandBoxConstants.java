package com.grasswort.wxpay;

/**
 * @author xuliangliang
 * @Classname SandBoxConstants.java
 * @Description
 * @Date 2020/4/22
 * @blame Java Team
 */
public class SandBoxConstants {

    public static final String APP_ID = "wx2421b1c4370ec43b";

    /**
     * 沙箱用例
     */
    public static class SandBoxCase {
        /**
         * 成功用例，但不通知
         */
        public static class SuccessNotNotify {
            public static final int FEE = 130;
            public static final String OUT_TRADE_NO = "20200422";
        }
    }
}
