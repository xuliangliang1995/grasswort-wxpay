package com.grasswort.wxpay.service.notify;

import com.grasswort.wxpay.service.INotifyHandler;
import com.grasswort.wxpay.service.dto.PayNotifyHandleResult;
import com.grasswort.wxpay.service.dto.RefundNotifyHandleResult;
import org.springframework.stereotype.Service;

/**
 * @author xuliangliang
 * @Classname CompositeNotifyHandler.java
 * @Description 通知处理类
 * @Date 2020/4/13
 * @blame Java Team
 */
@Service
public class CompositeNotifyHandler implements INotifyHandler {

    public CompositeNotifyHandler(PayResultNotifyHandler payResultNotifyHandler, RefundResultNotifyHandler refundResultNotifyHandler) {
        this.payResultNotifyHandler = payResultNotifyHandler;
        this.refundResultNotifyHandler = refundResultNotifyHandler;
    }

    private final PayResultNotifyHandler payResultNotifyHandler;
    private final RefundResultNotifyHandler refundResultNotifyHandler;

    @Override
    public PayNotifyHandleResult handlePayNotify(String xml) {
        return payResultNotifyHandler.handlePayNotify(xml);
    }

    @Override
    public RefundNotifyHandleResult handleRefundNotify(String xml) {
        return refundResultNotifyHandler.handleRefundNotify(xml);
    }
}
