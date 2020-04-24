package com.grasswort.wxpay.controller;

import com.grasswort.wxpay.service.IPayNotifyResolver;
import com.grasswort.wxpay.service.IRefundNotifyResolver;
import com.grasswort.wxpay.service.dto.PayNotifyRequestBody;
import com.grasswort.wxpay.service.dto.RefundNotifyRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuliangliang
 * @Classname NotifyHandleController.java
 * @Description 通知处理
 * @Date 2020/4/24
 * @blame Java Team
 */
@RestController
@RequestMapping("/notify")
public class NotifyHandleController {

    private IPayNotifyResolver payNotifyResolver;

    private IRefundNotifyResolver refundNotifyResolver;

    public NotifyHandleController(IPayNotifyResolver payNotifyResolver, IRefundNotifyResolver refundNotifyResolver) {
        this.payNotifyResolver = payNotifyResolver;
        this.refundNotifyResolver = refundNotifyResolver;
    }

    @PostMapping("/payResult")
    public ResponseEntity<String> handlePayNotify(@RequestBody String xml) {
        try {
            PayNotifyRequestBody requestBody = payNotifyResolver.resolvePayNotify(xml);
            // do business
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(payNotifyResolver.errorRes(e.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<>(payNotifyResolver.successRes(), HttpStatus.OK);
    }

    @PostMapping("/refundResult")
    public ResponseEntity<String> handleRefundNotify(@RequestBody String xml) {
        try {
            RefundNotifyRequestBody requestBody = refundNotifyResolver.resolveRefundNotify(xml);
            // do business
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(payNotifyResolver.errorRes(e.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<>(refundNotifyResolver.successRes(), HttpStatus.OK);
    }


}
